import java.util.*;

public class Role {
    private int rank;
    private String[] roleName;
    private double credit;
    private int level;
    private boolean onCard;
    private boolean validRank;

    public boolean checkOnCard(){
         return onCard;
    }
    
    public int getLevel(){
        return level;
    }
    
    public boolean checkRank(Player pRank){
        if(pRank.getRank() >= rank){
            return true;       
        } else{
            return false;
        }
    }

    public int getRank() {
        return rank;
    }
}