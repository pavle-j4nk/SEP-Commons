package rs.ac.uns.ftn.sep.commons.crypto.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ResourceUtils;
import rs.ac.uns.ftn.sep.commons.constants.Algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeyLoader {

    public static PrivateKey loadPrivate(String location) {
        try {
            return loadPrivateFromFile(ResourceUtils.getFile(location));
        } catch (FileNotFoundException e) {
            throw new FileException(e);
        }
    }

    public static PublicKey loadPublic(String location) {
        try {
            return loadPublicFromFile(ResourceUtils.getFile(location));
        } catch (FileNotFoundException e) {
            throw new FileException(e);
        }
    }

    public static X509Certificate loadCertificate(String location) {
        try {
            return loadCertificateFromFile(ResourceUtils.getFile(location));
        } catch (FileNotFoundException e) {
            throw new FileException(e);
        }
    }

    public static PrivateKey loadPrivateFromFile(File file) {
        String encoded;
        try {
            encoded = Files.readString(file.toPath());
        } catch (IOException e) {
            throw new FileException(e);
        }

        return KeyUtils.decodeToPrivate(encoded, Algorithms.RSA);
    }

    public static PublicKey loadPublicFromFile(File file) {
        String encoded;
        try {
            encoded = Files.readString(file.toPath());
        } catch (IOException e) {
            throw new FileException(e);
        }

        return KeyUtils.decodeToPublic(encoded, Algorithms.RSA);
    }

    public static X509Certificate loadCertificateFromFile(File file) {
        String encoded;
        try {
            encoded = Files.readString(file.toPath());
        } catch (IOException e) {
            throw new FileException(e);
        }

        return KeyUtils.decodeToCertificate(encoded);
    }

}
