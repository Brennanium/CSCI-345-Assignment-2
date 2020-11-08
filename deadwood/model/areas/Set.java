package deadwood.model.areas;

import java.util.ArrayList;

import deadwood.model.*;

public class Set extends Area{
    private Role[] offCardRoles;
    private int shotTokenCount;
    private SceneCard scene;

    public Set(String name, int shotTokenCount, Role[] roles) {
        super(name);
        
        this.shotTokenCount = shotTokenCount;
        offCardRoles = roles;
    }

    public Set(){
        super();
    }


    public int getShotTokenCount() {
        return shotTokenCount;
    }
    public Role[] getOffCardRoles(){
        return offCardRoles;
    }

    public void removeShotToken(){
        shotTokenCount--;
    }

    public int getBudget() {
        return scene.getBudget();
    }

    public void setSceneCard(SceneCard scene){
        this.scene = scene;
    }
   
   public Role getRoleForString(String roleString) { 
     for(int i = 0; i < offCardRoles.length; i++){
        if(offCardRoles[i].getRoleName().equals(roleString)){
              return offCardRoles[i];
        }
     }

     return scene.getRoleForString(roleString);
   }

   public boolean isRoleFree(Role role){
    for(int i = 0; i < occupants.size(); i++){
        if(occupants.get(i).getRole() == role){
            return false;
        }
    }

    return true;
}
}
