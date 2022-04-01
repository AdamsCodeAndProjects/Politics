package customException;

public class TooShort extends RuntimeException {
    public TooShort(String message) {
        super(message);
    }
}
