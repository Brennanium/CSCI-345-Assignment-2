package deadwood.model.events;

import deadwood.model.*;
import deadwood.model.areas.*;

public class PayEvent extends Event {
    private Player affectedPlayer;
    private Area startArea;
    private Area endArea;

    public PayEvent(Player affectedPlayer, int money, int credits){
        this.affectedPlayer = affectedPlayer;
        this.startArea = startArea;
        this.endArea = endArea;
    }


    public String toString(){
        return "";
    }
}

