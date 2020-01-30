package rs.ac.uns.ftn.sep.commons.dto.seller;

import lombok.Data;
import rs.ac.uns.ftn.sep.commons.constants.PaymentMethod;

@Data
public class SupportedPaymentMethodDto {
    private Long id;
    private String name;
    private PaymentMethod paymentMethod;
}
