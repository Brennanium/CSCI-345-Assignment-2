package deadwood;

import java.util.ArrayList;
import java.util.Scanner;

import deadwood.model.*;
import deadwood.model.areas.*;
import deadwood.model.events.EndGameEvent;
import deadwood.model.events.Event;

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
        String userInput = "";      
        
        while(true) {
            if(userInput.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.print(" > ");
            userInput = sc.nextLine();        
            if(dealWithUserInput(userInput)){
                break;
            }   
        }
        System.out.println("\n\nThanks for playing!  Come back to play soon!");
        sc.close();
    }

    private boolean dealWithUserInput(String input) {
        input = input.toLowerCase();

        boolean gameOver = false;
        ArrayList<Event> events;
        try {
            if(input.equals("Active player?")){
                System.out.println(
                    model.getCurrentPlayer()
                );
            } else if(input.equals("who")){
                System.out.println(
                    model.getCurrentPlayer()
                );
            } else if(input.equals("quit")){
                gameOver = false;
            } else if(input.equals("help")){
                printHelp();
            } else if(input.equals("where everyone")){
                model.getPlayers().stream()
                    .forEach(p -> {
                        System.out.print(p.getName() + " ");
                        System.out.println(p.getCurrentArea().getAreaSummary());
                    });
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
                    model.rehearse() ? 
                        "Rehearsal was successful!  You get one more practice chip." 
                        :
                        ""
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
            } else if(input.equals("list areas")){
                model.getAreas().stream()
                    .forEach(a -> {
                        System.out.print(a.getName());
                        if(a instanceof Set) {
                            System.out.print(
                                ((Set)a).hasActiveScene() ? 
                                "" : 
                                " (WRAPPED)");
                        }
                        System.out.println();
                    });
            } else if(input.equals("move")){ 
                model.getCurrentNeighbors()
                    .forEach(n -> System.out.println(n.getName()));
            } else if(input.equals("scene")){
                System.out.println(
                    model.getScene()
                );
            } else if(input.matches("upgrade (.+)")){
                try {
                    int rank = Integer.parseInt(input.substring(7, input.length()).trim());
                    System.out.println(model.upgrade(rank));
                } catch(NumberFormatException numExc) {
                    System.out.println("Invalid number.");
                }
            } else if(input.matches("move (.+)")){
                String place = input;
                String name = place.substring(5, place.length()).trim();
                model.move(name);
            } else if(input.matches("work (.+)")){
                String place = input;
                String name = place.substring(5, place.length()).trim();
                model.takeRole(name);
            } else if(input.equals("end")){
                events = model.end();
                for(Event ev : events) {
                    if(ev != null) {
                        System.out.println(ev);
                        if(ev instanceof EndGameEvent) {
                            gameOver = true;
                        }
                    }
                }
            } else {
                System.out.println("Invalid command");
            }
        } catch(InvalidActionException exc) {
                System.out.println("Invalid Action: " + exc.getReason());
        }

        return gameOver;
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
                players.add(new Player(playerName));
            } else {
                System.out.println("Choose another name!");
                i--;
            }
        }
        return new ActionManager(players);
    }

    private void printHelp(){
        String help = "- move (string)\n" + 
            "    move to specified location\n" + 
            "- move\n" +
            "    list possible locations to move to\n" +
            "- act\n" +
            "    act on a role\n" + 
            "- work (string)\n" +
            "    take a specified role\n" + 
            "- rehearse\n" +
            "    rehearse your role\n" +
            "- upgrade (int)\n" +
            "    upgrade player to specified level\n" +
            "- scene\n" +
            "    list details about a scene including roles\n" +
            "- end\n" +
            "    end a player's turn\n" +
            "- active player?\n" +
            "    list details about active player\n" +
            "- list roles\n" +
            "    list details of roles\n" + 
            "- ist areas\n" +
            "    list names of all areas on board\n" +
            "- list ranks\n" +
            "    list availible ranks to upgrade to\n" + 
            "- list players\n" +
            "    list details of all players\n" +
            "- role\n" +
            "    lists deatils of the role of the current player\n" + 
            "- who\n" +
            "    list current player's details\n" + 
            "- where\n" + 
            "    list details about your current location as well as your neighbor area names\n" +
            "- where everyone\n" + 
            "    list the areas of all the players\n";
        System.out.println(help);
    }
    
}

