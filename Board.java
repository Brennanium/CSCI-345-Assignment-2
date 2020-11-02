import java.util.*;
import java.util.stream.*;

class Board {
    private ArrayList<Area> areas;
    private ArrayList<SceneCard> undealtSceneCards;
    private ArrayList<SceneCard> dealtSceneCards;


    public void addArea(Area newArea) {
        if(!isDuplicateArea(newArea)){
            areas.add(newArea);
        }
    }
    public void dealSceneCards() {
        ArrayList<Area> sets = getSets();

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
    private ArrayList<Area> getSets() {
        List<Area> sets = areas.stream()
            .filter(a -> a.getIsSet())
            .collect(Collectors.toList());
        return new ArrayList<Area>(sets);
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