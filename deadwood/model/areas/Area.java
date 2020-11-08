package deadwood.model.areas;

import deadwood.model.*;

import java.util.ArrayList;

public abstract class Area {
    private String areaName;
    private ArrayList<Player> occupants;
    private ArrayList<Area> neighbors;
    private boolean isSet;

    public Area(String name) {
        
    }

    public Area(){
        
    }

    // getters
    public String getName() {
        return areaName;
    }
    public boolean getIsSet() {
        return isSet;
    }

    public void setNeighbors(ArrayList<Area> neighbors){
        this.neighbors = neighbors;
    }
    
    public boolean isNeighbor(Area area) {
        return neighbors.contains(area);
    }
    
    public boolean isCastingOffice(){
        return areaName.equals("Casting Office");
    }
    public void addPlayer(Player p) {
        occupants.add(p);
    }
    
    public void removePlayer(Player p) {
        occupants.remove(p);
    }
    
    public boolean isRoleFree(Role role){
        for(int i = 0; i < occupants.size(); i++){
            if(occupants.get(i).getRole() == role){
                return false;
            }
        }

        return true;
    }
}