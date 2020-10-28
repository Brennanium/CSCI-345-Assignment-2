import java.util.*;

public class Role {
    private int rank;
    private String roleName;
    private boolean availability;
    private double credit;
    private int level;
    private boolean onCard;

    public boolean checkAvailibity(){
        boolean availibility = true;
        return availibility;
    }

    public boolean checkOnCard(){
        boolean onCard = true;
        return onCard;
    }

    public boolean workSucceed(){
        boolean succeed = true;
        return succeed;
    }

    public int getLevel(){
        int level = 0;
        return level;
    }

    public boolean checkRank(Player playerInfo){
        boolean validRank = true;
        return validRank;
    }
}