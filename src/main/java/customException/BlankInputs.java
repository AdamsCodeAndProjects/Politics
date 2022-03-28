package customException;

public class BlankInputs extends RuntimeException {

    public BlankInputs (String message) {
        super(message);
    }
}
