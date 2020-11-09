//Thannaree deal with user input; query the model correctly
//don't deal with printing yet

package deadwood;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import deadwood.model.*;
import deadwood.model.areas;

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
        while(!userInput.equals("end") && sc.hasNext()){           
            dealWithUserInput(userInput);            
        }
    }

    private void dealWithUserInput(String input) {
        if(input.equals("Active player?")){
            model.getCurrentPlayer();
        } else if(input.equals("who")){
            model.getNonPlayer();
        } else if(input.equals("where")){
            model.getCurrentArea();
        } else if(input.equals("act")){
            model.act();
        } else if(input.matches("move(.*)")){
            String place = input;
            String command = place.substring(0,5);
            String name = place.substring(6, place.length()-1);
            model.move("name");
        } else if(input.matches("work(.*)")){
            String place = input;
            String command = place.substring(0,5);
            String name = place.substring(6, place.length()-1);
            model.takeRole("name");
        }
    }
       
    public static void main(String args[]){
    }
}

