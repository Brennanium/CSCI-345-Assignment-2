import java.util.ArrayList;

class Area {
    private String areaName;
    private ArrayList<Player> occupants;
    private Area[] neighbors;
    private Role[] offCardRoles;
    private int shotTokenCount;
    private boolean isSet;

    public Area() {

    }

    public boolean isNeighbor(Area area) {
        return true;
    }
    public boolean isCastingOffice(){
        return false;
    }
    public boolean checkArea() {
        return true;
    }
    public void addPlayer(Player p) {

    }
    public void removePlayer(Player p) {

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

    }
    public Role getRoleForString(String roleString) {
        return new Role();
    }
    public boolean isRoleFree(Role role){
        return true;
    }
}