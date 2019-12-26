package rs.ac.uns.ftn.sep.commons.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseEntityHelper {
    private static final String HEADER_LOCATION = "Location";

    public static ResponseEntity<?> sendRedirect(String url) {
        return ResponseEntity.status(HttpStatus.FOUND).header(HEADER_LOCATION, url).build();
    }

}
