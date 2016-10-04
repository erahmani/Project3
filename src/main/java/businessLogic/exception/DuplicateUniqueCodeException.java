package businessLogic.exception;

public class DuplicateUniqueCodeException extends RuntimeException {
    public DuplicateUniqueCodeException() {
    }

    public DuplicateUniqueCodeException(String message) {
        super(message);
    }
}
