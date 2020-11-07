package deadwood;

import deadwood.model.*;

public class TerminalController {
    private Game model;
    private ActionManager modelModifier;
    
    public TerminalController(Game model, ActionManager modelModifier) {
        this.model = model;
        this.modelModifier = modelModifier;

        gameLoop();
    }

    private void gameLoop(){

    }
}
