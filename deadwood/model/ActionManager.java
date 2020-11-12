package deadwood.model;

import deadwood.model.areas.*;
import deadwood.model.areas.Set;
import deadwood.model.events.*;

import java.util.*;

public class ActionManager {
    private Game game;
    
    public ActionManager(ArrayList<Player> players){
        game = new Game(players);
    }
    
    //do stuffs
    public MoveEvent move(String areaString) throws InvalidActionException {
        Player currentPlayer = game.getCurrentPlayer();
        Area currentArea = currentPlayer.getCurrentArea();

        Area newArea = game.getAreaForString(areaString);

        if(newArea == currentArea){
            throw new InvalidActionException("You are already there!");
        }
        //check if role == null
        if(currentPlayer.getRole() == null){
            if(newArea != null){  
                if(!game.getHasMoved()){
                    if(currentArea.isNeighbor(newArea)){
                        currentPlayer.setArea(newArea);
                        currentArea.removePlayer(currentPlayer);
                        newArea.addPlayer(currentPlayer);
                        game.hasMoved();

                    } else {
                        throw new InvalidActionException("You are not close enough to go there.");
                    }
                } else {
                    throw new InvalidActionException("You has already moved.");
                }
            } else{
                throw new InvalidActionException("That area doesn't exist.");
            }
        } else{
            throw new InvalidActionException(
                currentPlayer.getName() + " is working on a role.");
        }
        return null;
    }

    public TakeRoleEvent takeRole(String roleString) throws InvalidActionException {
        Player currentPlayer = game.getCurrentPlayer();

        if(!(currentPlayer.getCurrentArea() instanceof Set)) 
            throw new InvalidActionException(currentPlayer.getName() + " is not currently on an set.");
        else {
            Set currentArea = (Set)currentPlayer.getCurrentArea();
            
            Role currentRole = currentPlayer.getRole();
            Role newRole = ((Set)currentArea).getRoleForString(roleString);
            if(currentArea.hasActiveScene()){
                if(!game.getHasActed() || !game.getHasTakenRole()){
                    if(currentRole == null){
                        if(currentArea.isRoleFree(newRole)){
                            if(currentPlayer.isRoleValid(newRole)){
                                currentPlayer.setRole(newRole);
                                game.hasTakenRole();
                            } else {
                                throw new InvalidActionException(
                                    currentPlayer.getName() + " doesn't have a high enough rank to take this role.");
                            }
                        } else {
                            throw new InvalidActionException(roleString + " is not available.");
                        }
                    } else {
                        throw new InvalidActionException(currentPlayer.getName() + " is already working.");
                    }
                } else {
                    throw new InvalidActionException(currentPlayer.getName() + " has already acted or taken a role.");
                }
            } else {
                throw new InvalidActionException(currentArea.getName() + " has no active scene.");
            }
        }
        return null;
    }

    public ActEvent act() throws InvalidActionException {
        Player currentPlayer = game.getCurrentPlayer();
        if(!(currentPlayer.getCurrentArea() instanceof Set))
            throw new InvalidActionException(currentPlayer.getName() + " is not on a set.");
        else {
            Set currentArea = (Set)currentPlayer.getCurrentArea();
            Role currentRole = currentPlayer.getRole();

            int diceRoll = rollDie();

            if(currentRole != null) {
                if(currentArea.hasActiveScene()){
                    if(!game.getHasActed()){
                        if(!game.getHasTakenRole()) {
                            boolean succeeds = diceRoll >= (currentArea.getBudget() + currentPlayer.getPracticeChips());
                            game.hasActed();
                            game.hasRehearsed();
                            if(currentRole.checkOnCard() && succeeds){
                                currentPlayer.pay(0, 2);
                                currentArea.removeShotToken();
                                return new ActEvent(currentPlayer, true, 0, 2);
                            } else if(currentRole.checkOnCard() && !succeeds){
                                currentPlayer.pay(0, 0);
                                return new ActEvent(currentPlayer, false, 0, 0);
                            } else if(!currentRole.checkOnCard() && succeeds){
                                currentPlayer.pay(1, 1);
                                currentArea.removeShotToken();
                                return new ActEvent(currentPlayer, true, 1, 1);
                            } else if(!currentRole.checkOnCard() && !succeeds){
                                currentPlayer.pay(1, 0);
                                return new ActEvent(currentPlayer, false, 1, 0);
                            } else {
                                throw new InvalidActionException("Something went wrong....");
                            }
                        } else {
                            throw new InvalidActionException(
                                currentPlayer.getName() + " has already taken a role this turn.");
                        }
                    } else {
                        throw new InvalidActionException(
                            currentPlayer.getName() + " has already acted or rehearsed this turn.");
                    }
                } else {
                    throw new InvalidActionException(currentArea.getName() + " has no active scene.");
                }
            } else {
                throw new InvalidActionException(currentPlayer.getName() + " is not working on a role.");
            }
        }
    }

