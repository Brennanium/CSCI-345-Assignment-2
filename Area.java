import java.util.ArrayList;

class Area {
    private String areaName;
    private ArrayList<Player> occupants;
    private ArrayList<Area> neighbors;
    private Role[] offCardRoles;
    private int shotTokenCount;
    private boolean isSet;
    private boolean roleFree;
    private SceneCard scene;

    public Area() {

    }

    public boolean isNeighbor(Area area) {
        return neighbors.contains(area);
    }
    
    public boolean isCastingOffice(){
        return false;
    }
    public boolean checkArea() {
        return true;
    }
    public void addPlayer(Player p) {
        occupants.add(p);
    }
    
    public void removePlayer(Player p) {
        occupants.remove(p);
    }
    
    
    public int getShotTokenCount() {
        return 0;
    }
    
    public boolean getIsSet() {
        return isSet;
    }
    
    public int getBudget() {
        return 0;
    }
    
    public void removeShotToken(){
         shotTokenCount --;
    }
    
    public Role getRoleForString(String roleString) { 
      for(int i = 0; i < offCardRoles.length; i++){
         if(offCardRoles[i].getRoleName().equals(roleString)){
               return offCardRoles[i];
         }
      }
      return null;
    }
    
    public Role[] getOffCardRoles(){
      return offCardRoles;
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