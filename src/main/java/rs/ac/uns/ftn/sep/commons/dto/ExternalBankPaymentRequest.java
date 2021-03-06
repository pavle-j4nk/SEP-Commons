package rs.ac.uns.ftn.sep.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalBankPaymentRequest {
    @Max(value = 30)
    private String merchantId;

    @Max(value = 100)
    private String merchantPassword;

    private BigDecimal amount;

    private Integer merchantOrderId;

    private Date merchantTimestamp;

    private String successUrl;

    private String failedUrl;

    private String errorUrl;
}
