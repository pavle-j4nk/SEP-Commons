package rs.ac.uns.ftn.sep.commons.client.exception;

public class UnknownInstance extends RuntimeException {

    public UnknownInstance(String instanceName) {
        super(instanceName);
    }

}
