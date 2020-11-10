package deadwood.model.events;

import deadwood.model.*;
import deadwood.model.areas.*;

public class MoveEvent extends Event {
    private Player affectedPlayer;
    private Area startArea;
    private Area endArea;

    public MoveEvent(Player affectedPlayer,Area startArea,Area endArea){
        this.affectedPlayer = affectedPlayer;
        this.startArea = startArea;
        this.endArea = endArea;
    }


    public String toString(){
        return "";
    }
}
