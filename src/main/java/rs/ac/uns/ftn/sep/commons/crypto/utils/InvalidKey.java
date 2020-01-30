package rs.ac.uns.ftn.sep.commons.crypto.utils;

public class InvalidKey extends RuntimeException {

    public InvalidKey(String message) {
        super(message);
    }

    public InvalidKey(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidKey(Throwable cause) {
        super(cause);
    }
}
