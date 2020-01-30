package rs.ac.uns.ftn.sep.commons.dto.seller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.sep.commons.constants.PaymentMethod;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePaymentMethodDto {
    private Long externalId;
    private String name;
    private String email;
    private PaymentMethod paymentMethod;
}
