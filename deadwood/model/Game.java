//Brennan

package deadwood.model;

import deadwood.model.areas.*;
import deadwood.XML.*;

import java.util.ArrayList;

import org.w3c.dom.Document;

public class Game {
    private final int DEFAULT_NUM_OF_PLAYERS = 2;
    private final String DEFAULT_XML_FILEPATH = "[ default filepath ]";


    private Board board;
    private int countDay;

    public Game(int numberOfPlayers) {
        initFromXML();
    }

    public Game() {
        
    }

    private void initFromXML() {
        Document doc1 = null;
        Document doc2 = null;
        XMLParser parsing = new XMLParser();
        ArrayList<SceneCard> scenes;
        ArrayList<Area> areas;  
        
        try {
            doc1 = parsing.getDocFromFile("./CSCI-345-Assignment-2/deadwood/XML/cards.xml");
            scenes = parsing.readSceneData(doc1);

            doc2 = parsing.getDocFromFile("./CSCI-345-Assignment-2/deadwood/XML/board.xml");
            areas = parsing.readAreaData(doc2);

        }  catch(Exception e){
            System.out.println("Error = " + e);
        }
    }

    public boolean endDayCheck() {
        return true;
    }
    public void constructScenes() {

    }
    public void constructBoard() {

    }
    private int[] calcScore() {
        return new int[0];
    }    
    public void setScene() {

    }
    public Area getAreaForString(String areaString) {
        return new Set();
        
        //board.getAreaForString(areaString);
    }
    public Player getCurrentPlayer() {
        return new Player();
    }
    public Player[] getPlayerAreas() {
        return new Player[0];
    }
    
    private void checkStates() {

    }
    private void wrapDay() {

    }
    private void wrapGame() {

    }

    public int[] getCostForRank(int rank) {
        /* switch(rank) {
            case 2: 
            return {4, 5};
            case 3: 
            arr = [10,10];
            return arr;
            case 4: 
            arr = [18, 15];
            return arr;
            case 5: 
            arr = [28, 20];
            return arr;
            case 6: 
            arr = [40, 25];
            return arr;
            default:
        } */
        return new int[2];
    }
}