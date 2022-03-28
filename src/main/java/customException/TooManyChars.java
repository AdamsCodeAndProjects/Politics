package customException;

public class TooManyChars extends RuntimeException {

    public TooManyChars (String message) {
        super(message);
    }
}
