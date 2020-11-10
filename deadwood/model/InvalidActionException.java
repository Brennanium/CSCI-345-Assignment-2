package deadwood.model;

public class InvalidActionException extends Exception{
    private String reason;

    public InvalidActionException(String message) {
        super(message);
        reason = message;
    }

    public String getReason(){
        return reason;
    }
}
