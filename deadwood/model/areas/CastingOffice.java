package deadwood.model.areas;

import java.util.ArrayList;
import java.util.HashMap;

import deadwood.model.*;


public class CastingOffice extends Area {
    public HashMap<Integer, Integer> moneyForRank;
    public HashMap<Integer, Integer> creditsForRank;
    
    public CastingOffice(HashMap<Integer, Integer> moneyForRank, HashMap<Integer, Integer> creditsForRank) {
        super("office");
        this.moneyForRank = moneyForRank;
        this.creditsForRank = creditsForRank;
    }

    public Integer getMoneyForRank(int rank) {
        return moneyForRank.get(rank);
    }
    
    public Integer getCreditsForRank(int rank) {
        return creditsForRank.get(rank);
    }

    public boolean playerCanAffordRank(Player p, int rank) throws InvalidActionException {
        Integer money = moneyForRank.get(rank);
        Integer credits = creditsForRank.get(rank);
        
        if(money == null || credits == null) {
            throw new InvalidActionException(rank + " is not a valid rank.");
        }
        if(p.getRank() >= rank) {
            throw new InvalidActionException("must upgrade to rank greater than current rank");
        }
        
        return false;    
    }

    public ArrayList<String> getRankUpgradeStrings() {
        ArrayList<String> strings = new ArrayList<String>();

        moneyForRank.forEach((k, v) -> {
            String str = String.format(
                "Rank %d: $%d, %d credits", k, v, creditsForRank.get(k));
            strings.add(str);
        });

        return strings;
    }

    public String getAreaSummary() {
        return "in Casting Office";
    }

}
