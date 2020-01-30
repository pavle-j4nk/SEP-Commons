package rs.ac.uns.ftn.sep.commons.crypto.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.ByteArrayInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KeyUtils {
    private static final String BEGIN_PATTERN = "(.*?)-----BEGIN (.*)-----";
    private static final String END_PATTERN = "-----END (.*)-----(.*?)";
    private static final String NEWLINE_PATTERN = "\r?\n";

    private static final Base64.Decoder DECODER = Base64.getDecoder();

    /**
     * Encodes key to Base64 string.
     *
     * @param key
     * @return
     */
    public static String encode(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    /**
     * Encodes certificate to Base64 string.
     *
     * @param certificate
     * @return
     */
    public static String encode(Certificate certificate) {
        try {
            return Base64.getEncoder().encodeToString(certificate.getEncoded());
        } catch (CertificateEncodingException e) {
            throw new InvalidCertificate(e);
        }
    }

    /**
     * Decodes Base64 encoded public key representation to {@link PublicKey} instance.
     *
     * @param encoded   public key (base64)
     * @param algorithm
     * @return
     */
    public static PublicKey decodeToPublic(String encoded, String algorithm) {
        encoded = strip(encoded);

        KeyFactory keyFactory = createKeyFactory(algorithm);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(DECODER.decode(encoded), algorithm);

        PublicKey publicKey;
        try {
            publicKey = keyFactory.generatePublic(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new InvalidKey(e);
        }

        return publicKey;
    }

    /**
     * Decodes Base64 encoded private key representation to {@link PrivateKey} instance.
     *
     * @param encoded   public key (base64)
     * @param algorithm
     * @return
     */
    public static PrivateKey decodeToPrivate(String encoded, String algorithm) {
        encoded = strip(encoded);

        KeyFactory keyFactory = createKeyFactory(algorithm);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(DECODER.decode(encoded), algorithm);

        PrivateKey publicKey;
        try {
            publicKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new InvalidKey(e);
        }

        return publicKey;
    }

    private static KeyFactory createKeyFactory(String algorithm) {
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new InvalidKey(e);
        }

        return keyFactory;
    }

    /**
     * Decodes Base64 encoded x509 certificate to {@link Certificate} instance.
     *
     * @param encoded certificate (base64)
     * @return
     */
    public static X509Certificate decodeToCertificate(String encoded) {
        encoded = strip(encoded);

        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            throw new InvalidCertificate(e);
        }

        Certificate certificate;
        try {
            certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(DECODER.decode(encoded)));
        } catch (CertificateException e) {
            throw new InvalidCertificate(e);
        }

        return (X509Certificate) certificate;
    }

    /**
     * Removes key header, footers and new lines.
     *
     * @param encodedKey
     * @return
     */
    public static String strip(String encodedKey) {
        return encodedKey.replaceAll(BEGIN_PATTERN, "").replaceAll(END_PATTERN, "").replaceAll(NEWLINE_PATTERN, "");
    }

}
