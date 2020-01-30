package rs.ac.uns.ftn.sep.commons.crypto.utils;

public class InvalidCertificate extends RuntimeException {

    public InvalidCertificate(String message) {
        super(message);
    }

    public InvalidCertificate(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCertificate(Throwable cause) {
        super(cause);
    }
}
