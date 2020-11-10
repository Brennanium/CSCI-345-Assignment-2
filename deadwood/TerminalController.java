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
    
    public TerminalController(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
        model = initModel();
        gameLoop();
    }

    public TerminalController() {
        model = initModel();
        gameLoop();
    }

    private void gameLoop(){
        String userInput = "";
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext())
            userInput = sc.nextLine();
        while(!userInput.equalsIgnoreCase("quit") && sc.hasNext()) {
            dealWithUserInput(userInput);    
            userInput = sc.nextLine();        
        }


        sc.close();
    }

    private void dealWithUserInput(String input) {
        input = input.toLowerCase();
        
        /*  list roles, 
            list level, 
            scene: shot counter have left

            (list players) do later
            */
        try {
            if(input.equals("Active player?")){
                model.getCurrentPlayer();
            } else if(input.equals("who")){
                model.getNonPlayer();
            } else if(input.equals("where everyone")){
                model.getPlayerAreas();
            } else if(input.equals("where")){
                model.getCurrentArea();
            } else if(input.equals("act")){
                model.act();
            } else if(input.equals("role")){
                model.getCurrentRole();
            } else if(input.equals("rehearse")){
                model.rehearse();
            } else if(input.equals("list roles")){ 
                model.getRoles();
            } else if(input.equals("list levels")){
                model.getLevels();
            } else if(input.equals("list players")){
                model.getPlayers();
            } else if(input.equals("scene")){
                model.getScene();
            } else if(input.matches("upgrade (.+)")){
                try {
                    int rank = Integer.parseInt(input.substring(5, input.length()).trim());
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
                    .forEach(ev -> System.out.println(ev));
            } else {
                System.out.println("Invalid invalid command");
            }
        } catch(InvalidActionException exc) {
                System.out.println("Invalid Action: " + exc.getReason());
        }
    }

    //let user choose player names/colors
    //make a list of players from that information
    //pass it into a new ActionManager
    private ActionManager initModel(){        
        ArrayList<Player> players = new ArrayList<Player>();
        Scanner scan = new Scanner(System.in);
        if(numOfPlayers == null){
            System.out.println("How many player?");
            numOfPlayers = scan.nextInt();
        }
        if(numOfPlayers < 2 || numOfPlayers > 8){
            scan.close();
            throw new IllegalArgumentException("Invalid number of players.");
        }
        for(int i = 0; i <= numOfPlayers; i++){
            System.out.println("What is your name?");
            String playerName = scan.nextLine();
            if(!players.stream().anyMatch(p -> p.getName().equals(playerName))) { 
                /* System.out.println("Choose one color");
                String playerColor = scan.nextLine(); */
                players.add(new Player(playerName));
            } else {
                System.out.println("Choose another name!");
                i--;
            }
        }


        scan.close();
        return new ActionManager(players);
    }
}

