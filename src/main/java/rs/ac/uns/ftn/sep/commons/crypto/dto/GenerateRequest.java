package rs.ac.uns.ftn.sep.commons.crypto.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class GenerateRequest extends KeygenRequest {
    public GenerateRequest(String token, Set<String> ip) {
        super(token, ip);
    }

    public GenerateRequest(String token) {
        super(token, null);
    }

}
