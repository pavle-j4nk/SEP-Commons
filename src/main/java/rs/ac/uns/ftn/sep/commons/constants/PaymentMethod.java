package rs.ac.uns.ftn.sep.commons.constants;

public enum PaymentMethod {
    BANK("bank"),
    PAYPAL("paypal"),
    BITCOIN("bitcoin");

    private final String value;
    PaymentMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static PaymentMethod forName(String name) {
        PaymentMethod paymentMethod = null;

        if(name.equalsIgnoreCase(BANK.value))
            paymentMethod = BANK;
        else if(name.equalsIgnoreCase(PAYPAL.value))
            paymentMethod = PAYPAL;
        else if(name.equalsIgnoreCase(BITCOIN.value))
            paymentMethod = BITCOIN;

        return paymentMethod;
    }

}
