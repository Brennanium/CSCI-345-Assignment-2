package deadwood.model.events;

import deadwood.model.*;
import deadwood.model.areas.*;

public class TakeRoleEvent extends Event {
    private Player affectedPlayer;
    private Area currentArea;
    private Role newRole;

    public TakeRoleEvent(Player affectedPlayer,Area currentArea,Role newRole){
        this.affectedPlayer = affectedPlayer;
        this.currentArea = currentArea;
        this.newRole = newRole;
    }


    public String toString(){
        return "";
    }
}