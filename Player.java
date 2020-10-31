import java.io.*;
import java.util.*;

public class Player{

    private String name;
    private int rank;
    private Role role;
    private int dollars;
    private int credits;
    private int successfulScenes;
    private Area currentArea;
    private int countRehearse;
    private int practiceChips;

    public void setRole(Role roleName){

    }
    public Role getRole(){
        return role;
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int newRank){
        rank = newRank;
    }
    public int getPracticeChips() {
        return 0;
    }
    public void addPracticeChip(){

    }
    public void resetPracticeChips(){

    }
    public void setArea(Area areaName){
        
    }
    public void pay(int dollars, int credits) {
        this.dollars += dollars;
        this.credits += credits;
    }
    public boolean canAfford(int dollars, int credits) {
        return this.dollars >= dollars && this.credits >= credits;
    }
    public void buy(int dollars, int credits) {
        this.dollars -= dollars;
        this.credits -= credits;
    }
    public Area getCurrentArea() {
        return currentArea;
    }

    public boolean isRoleValid(Role checkRole){
        return true;
    }
}