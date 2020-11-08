package deadwood.model.areas;

import deadwood.model.*;

import java.util.ArrayList;

public abstract class Area {
    private String areaName;
    protected ArrayList<Player> occupants;
    private ArrayList<Area> neighbors;
    private boolean isSet;
/*     private int coordX;
    private int coordY;
    private int coordH;
    private int coordW; */

    public Area(String name, ArrayList<Area> neighbors, Role[] roles) {

    }

    public Area(String name) {
        areaName = name;
    }

    public Area() {

    }

/*     public Area(int x, int y, int h, int w){
        coordX = x;
        coordY = y;
        coordH = h;
        coordW = w;
    } */

    // getters
    public String getName() {
        return areaName;
    }
    public boolean getIsSet() {
        return isSet;
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

    public void setNeighbors(ArrayList<Area> neighbors){
        this.neighbors = neighbors;
    }
    
    public ArrayList<Area> getNeighbors(){
        return neighbors;
    }
    public void removePlayer(Player p) {
        occupants.remove(p);
    }

}