package Bespoke.BespokeBids.exception;

public class NotCorrespondingIdException extends RuntimeException {
    public NotCorrespondingIdException() {
        super();
    }

    public NotCorrespondingIdException(String message) {
        super(message);
    }

    public NotCorrespondingIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotCorrespondingIdException(Throwable cause) {
        super(cause);
    }


}
