package customException;

public class OnlyNumbers extends RuntimeException {
    public OnlyNumbers(String message) {
        super(message);
    }
}
