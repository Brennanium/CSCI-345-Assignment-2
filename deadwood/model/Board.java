package deadwood.model;

import deadwood.model.areas.*;
import deadwood.model.areas.Set;
import deadwood.XML.*;

import java.util.*;
import java.util.stream.*;
import java.util.ArrayList;

import org.w3c.dom.Document;

class Board {
    private final String DEFAULT_XML_FILEPATH = "./CSCI-345-Assignment-2/deadwood/XML/";


    private ArrayList<Area> areas;
    private ArrayList<SceneCard> undealtSceneCards;
    private ArrayList<SceneCard> dealtSceneCards;


    public Board() {
        getAreasFromXML();
        getScenesFromXML();
        dealtSceneCards = new ArrayList<SceneCard>();
    }

    private void getAreasFromXML() {
        Document boardDoc = null;
        XMLParser parsing = new XMLParser();
        
        try {
            boardDoc = parsing.getDocFromFile(DEFAULT_XML_FILEPATH + "board.xml");
            areas = parsing.readAreaData(boardDoc);

        }  catch(Exception e){
            System.out.println("Error = " + e);
        }
    }
    private void getScenesFromXML() {
        Document cardsDoc = null;
        XMLParser parsing = new XMLParser();
        
        try {
            cardsDoc = parsing.getDocFromFile(DEFAULT_XML_FILEPATH + "cards.xml");
            undealtSceneCards = parsing.readSceneData(cardsDoc);

        }  catch(Exception e){
            System.out.println("Error = " + e);
        }
    }
    public void addArea(Area newArea) {
        if(!isDuplicateArea(newArea)){
            areas.add(newArea);
        }
    }
    public void dealSceneCards() {
        ArrayList<Set> sets = getSets();

        if(sets.size() <= undealtSceneCards.size()){
            sets.forEach(a -> a.setSceneCard(getRandomSceneCard()));
        }        
    }
    public Area getAreaForString(String areaString) {
        return areas.stream()
            .filter(a -> a.getName().equals(areaString))
            .findAny()
            .orElse(null);
    }
    public int getNumberOfRemainingScenes() {
        return undealtSceneCards.size();
    }
    private ArrayList<Set> getSets() {
        List<Set> sets = areas.stream()
            .filter(a -> a instanceof Set)
            .map((Area a) -> (Set)a)
            .collect(Collectors.toList());
        return new ArrayList<Set>(sets);
    }
    private SceneCard getRandomSceneCard() {
        int randomIndex = (int)((Math.random()*undealtSceneCards.size()));
        SceneCard randomCard = undealtSceneCards.remove(randomIndex);
        dealtSceneCards.add(randomCard);
        return randomCard;
    }
    private boolean isDuplicateArea(Area area){
        return areas.contains(area) || 
            areas.stream()
                .anyMatch(a -> a.getName().equals(area.getName()));
    }


    public String toString() {
        return "";
    }
}