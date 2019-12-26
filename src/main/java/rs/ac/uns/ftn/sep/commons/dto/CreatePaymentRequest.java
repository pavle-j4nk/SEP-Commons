package rs.ac.uns.ftn.sep.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {
    private String merchantName;
    private String redirectUrl;
    private Double amount;
}
