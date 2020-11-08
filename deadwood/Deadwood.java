package deadwood;

import deadwood.model.*;

public class Deadwood {
    static void main(String[] args){
        ActionManager model;
        if(args.length > 0) {
            model = new ActionManager(Integer.parseInt(args[0]));
        } else {
            model = new ActionManager(2);
        }
        
        new TerminalController(model);
    }
}
