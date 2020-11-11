package deadwood.model.areas;

import java.util.ArrayList;
import java.util.Collections;

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

    public String getAreaSummary() {
        String str = "";
        if(scene != null){
            str = String.format(
                "in %s shooting %s.", getName(), scene.toString());
        } else {
            str = String.format(
                "in %s not shooting anything at the moment.", getName());
        }
        return str;
    }
    
    public String getRolesInfo(){
        String str = "";
        if(scene != null && scene.getIsActive() == true){
            str = "On card roles: \n";
            for(Role r : scene.getOnCardRoles()) {
                str += r + " " + (isRoleFree(r) ? "\n" : "TAKEN\n");
            }
            str += "Off card roles: \n";
            for(Role r : offCardRoles) {
                str += r + " " + (isRoleFree(r) ? "\n" : "TAKEN\n");
            }
        } else {
            str = String.format
                ("There is no active scene card in %s.", getName());
        }
        return str;
    }

    public String getSceneInfo(){ 
        //make a sentence or list 
        String str =  "";
        if(scene != null && scene.getIsActive() == true){
            str = String.format
                ("Area Name: %s %n Scene Name: %s %n Scene Budget: %d %n %d %n", 
                getName(), //area name
                scene.getSceneName(), 
                scene.getBudget(), 
                shotTokenCount);
            str += getRolesInfo();
        } else {
            str= String.format
                ("There is no active scene card in %s.", getName());
        }
        return str;
    }
}