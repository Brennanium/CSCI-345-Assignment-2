//Thannaree deal with user input; query the model correctly
//don't deal with printing yet

package deadwood;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import deadwood.model.*;
import deadwood.model.areas.*;

public class TerminalController {
    private ActionManager model;
    
    public TerminalController() {
        model = initModel();
        gameLoop();
    }

    private void gameLoop(){
        String userInput = "";
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext())
            userInput = sc.nextLine();
        while(!userInput.equals("end") && sc.hasNext()){           
            dealWithUserInput(userInput);    
            userInput = sc.nextLine();        
        }


        sc.close();
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

    //let user choose player names/colors
    //make a list of players from that information
    //pass it into a new ActionManager
    private ActionManager initModel(){        
        ArrayList<Player> players = new ArrayList<Player>();
        Scanner scan = new Scanner(System.in);
        System.out.println("How many player?");
        int numPlayer = scan.nextInt();
        for(int i = 0; i <= numPlayer; i++){
            System.out.println("What is your name?");
            String playerName = scan.nextLine();
            System.out.println("Choose one color");
            String playerColor = scan.nextLine();
            players.add(playerName, playerColor);
        }
        return new ActionManager(players);
    }
}

