//import java.util.*;

class SceneCard {
    private String sceneName;
    private int difficulty;
    private int sceneBudget;
    private Role[] onCardRoles;
    private boolean isActive;

    // getters
    public String getSceneName() {
        return sceneName;
    }
    public int getDifficulty(){
        return difficulty;
    }
    public int getBudget() {
        return sceneBudget;
    }
    public boolean getIsActive() {
        return isActive;
    }
    public Role[] getOnCardRoles(){
      return onCardRoles;
    }

    public Role getRoleForString(String roleString){
        for(int i = 0; i < onCardRoles.length; i++){
            if(onCardRoles[i].getRoleName().equals(roleString)){
                  return onCardRoles[i];
            }
        }

        return null;
    }
}