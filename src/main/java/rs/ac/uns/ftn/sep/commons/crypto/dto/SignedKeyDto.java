package rs.ac.uns.ftn.sep.commons.crypto.dto;

import lombok.*;

@Getter
@With
@AllArgsConstructor
@NoArgsConstructor
public class SignedKeyDto {
    private String privateKey;
    private String clientCertificate;
    private String caCertificate;
}
