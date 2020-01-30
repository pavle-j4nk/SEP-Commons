package rs.ac.uns.ftn.sep.commons.crypto.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class SignRequest extends KeygenRequest {
    private String publicKey;

    public SignRequest(String publicKey, String token, Set<String> ip) {
        super(token, ip);
        this.publicKey = publicKey;
    }

    public SignRequest(String publicKey, String token) {
        super(token, null);
        this.publicKey = publicKey;
    }

}
