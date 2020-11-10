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
    
    public boolean move(String areaString) /* throws InvalidActionException */ {
        Player currentPlayer = game.getCurrentPlayer();
        Area currentArea = currentPlayer.getCurrentArea();

        Area newArea = game.getAreaForString(areaString);

        if(newArea != null && currentArea.isNeighbor(newArea)){
            currentPlayer.setArea(newArea);
            currentArea.removePlayer(currentPlayer);
            newArea.addPlayer(currentPlayer);
        }

        return true;
    }
    public boolean takeRole(String roleString) /* throws InvalidActionException */ {
        Player currentPlayer = game.getCurrentPlayer();

        if(!(currentPlayer.getCurrentArea() instanceof Set)) return false;
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

            return true;
        }
    }
    public boolean act() /* throws InvalidActionException */ {
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

    public boolean rehearse() /* throws InvalidActionException */ {
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
    public boolean upgrade(int desiredRank) /* throws InvalidActionException */ {
        Player currentPlayer = game.getCurrentPlayer();
        if(!(currentPlayer.getCurrentArea() instanceof CastingOffice)) return false;
        else {
            CastingOffice currentArea = (CastingOffice)currentPlayer.getCurrentArea();
            int[] desiredRankCost = game.getCostForRank(desiredRank);
            
            if(currentPlayer.getRank() < desiredRank){
                if(currentPlayer.canAfford(desiredRankCost[0],desiredRankCost[1])){
                    currentPlayer.buy(desiredRankCost[0],desiredRankCost[1]);
                    currentPlayer.setRank(desiredRank);
                }
            }


            return true;
        }
    }


	public void getCurrentPlayer() {
    
    }
    
    public Event[] end() {
        return new Event[0];
    }

    private int rollDie() {
        int min = 1;
        int max = 6;
        return (int) (Math.random() * (max - min + 1) + min);
    }

    // for when use ask who
    public void getNonPlayer(){

    }

    public void getCurrentArea(){

    }
}
