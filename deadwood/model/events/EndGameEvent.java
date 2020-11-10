package deadwood.model.events;

import java.util.*;

import deadwood.model.*;
import deadwood.model.areas.*;

public class EndGameEvent extends Event {
    private ArrayList<Player> players;
    private Area currentArea;
    private Role newRole;

    public EndGameEvent(ArrayList<Player> players, Area currentArea,Role newRole){
        this.players = players;
        this.currentArea = currentArea;
        this.newRole = newRole;
    }

    private void sortPlayers(){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player p1, Player p2){
                return p2.getCurrentScore() - p1.getCurrentScore();
            }
        });
    }

    public String toString(){
        sortPlayers();

        StringBuffer sb = new StringBuffer("Game Over! \n Scores:\n");
        int i = 1;
        for(Player p : players) {
            sb.append(String.format(
                "", "args")
            );
        }
        return sb.toString();
    }
}