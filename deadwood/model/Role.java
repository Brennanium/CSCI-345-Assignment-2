package deadwood.model;

public class Role {
    private int rank;
    private String roleName;
    private boolean onCard;
    private String description;

    /**
     * 
     * @param roleName
     * @param rank
     * @param description
     * @param onCard
     */
    public Role(String roleName, int rank, String description, boolean onCard) {
        this.roleName = roleName;
        this.rank = rank;
        this.description = description;
        this.onCard = onCard;
    }

    /**
     * 
     * @return boolean
     */
	public boolean checkOnCard(){
         return onCard;
    }

    /**
     * 
     * @return String
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * 
     * @param pRank
     * @return boolean
     */
    public boolean checkRank(Player pRank){
        if(pRank.getRank() >= rank){
            return true;       
        } else{
            return false;
        }
    }

    /**
     * 
     * @return int
     */
    public int getRank() {
        return rank;
    }
    
    /**
     * 
     * @return String
     */
    public String getRoleName() {
      return roleName;
    }

    /**
     * 
     * @return String
     */
    public String toString() {
        String str = String.format(
            "Role Name: %s %nRank: %d %nDescription: \"%s\" %n%s %n", 
            roleName, 
            rank, 
            description.trim(),
            onCard ? "Staring Role" : "Extra");
        return str;
    }
}