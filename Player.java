import java.io.*;
import java.util.*;

public class Player{

    private String name;
    private int pRank;
    private Role role;
    private int dollars;
    private int credits;
    private int successfulScenes;
    private Area currentArea;
    private int countRehearse;
    private int practiceChips;

    public void setRole(Role roleName){
      this.role = roleName;
    }
    
    public Role getRole(){
        return role;
    }
    
    public int getRank() {
        return pRank;
    }
    
    public void setRank(int newRank){
        pRank = newRank;
    }
    
    public int getPracticeChips() {
        return 0;
    }
    
    public void addPracticeChip(){
         this.practiceChips += practiceChips;    
    }
    
    public void resetPracticeChips(){
         this.practiceChips = 0;  
    }
    
    public void setArea(Area areaName){
         this.currentArea = areaName;    
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

    public boolean isRoleValid(Role checkRank){
      if(pRank >= checkRank.getRank()){
         return true;
      } else {
         return false;
      }
   }   
}