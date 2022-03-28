package customException;

public class UnallowedSpaces extends RuntimeException {

    public UnallowedSpaces (String message) {
        super(message);
    }
}
