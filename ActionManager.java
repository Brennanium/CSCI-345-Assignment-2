import java.util.*;

public class ActionManager {
    private GameManager game;
    private TerminalController controller;
    
    
    
    public boolean move(String areaString) {
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
    public boolean takeRole(String roleString) {
        Player currentPlayer = game.getCurrentPlayer();
        Area currentArea = currentPlayer.getCurrentArea();
        Role currentRole = currentPlayer.getRole();

        Role newRole = currentArea.getRoleForString(roleString);

        if(newRole != null && currentRole == null){
            if(currentArea.isRoleFree(newRole)){
                if(currentPlayer.isRoleValid(newRole)){
                    currentPlayer.setRole(newRole);
                }
            }
        }

        return true;
    }
    public boolean act() {
        Player currentPlayer = game.getCurrentPlayer();
        Area currentArea = currentPlayer.getCurrentArea();
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

    public boolean rehearse() {
        Player currentPlayer = game.getCurrentPlayer();
        Area currentArea = currentPlayer.getCurrentArea();
        Role currentRole = currentPlayer.getRole();
        
        if(currentRole != null) {
            if(currentPlayer.getPracticeChips() < currentArea.getBudget()) {
                currentPlayer.addPracticeChip();
            }
        }


        return true;
    }
    public boolean upgrade(int desiredRank) {
        Player currentPlayer = game.getCurrentPlayer();
        Area currentArea = currentPlayer.getCurrentArea();
        int[] desiredRankCost = game.getCostForRank(desiredRank);

        if(currentArea.isCastingOffice()){
            if(currentPlayer.getRank() < desiredRank){
                if(currentPlayer.canAfford(desiredRankCost[0],desiredRankCost[1])){
                    currentPlayer.buy(desiredRankCost[0],desiredRankCost[1]);
                    currentPlayer.setRank(desiredRank);
                }
            }
        }


        return true;
    }

    private int rollDie() {
        int min = 1;
        int max = 6;
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
