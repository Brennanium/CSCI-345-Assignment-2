//Brennan

package deadwood.model;

import java.util.ArrayList;

import deadwood.model.areas.*;


public class Game {
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Board board;
    private int countDay;

    public Game(ArrayList<Player> players) {
        this.players = players;

        board = Board.getInstance();
    }

    

    public boolean endDayCheck() {
        return true;
    }

    public Area getAreaForString(String areaString) {
        return board.getAreaForString(areaString);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public int getNumOfPlayers() {
        return players.size();
    }

    private void checkStates() {

    }
    private void wrapDay() {

    }
    private void wrapGame() {

    }


    public int[] getCostForRank(int rank) {
        /* switch(rank) {
            case 2: 
            return {4, 5};
            case 3: 
            arr = [10,10];
            return arr;
            case 4: 
            arr = [18, 15];
            return arr;
            case 5: 
            arr = [28, 20];
            return arr;
            case 6: 
            arr = [40, 25];
            return arr;
            default:
        } */
        return new int[2];
    }
}