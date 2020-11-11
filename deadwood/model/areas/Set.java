package deadwood.model.areas;

import java.util.ArrayList;
import java.util.Collections;

import deadwood.model.*;

public class Set extends Area{
    private Role[] offCardRoles;
    private int shotTokenCount;
    private SceneCard scene;
    private Game game;
    private Player player;

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
        if(offCardRoles[i].getRoleName().equalsIgnoreCase(roleString)){
              return offCardRoles[i];
        }
     }

     return scene.getRoleForString(roleString);
   }

    //to do
    public ArrayList<Role> getRoles() {
        ArrayList<Role> list = new ArrayList<Role>();
        Collections.addAll(list, offCardRoles);
        if(scene != null) 
            Collections.addAll(list, scene.getOnCardRoles());
        return list;
    }

   public boolean isRoleFree(Role role){
    for(int i = 0; i < occupants.size(); i++){
        if(occupants.get(i).getRole() == role){
            return false;
        }
    }

    return true;
}

public String getSceneInfo(){ 
    /* if(sceneCardFlip == true){
        //display current player, no scene, no active scene, num player = ..
    } else {
        // scene name, budget, shot token left, other player in the same scene
    } */
    String str = String.format
        ("%s %d %d", scene.getSceneName(), scene.getBudget(), player.getShotToken());

    if(scene.getIsActive() == true){
        String strn = String.format
            ("%s %d", game.getCurrentPlayer(), game.getNumOfPlayers());
        return strn;
    }
    return str;
}
}
