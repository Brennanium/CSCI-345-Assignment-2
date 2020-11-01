import java.util.ArrayList;

class Area {
    private String areaName;
    private ArrayList<Player> occupants;
    private Area[] neighbors;
    private Role[] offCardRoles;
    private int shotTokenCount;
    private boolean isSet;
    private boolean isNeighbor;
    private boolean roleFree;

    public Area() {

    }

    public boolean isNeighbor(Area area) {
      //ArrayList <String> neighbor = new ArrayList<String>();
      
        if(neighbor.get(i).contains(Area)){
            isNeighbor = true;
         } else {
            isNeighbor = false;
         }
      }
      return isNeighbor;
    }
    
    public boolean isCastingOffice(){
        return false;
    }
    public boolean checkArea() {
        return true;
    }
    public void addPlayer(Player p) {
        ArrayList<Player> occupants = new ArrayList<Player>();
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
      
        return new Role();
    }
    
    public boolean isRoleFree(Role role){
      //ArrayList <String> roleList = new ArrayList<String>();
      
      for(int i = 0; i < role.size(); i++){
         if(p.getRole.get(i).contains(p.getRole())){
            roleFree = true; 
         } else {
            roleFree = false;
         }
      }
        return roleFree;
    }
}