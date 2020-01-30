package rs.ac.uns.ftn.sep.commons.crypto.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;

@RequiredArgsConstructor
@Getter
@With
public class CertificateDto {
    private final String certificate;
}
