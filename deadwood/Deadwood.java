package deadwood;

import deadwood.model.*;

public class Deadwood {
    static void main(String[] args){
        Game model;
        if(args.length > 0) {
            model = new Game(Integer.parseInt(args[0]));
        } else {
            model = new Game();
        }
        
        ActionManager modelModifier = new ActionManager(model);
        
        new TerminalController(model, modelModifier);
    }
}
