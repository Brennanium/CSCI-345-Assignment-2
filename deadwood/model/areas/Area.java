package deadwood.model.areas;

import deadwood.model.*;

import java.util.ArrayList;

public abstract class Area {
    private String areaName;
    private ArrayList<Player> occupants;
    private ArrayList<Area> neighbors;
    private Role[] offCardRoles;
    private int shotTokenCount;
    private boolean isSet;
    private SceneCard scene;

    public Area(String name, ArrayList<Area> neighbors, Role[] roles) {

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
    public int getBudget() {
        return scene.getBudget();
    }
    public int getShotTokenCount() {
        return shotTokenCount;
    }
    public Role[] getOffCardRoles(){
        return offCardRoles;
    }

    public void setSceneCard(SceneCard scene){
        this.scene = scene;
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
    
    public void removeShotToken(){
         shotTokenCount--;
    }
    
    public Role getRoleForString(String roleString) { 
      for(int i = 0; i < offCardRoles.length; i++){
         if(offCardRoles[i].getRoleName().equals(roleString)){
               return offCardRoles[i];
         }
      }

      return scene.getRoleForString(roleString);
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