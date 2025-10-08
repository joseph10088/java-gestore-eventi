package CustomException;

public class PrenotaException extends Exception {

    public PrenotaException(String messaggio) {
        super(messaggio);
    }
    
    public PrenotaException(){
        super();
    }
}
