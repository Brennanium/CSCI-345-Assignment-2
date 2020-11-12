//Thannaree deal with user input; query the model correctly
//
//word on matches ignoring case

package deadwood;

import java.util.ArrayList;
import java.util.Scanner;

import deadwood.model.*;
import deadwood.model.areas.*;

public class TerminalController {
    private ActionManager model;
    private Integer numOfPlayers = null;
    Scanner sc;
    
    public TerminalController(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;

        sc = new Scanner(System.in);

        model = initModel();
        gameLoop();
    }

    public TerminalController() {
        sc = new Scanner(System.in);

        model = initModel();
        gameLoop();
    }

    private void gameLoop(){
        System.out.println("\n\nThe game has started!");
        System.out.println(model.getCurrentPlayer().getName() + 
            " has the first turn.");  
        //System.out.print(" > ");
        String userInput = "";
        //sc.nextLine();        
        
        while(true) {
            if(userInput.equalsIgnoreCase("quit")) {
                break;
            }
            //userInput = sc.nextLine();  
            System.out.print(" > ");
            //System.out.print("");
            userInput = sc.nextLine();        
            dealWithUserInput(userInput);    
        }
        sc.close();
    }

    private void dealWithUserInput(String input) {
        input = input.toLowerCase();

        try {
            if(input.equals("Active player?")){
                System.out.println(
                    model.getCurrentPlayer()
                );
            } else if(input.equals("who")){
                System.out.println(
                    model.getCurrentPlayer()
                );
            } else if(input.equals("where everyone")){
                model.getPlayerAreas().stream()
                    .forEach(a -> System.out.println(a.getAreaSummary()));
            } else if(input.equals("where")){
                System.out.println(
                    model.getCurrentArea().getAreaSummary()
                );
            } else if(input.equals("act")){
                System.out.println(
                    model.act()
                );
            } else if(input.equals("role")){
                System.out.println(
                    model.getCurrentRole()
                );
            } else if(input.equals("rehearse")){
                System.out.println(
                    model.rehearse()
                );
            } else if(input.equals("list roles")){ 
                model.getRoles().stream()
                    .forEach(r -> System.out.println(r));
            } else if(input.equals("list ranks")){
                model.getRanks().stream()
                    .forEach(s -> System.out.println(s));
            } else if(input.equals("list players")){
                model.getPlayers().stream()
                    .forEach(a -> System.out.println(a));
            } else if(input.equals("scene")){
                System.out.println(
                    model.getScene()
                );
            } else if(input.matches("upgrade (.+)")){
                try {
                    int rank = Integer.parseInt(input.substring(7, input.length()).trim());
                    model.upgrade(rank);
                } catch(NumberFormatException numExc) {
                    System.out.println("Invalid number.");
                }
            } else if(input.matches("move (.+)")){
                String place = input;
                //String command = place.substring(0,4);
                String name = place.substring(5, place.length()).trim();
                model.move(name);
            } else if(input.matches("work (.+)")){
                String place = input;
                //String command = place.substring(0,4);
                String name = place.substring(5, place.length()).trim();
                model.takeRole(name);
            } else if(input.equals("end")){
                model.end().stream()
                    .forEach(ev -> {
                        if(ev != null) System.out.println(ev);
                    });
            } else {
                System.out.println("Invalid command");
            }
        } catch(InvalidActionException exc) {
                System.out.println("Invalid Action: " + exc.getReason());
        }
    }

    //let user choose player names/colors
    //make a list of players from that information
    //pass it into a new ActionManager
    private ActionManager initModel(){  
        System.out.println("Welcome to Deadwood!");      
        ArrayList<Player> players = new ArrayList<Player>();

        if(numOfPlayers == null){
            System.out.println("How many players?");
            numOfPlayers = sc.nextInt();
        }
        if(numOfPlayers < 2 || numOfPlayers > 8){
            sc.close();
            throw new IllegalArgumentException("Invalid number of players.");
        }
        sc.nextLine();
        for(int i = 1; i <= numOfPlayers; i++){
            System.out.println("What is your name?");
            String playerName = sc.nextLine();
            if(!players.stream().anyMatch(p -> p.getName().equals(playerName))) { 
                /* System.out.println("Choose one color");
                String playerColor = scan.nextLine(); */
                players.add(new Player(playerName));
            } else {
                System.out.println("Choose another name!");
                i--;
            }
        }
        return new ActionManager(players);
    }



    
}

