package rs.ac.uns.ftn.sep.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.uns.ftn.sep.commons.constants.PaymentMethod;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupportedPaymentMethods {
    private List<PaymentMethod> supportedPaymentMethods;
}
