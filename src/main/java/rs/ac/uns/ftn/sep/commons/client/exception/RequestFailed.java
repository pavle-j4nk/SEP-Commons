package rs.ac.uns.ftn.sep.commons.client.exception;

import org.springframework.http.HttpStatus;

public class RequestFailed extends RuntimeException {

    public RequestFailed(HttpStatus status) {
        super(status.value() + " " + status.getReasonPhrase());
    }
}
