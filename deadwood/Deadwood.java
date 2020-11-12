package deadwood;

public class Deadwood {

    public static void main(String[] args){
        if(args.length > 0) {
            new TerminalController(Integer.parseInt(args[0]));
        } else {
            new TerminalController();
        }
        
    }
}
