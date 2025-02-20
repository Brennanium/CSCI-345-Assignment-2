package deadwood.model.events;

import java.util.*;

import deadwood.model.*;
import deadwood.model.areas.Set;

public class EndSceneEvent extends Event {
    private ArrayList<Player> players;
    public HashMap<Player,Integer> moneyForPlayer;
    private Set currentArea;
    private SceneCard scene;

    public EndSceneEvent(
        ArrayList<Player> players, 
        HashMap<Player,Integer> moneyForPlayer, 
        Set currentArea,
        SceneCard scene)
        {
        this.players = players;
        this.moneyForPlayer = moneyForPlayer;
        this.currentArea = currentArea;
        this.scene = scene;
    }

    private void sortPlayers(){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player p1, Player p2){
                return p2.getRank() - p1.getRank();
            }
        });
    }

    public String toString(){
        sortPlayers();

        StringBuffer sb = new StringBuffer(
            String.format("Scene %s in %s is wrapped!%n", scene.getSceneName(), currentArea.toString())
        );
        if(moneyForPlayer.size() > 0) {
            sb.append("Player bonuses:\n");
            players.forEach(p -> {
                Integer money = moneyForPlayer.get(p);
                if(money != null) {
                    sb.append(" " + p.getName() + ": $" + money + "\n");
                }
            });
        }
        
        return sb.toString();
    }
}