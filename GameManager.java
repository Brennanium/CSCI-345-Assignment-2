class GameManager {
    private Board board;
    private int countDay;

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
        return board.getAreaForString(areaString);
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