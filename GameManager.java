class GameManager {
    private Board board;
    private PlayerManager playerManager;
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
    public PlayerInfo getCurrentPlayer() {
        return new PlayerInfo();
    }
    public Player[] getPlayerAreas() {
        return new Player[0];
    }
    public boolean move(String areaString) {
        return true;
    }
    public boolean takeRole(String roleString) {
        return true;
    }
    public boolean act() {
        return true;
    }
    public boolean rehearse() {
        return true;
    }
    public boolean upgrade() {
        return true;
    }
    public void endTurn() {

    }
    private void checkStates() {

    }
    private void wrapDay() {

    }
    private void wrapGame() {

    }
}