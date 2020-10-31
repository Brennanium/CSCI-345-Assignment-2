class Board {
    private Area[] areas;
    private SceneCard[] undealtSceneCards;
    private SceneCard[] dealtSceneCards;

    public boolean move(String roleString, Player p) {
        return true;
    }
    public void setupAreas() {

    }
    public void dealSceneCards() {

    }
    public Area getAreaForString(String areaString) {
        Area area = searchArea(areaString);

        return area;
    }
    public int getNumberOfRemainingScenes() {
        return 0;
    }
    public String toString() {
        return "";
    }
    private void shuffleSceneCards() {

    }
    private Area searchArea(String areaString) {
        return new Area();
    }
}