package rs.ac.uns.ftn.sep.commons.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.security.KeyStore;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KeystoreHelper {

    @SneakyThrows
    public static KeyStore create(String password) {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, password.toCharArray());

        return keyStore;
    }

}
