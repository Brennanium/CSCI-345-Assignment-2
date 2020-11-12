package deadwood.model.areas;

import java.util.*;
import java.util.stream.*;

import deadwood.model.*;
import deadwood.model.events.*;

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

    public boolean hasActiveScene(){
        return scene.getIsActive();
    }

    public EndSceneEvent checkWrapScene(){
        if(scene.getIsActive() && shotTokenCount == 0) {
            return wrapScene();
        } else { 
            return null;
        }
    }
    
    private EndSceneEvent wrapScene(){
        ArrayList<Player> offCard = getPlayersWorkingOffCard();
        ArrayList<Player> onCard = getPlayersWorkingOnCard();
        HashMap<Player,Integer> bonusForPlayer = new HashMap<Player,Integer>();

        if(onCard.size() > 0) {
            ArrayList<Integer> dice = new ArrayList<Integer>();
            for(int i = 0; i < scene.getBudget(); i++) {
                dice.add(rollDie());
            } 
            Collections.sort(dice, new Comparator<Integer>(){
                public int compare(Integer i1, Integer i2){
                    return i2 - i1;
                }
            });
            Collections.sort(onCard, new Comparator<Player>(){
                public int compare(Player p1, Player p2){
                    return p2.getRole().getRank() - p1.getRole().getRank();
                }
            });

            //pay on card roles
            Player player;
            int bonus;
            for(int i = 0; i < dice.size(); i++) {
                player = onCard.get(i % onCard.size());
                bonus = dice.get(i);
                player.pay(bonus, 0);
                bonusForPlayer.put(player, bonus);
            } 

            //pay off card roles if there are any
            if(offCard.size() > 0) {
                offCard.forEach(p -> {
                    int bns = p.getRole().getRank();
                    p.pay(bns, 0);
                    bonusForPlayer.put(p, bns);
                });
            }
        }

        //remove everyone's roles
        //add successful scene #############
        occupants.forEach(p -> p.setRole(null));
        
        //kill the scene
        scene.wrapScene();

        offCard.addAll(onCard);

        return new EndSceneEvent(offCard, bonusForPlayer, this, scene);
    }

    public ArrayList<Player> getPlayersWorkingOnCard() {
        List<Player> working = occupants.stream()
            .filter(p -> p.getRole() != null && p.getRole().checkOnCard())
            .collect(Collectors.toList());
                    
        return new ArrayList<Player>(working);
    }
    
    public ArrayList<Player> getPlayersWorkingOffCard() {
        List<Player> working = occupants.stream()
            .filter(p -> p.getRole() != null && !p.getRole().checkOnCard())
            .collect(Collectors.toList());
                    
        return new ArrayList<Player>(working);
    }
    
    public String getAreaSummary() {
        StringBuffer sb  = new StringBuffer();
        if(scene != null || scene.getIsActive()){
            sb.append(String.format(
                "in %s shooting %s.%n", getName(), scene.toString()));
        } else {
            sb.append(String.format(
                "in %s not shooting anything at the moment.%n", getName()));
        }
        sb.append("Neighboring areas: \n");
        getNeighbors().forEach(b -> sb.append("  " + b.toString() + "\n"));
        return sb.toString();
    }
    
    public String getRolesInfo(){
        String str = "";
        if(scene != null && scene.getIsActive() == true){
            str = "On card roles: \n";
            for(Role r : scene.getOnCardRoles()) {
                str += r + " " + (isRoleFree(r) ? "\n" : "  (TAKEN)\n\n");
            }
            str += "Off card roles: \n";
            for(Role r : offCardRoles) {
                str += r + " " + (isRoleFree(r) ? "\n" : "  (TAKEN)\n\n");
            }
        } else {
            str = String.format
                ("There is no active scene card in %s.%n", getName());
        }
        return str;
    }

    public String getSceneInfo(){ 
        String str =  "";
        if(scene != null && scene.getIsActive() == true){
            str = String.format
                ("Area Name: %s %nScene Name: %s %nScene Budget: %d %nShot Token: %d %n", 
                getName(), //area name
                scene.getSceneName(), 
                scene.getBudget(), 
                shotTokenCount);
            str += getRolesInfo();
        } else {
            str= String.format
                ("There is no active scene card in %s.%n", getName());
        }
        return str;
    }

    private int rollDie() {
        int min = 1;
        int max = 6;
        return (int) (Math.random() * (max - min + 1) + min);
    }
}