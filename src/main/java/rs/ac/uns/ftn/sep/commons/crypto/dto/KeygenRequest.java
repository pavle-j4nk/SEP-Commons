package rs.ac.uns.ftn.sep.commons.crypto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class KeygenRequest {
    protected String token;
    protected Set<String> ip;
}
