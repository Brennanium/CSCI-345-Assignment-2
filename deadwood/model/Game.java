//Brennan

package deadwood.model;

import java.util.*;
import java.util.stream.*;

import deadwood.model.areas.*;
import deadwood.model.areas.Set;
import deadwood.model.events.*;


public class Game {
    private ArrayList<Player> players;
    private ArrayList<Player> playersInTurnOrder;
    private Player currentPlayer;
    private Board board;
    private int countDay;
    private boolean currentPlayerHasMoved;
    public boolean getHasMoved(){ return currentPlayerHasMoved; }
    public void hasMoved(){ currentPlayerHasMoved = true; }
    private boolean currentPlayerHasActed;
    public boolean getHasActed(){ return currentPlayerHasActed; }
    public void hasActed(){ currentPlayerHasActed = true; }
    private boolean currentPlayerHasRehearsed;
    public boolean getHasRehearsed(){ return currentPlayerHasRehearsed; }
    public void hasRehearsed(){ currentPlayerHasRehearsed = true; }
    private boolean currentPlayerHasUpgraded;
    public boolean getHasUpgraded(){ return currentPlayerHasUpgraded; }
    public void hasUpgraded(){ currentPlayerHasUpgraded = true; }
    private boolean currentPlayerHasTakenRole;
    public boolean getHasTakenRole(){ return currentPlayerHasTakenRole; }
    public void hasTakenRole(){ currentPlayerHasTakenRole = true; }

    public Game(ArrayList<Player> players) {
        this.players = players;

        List<Player> playersCopy = players.stream()
            .map(p -> p)
            .collect(Collectors.toList());
        playersInTurnOrder = new ArrayList<Player>(playersCopy);
        Collections.shuffle(playersInTurnOrder);

        board = Board.getInstance();
        board.dealSceneCards();

        initPlayers();
    }

    public void setNextPlayer() {
        if(currentPlayer == null) {
            currentPlayer = playersInTurnOrder.get(0);
            return;
        } 
        int currentIndex = playersInTurnOrder.indexOf(currentPlayer);
        int nextIndex = (currentIndex + 1) % players.size();

        currentPlayer = playersInTurnOrder.get(nextIndex);

        currentPlayerHasActed = false;
        currentPlayerHasMoved = false;
        currentPlayerHasRehearsed = false;
        currentPlayerHasUpgraded = false;
        currentPlayerHasTakenRole = false;
    }

    public EndSceneEvent endSceneCheck() {
        if(currentPlayer.getCurrentArea() instanceof Set) {
            Set currentArea = (Set)currentPlayer.getCurrentArea();

            return currentArea.checkWrapScene();
        }
        return null;
    }

    public EndDayEvent endDayCheck() {
        // The  day  is  over  when  there  is  only  one  scene  left.
        // This  last  scene  does  not  finish,  and  
        // there  is  no  further  payment  to  any-one  who  was  still  working  on  it.
        // Players return to the trailers to play the next day. 

        /* if() {
            wrapDay();
        } */
        
        return null;

    }

    private void wrapDay() {
        returnToTrailer();
        /* Return everyone’s die to the Trailers. 
        Remove the last scene card from the board.
        Deal ten new scenes onto the board, face down.
        Replace all the shot counters.  */
    }

    public EndGameEvent endGameCheck() {
        if(countDay == 0 || board.getNumberOfRemainingScenes() == 0) {
            return wrapGame();
        }
        
        return null;
    }

    private EndGameEvent wrapGame() {
        return null;
    }

    public void returnToTrailer(){
        players.stream()
            .forEach(p -> {
                p.getCurrentArea().removePlayer(p);
                Area trailers = getAreaForString("trailer");
                trailers.addPlayer(p);
                p.setArea(trailers);
            });
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

    public void initPlayers(){
        setNextPlayer();
        
        int startingRank;
        int startingCredits;
        switch(players.size()){
            case 4:
                startingRank = 1;
                startingCredits = 0;
                countDay = 4;
                break;
            case 5:
                startingRank = 1;
                startingCredits = 2;
                countDay = 4;
                break;
            case 6:
                startingRank = 1;
                startingCredits = 4;
                countDay = 4;
                break;
            case 7:
            case 8:
                startingRank = 2;
                startingCredits = 0;
                countDay = 4;
                break;
            default: 
                startingRank = 1;
                startingCredits = 0;
                countDay = 3;
                break;
        }
        players.stream()
            .forEach(p -> {
                Area trailers = getAreaForString("trailer");
                trailers.addPlayer(p);
                p.setArea(trailers);
                
                p.setRank(startingRank);
                p.pay(0, startingCredits);
            });
    }
}