package rs.ac.uns.ftn.sep.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class PaymentStatusResponse {
    private Long paymentId;
    private PaymentStatus status;
}