    public boolean rehearse() throws InvalidActionException {
        Player currentPlayer = game.getCurrentPlayer();
        if(!(currentPlayer.getCurrentArea() instanceof Set))
            throw new InvalidActionException(currentPlayer.getName() + " is not on a set.");
        else {
            Set currentArea = (Set)currentPlayer.getCurrentArea();
            Role currentRole = currentPlayer.getRole();
            
            if(currentRole != null) {
                if(currentPlayer.getPracticeChips() < currentArea.getBudget()) {
                    if(!game.getHasRehearsed()){
                        currentPlayer.addPracticeChip();
                        game.hasRehearsed();
                        game.hasActed();
                    } else {
                        throw new InvalidActionException(currentPlayer.getName() + " has already rehearsed or acted this turn.");
                    }
                } else {
                    throw new InvalidActionException(currentPlayer.getName() + " has too many practice chips!");
                }
            } else {
                throw new InvalidActionException(currentPlayer.getName() + " is not working on a role.");
            }
            return true;
        }
    }

    public UpgradeEvent upgrade(int desiredRank) throws InvalidActionException {
        Player currentPlayer = game.getCurrentPlayer();
        if(!(currentPlayer.getCurrentArea() instanceof CastingOffice)) 
            throw new InvalidActionException(
                String.format("Player %s not in Casting Office.", currentPlayer.getName()));
        else {
            if(!game.getHasUpgraded()) {
                CastingOffice currentArea = (CastingOffice)currentPlayer.getCurrentArea();
                if(currentArea.playerCanAffordRank(currentPlayer,desiredRank)){
                    int money = currentArea.getMoneyForRank(desiredRank);
                    int credits = currentArea.getCreditsForRank(desiredRank);
                    
                    int oldRank = currentPlayer.getRank();
                    currentPlayer.buy(money,credits);
                    currentPlayer.setRank(desiredRank);  

                    game.hasUpgraded();
                    
                    return new UpgradeEvent(currentPlayer, oldRank, desiredRank);
                } else {
                    throw new InvalidActionException(
                        String.format("Player %s cannot affort rank %d.", currentPlayer.getName(), desiredRank));
                }
            } else {
                throw new InvalidActionException(
                    currentPlayer.getName() + " has already upgraded this turn.");
            }
        }
    }
    
    public ArrayList<Event> end() {
        ArrayList<Event> events = new ArrayList<Event>();
        EndSceneEvent sceneEnd = game.endSceneCheck();
        if(sceneEnd != null) 
            events.add(sceneEnd);
        EndDayEvent dayEnd = game.endDayCheck();
        if(dayEnd != null) 
            events.add(dayEnd);
        EndGameEvent gameEnd = game.endGameCheck();
        if(gameEnd != null) 
            events.add(gameEnd);

        game.setNextPlayer();  
        return events;
    }

    private int rollDie() {
        int min = 1;
        int max = 6;
        return (int) (Math.random() * (max - min + 1) + min);
    }

    //get stuffs
	public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public Area getCurrentArea(){
        return game.getCurrentPlayer().getCurrentArea();
    }

	public ArrayList<Area> getPlayerAreas() {
        return game.getPlayerAreas();
	}

	public ArrayList<Role> getRoles() throws InvalidActionException{
        Player currentPlayer = game.getCurrentPlayer();
        Area currentArea = currentPlayer.getCurrentArea();
        if(currentArea instanceof Set) {
            //if scene is active
            return ((Set)currentArea).getRoles();
        }  else {
            throw new InvalidActionException(
                String.format("Player %s not on a Set.", currentPlayer.getName()));
        }        
	}

	public ArrayList<String> getRanks() throws InvalidActionException {
        Player currentPlayer = game.getCurrentPlayer();
        Area currentArea = currentPlayer.getCurrentArea();
        if(currentArea instanceof CastingOffice) {
            return ((CastingOffice)currentArea).getRankUpgradeStrings();
        } else {
            throw new InvalidActionException(
                String.format("Player %s not in Casting Office.", currentPlayer.getName()));
        }
	}

	public String getScene() throws InvalidActionException {
        Player currentPlayer = game.getCurrentPlayer();
        Area currentArea = currentPlayer.getCurrentArea();
        if(currentArea instanceof Set) {
           return ((Set)currentArea).getSceneInfo();
        }  else {
            throw new InvalidActionException(
                String.format("Player %s not on a Set.", currentPlayer.getName()));
        }       
	}

	public ArrayList<Player> getPlayers() {
        return game.getPlayers();
	}

	public Role getCurrentRole() {
        return game.getCurrentPlayer().getRole();
	}
}
