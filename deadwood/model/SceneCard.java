package deadwood.model;

public class SceneCard {
    private String sceneName;
    private int sceneBudget;
    private Role[] onCardRoles;
    private boolean isActive;
    private int sceneNumber;
    private String sceneDescr;

    public SceneCard(String name, int number, int budget, String descr){
        sceneName = name;
        sceneNumber = number;
        sceneBudget = budget;
        sceneDescr = descr;
    }

    // getters
    public String getSceneName() {
        return sceneName;
    }
    public int getsceneNumber(){
        return sceneNumber;
    }
    public String getDescr(){
        return sceneDescr;
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