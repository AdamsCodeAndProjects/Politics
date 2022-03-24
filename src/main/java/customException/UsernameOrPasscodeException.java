package customException;

public class UsernameOrPasscodeException extends RuntimeException{

    public UsernameOrPasscodeException (String message) {
        super(message);
    }
}
