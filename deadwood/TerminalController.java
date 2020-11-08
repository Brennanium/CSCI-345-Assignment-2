//Thannaree deal with user input; query the model correctly
//don't deal with printing yet

package deadwood;

import java.util.Scanner;

import deadwood.model.*;

public class TerminalController {
    private ActionManager model;
    
    public TerminalController(ActionManager model) {
        this.model = model;

        gameLoop();
    }

    private void gameLoop(){
        String userInput = "";
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext())
            userInput = sc.nextLine();
        while(!userInput.equals("quit") && sc.hasNext()){
            
            dealWithUserInput(userInput);
            
        }
    }
    
    private void dealWithUserInput(String input) {
        if(input.equals("who")){
            model.getCurrentPlayer();
        }
        if(input.matches("work \\w*")){
            model.takeRole("name of the role");
        }
    }
       
    //public static void main()
}

