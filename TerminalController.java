

public class TerminalController {
    private GameManager model;
    private ActionManager modelModifier;
    
    public TerminalController(GameManager model, ActionManager modelModifier) {
        this.model = model;
        this.modelModifier = modelModifier;

        gameLoop();
    }

    private void gameLoop(){

    }
}
