package rs.ac.uns.ftn.sep.commons.enums;

public enum Service {
    BANK("bank"),
    SELLER("seller"),
    KEYGEN("keygen"),
    EXTERNAL_BANK("external-bank");

    private final String serviceName;

    Service(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }
}
