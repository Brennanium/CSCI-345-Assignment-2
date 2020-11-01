import java.util.*;

class SceneCard {
    private String sceneName;
    private int difficulty;
    private int sceneValue;
    private Role[] onCardRoles;
    private boolean isActive;

    public boolean getIsActive() {
        return false;
    }

    public Role[] getAvailRole(){
        return new Role[0];
    }

    public Player[] getActor(){
        return new Player[0];
    }

    public int getDifficulty(){
        return difficulty;
    }
    
    public Role[] getOnCardRoles(){
      return onCardRoles;
    }
}