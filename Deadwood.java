public class Deadwood {
    static void main(String[] args){
        GameManager model;
        if(args.length > 0) {
            model = new GameManager(Integer.parseInt(args[0]));
        } else {
            model = new GameManager();
        }
        
        ActionManager modelModifier = new ActionManager(model);
        
        new TerminalController(model, modelModifier);
    }
}
