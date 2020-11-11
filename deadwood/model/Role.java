
package deadwood.model;


public class Role {
    private int rank;
    private String roleName;
    private boolean onCard;
    private String description;

    public Role(String roleName, int rank, String description, boolean onCard) {
        this.roleName = roleName;
        this.rank = rank;
        this.description = description;
        this.onCard = onCard;
    }

    public Role() {
	}

	public boolean checkOnCard(){
         return onCard;
    }

    public String getDescription() {
        return description;
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
    
    public String getRoleName() {
      return roleName;
    }

    public String toString() {
        //make a sentence or list
        String str = String.format(
            "Role Name: %s %n Rank: %d %n Description: \"%s\" %n", roleName, rank, description);
        return str;
    }
}