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

        if(newArea != null && currentArea.isNeighbor(newArea)){
            currentPlayer.setArea(newArea);
            currentArea.removePlayer(currentPlayer);
            newArea.addPlayer(currentPlayer);
        }

        return null;
    }

    public TakeRoleEvent takeRole(String roleString) throws InvalidActionException {
        Player currentPlayer = game.getCurrentPlayer();

        if(!(currentPlayer.getCurrentArea() instanceof Set)) return null;
        else {
            Set currentArea = (Set)currentPlayer.getCurrentArea();
            
            Role currentRole = currentPlayer.getRole();
            Role newRole = ((Set)currentArea).getRoleForString(roleString);

            if(currentRole == null){
                if(currentArea.isRoleFree(newRole)){
                    if(currentPlayer.isRoleValid(newRole)){
                        currentPlayer.setRole(newRole);
                    }
                }
            }

            return null;
        }
    }

    public boolean act() throws InvalidActionException {
        Player currentPlayer = game.getCurrentPlayer();
        if(!(currentPlayer.getCurrentArea() instanceof Set)) return false;
        else {
            Set currentArea = (Set)currentPlayer.getCurrentArea();
            Role currentRole = currentPlayer.getRole();

            int diceRoll = rollDie();

            if(currentArea.getIsSet() && currentRole != null) {
                boolean succeeds = diceRoll >= (currentArea.getBudget() + currentPlayer.getPracticeChips());
                if(currentRole.checkOnCard() && succeeds){
                    currentPlayer.pay(0, 2);
                    currentArea.removeShotToken();
                } else if(currentRole.checkOnCard() && !succeeds){
                    currentPlayer.pay(0, 0);
                } else if(!currentRole.checkOnCard() && succeeds){
                    currentPlayer.pay(1, 1);
                    currentArea.removeShotToken();
                } else if(!currentRole.checkOnCard() && !succeeds){
                    currentPlayer.pay(1, 0);
                }
            }


            return true;
        }
    }

    public boolean rehearse() throws InvalidActionException {
        Player currentPlayer = game.getCurrentPlayer();
        if(!(currentPlayer.getCurrentArea() instanceof Set)) return false;
        else {
            Set currentArea = (Set)currentPlayer.getCurrentArea();
            Role currentRole = currentPlayer.getRole();
            
            if(currentRole != null) {
                if(currentPlayer.getPracticeChips() < currentArea.getBudget()) {
                    currentPlayer.addPracticeChip();
                }
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
            CastingOffice currentArea = (CastingOffice)currentPlayer.getCurrentArea();
            if(currentArea.playerCanAffordRank(currentPlayer,desiredRank)){
                int money = currentArea.getMoneyForRank(desiredRank);
                int credits = currentArea.getCreditsForRank(desiredRank);
                
                int oldRank = currentPlayer.getRank();
                currentPlayer.buy(money,credits);
                currentPlayer.setRank(desiredRank);  
                
                return new UpgradeEvent(currentPlayer, oldRank, desiredRank);
            } else {
                throw new InvalidActionException(
                    String.format("Player %s cannot affort rank %d.", currentPlayer.getName(), desiredRank));
            }
        }
    }
    
    public ArrayList<Event> end() {
        ArrayList<Event> events = new ArrayList<Event>();
        EndSceneEvent sceneEnd = game.endSceneCheck();
        if(sceneEnd == null) 
            events.add(sceneEnd);
        EndDayEvent dayEnd = game.endDayCheck();
        if(dayEnd == null) 
            events.add(dayEnd);
        EndGameEvent gameEnd = game.endGameCheck();
        if(gameEnd == null) 
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
