package customException;

public class DuplicateUsername extends RuntimeException {

    public DuplicateUsername (String message) {
        super(message);
    }
}
