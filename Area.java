import java.util.ArrayList;

class Area {
    private String areaName;
    private ArrayList<Player> occupants;
    private Area[] neighbors;
    private Role[] offCardRoles;
    private int shotTokenCount;

    public Area() {

    }

    public boolean checkIsNeighbor(Area area) {
        return true;
    }
    public boolean checkArea() {
        return true;
    }
    public void addPlayer(Player p) {

    }
    public void removePlayer(Player p) {

    }
    public boolean takeRole(String roleName, Player p) {
        return true;
    }
    public boolean act(String roleName, Player p) {
        return true;
    }
    public int getShotTokenCount() {
        return 0;
    }
    public Role searchRole(String roleName) {
        return new Role();
    }
}