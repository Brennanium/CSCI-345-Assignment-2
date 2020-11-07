package deadwood.model;

import deadwood.model.areas.*;

public class Player{

    private String name;
    private int pRank;
    private Role role;
    private int dollars;
    private int credits;
    private int successfulScenes;
    private Area currentArea;
    private int practiceChips;

    public String getName() {
        return name;
    }
    public int getRank() {
        return pRank;
    }
    public Role getRole(){
        return role;
    }
    public int getDollars() {
        return dollars;
    }
    public int getCredits() {
        return credits;
    }
    public int getSuccessfulScenes() {
        return successfulScenes;
    }
    public Area getCurrentArea() {
        return currentArea;
    }
    public int getPracticeChips() {
        return practiceChips;
    }
    

    public void setRank(int newRank){
        pRank = newRank;
    }
    public void setRole(Role roleName){
        this.role = roleName;
    }
    public void addPracticeChip(){
         this.practiceChips++;    
    }
    // convenience method
    public void rehearse(){
        addPracticeChip();    
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
    
    public void wrapScene() {
        successfulScenes++;
    }
    public boolean canAfford(int dollars, int credits) {
        return this.dollars >= dollars && this.credits >= credits;
    }
    
    public void buy(int dollars, int credits) {
        this.dollars -= dollars;
        this.credits -= credits;
    }

    public boolean isRoleValid(Role checkRank){
      if(pRank >= checkRank.getRank()){
         return true;
      } else {
         return false;
      }
    }

    public String toString() {
        return "";
    }
}