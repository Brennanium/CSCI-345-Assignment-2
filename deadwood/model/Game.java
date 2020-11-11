//Brennan

package deadwood.model;

import java.util.*;
import java.util.stream.*;

import deadwood.model.areas.*;
import deadwood.model.events.*;


public class Game {
    private ArrayList<Player> players;
    private ArrayList<Player> playersInTurnOrder;
    private Player currentPlayer;
    private Board board;
    private int countDay;

    public Game(ArrayList<Player> players) {
        this.players = players;

        List<Player> playersCopy = players.stream()
            .map(p -> p)
            .collect(Collectors.toList());
        playersInTurnOrder = new ArrayList<Player>(playersCopy);
        Collections.shuffle(playersInTurnOrder);

        board = Board.getInstance();

        setNextPlayer();

        players.stream()
            .forEach(p -> p.setArea(getAreaForString("trailer")));

    }

    public void setNextPlayer() {
        if(currentPlayer == null) {
            currentPlayer = playersInTurnOrder.get(0);
            return;
        } 
        int currentIndex = playersInTurnOrder.indexOf(currentPlayer);
        int nextIndex = (currentIndex + 1) % players.size();

        currentPlayer = playersInTurnOrder.get(nextIndex);
    }

    public EndSceneEvent endSceneCheck() {
        /* if() {
            wrapDay();
        } */
        
        return null;/* new EndSceneEvent(players, currentPlayer.getCurrentArea(), new SceneCard()); */

    }

    public EndDayEvent endDayCheck() {
        /* if() {
            wrapDay();
        } */
        
        return null;/* new EndDayEvent(currentPlayer, currentPlayer.getCurrentArea(), new Role()); */

    }

    private void wrapDay() {

    }

    public EndGameEvent endGameCheck() {
        /* if() {
            wrapGame();
        } */
        
        return null;
    }

    private void wrapGame() {

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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Area> getPlayerAreas() {
        List<Area> playerAreas = players.stream()
            .map(p -> p.getCurrentArea())
            .collect(Collectors.toList());
        return new ArrayList<Area>(playerAreas);
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