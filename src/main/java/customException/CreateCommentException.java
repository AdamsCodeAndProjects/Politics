package customException;

public class CreateCommentException extends RuntimeException{
    public CreateCommentException(String message) {
        super(message);
    }
}
