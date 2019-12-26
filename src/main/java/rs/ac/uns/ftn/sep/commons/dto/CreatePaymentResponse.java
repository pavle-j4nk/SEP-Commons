package rs.ac.uns.ftn.sep.commons.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class CreatePaymentResponse {
    private Long paymentId;
    private String redirect;
    private String errorMessage;
}
