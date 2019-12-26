package rs.ac.uns.ftn.sep.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalBankPaymentResponse {
    private Long id;
    private String url;
}
