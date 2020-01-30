package rs.ac.uns.ftn.sep.commons.ssl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.SslStoreProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import rs.ac.uns.ftn.sep.commons.client.KeygenClient;
import rs.ac.uns.ftn.sep.commons.constants.Algorithms;
import rs.ac.uns.ftn.sep.commons.crypto.dto.GenerateRequest;
import rs.ac.uns.ftn.sep.commons.crypto.dto.SignedKeyDto;
import rs.ac.uns.ftn.sep.commons.crypto.utils.KeyUtils;
import rs.ac.uns.ftn.sep.commons.helper.KeystoreHelper;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Enumeration;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class SslKeys implements SslStoreProvider {
    public static final String KEY_ALIAS_DEFAULT = "main";
    public static final int PASSWORD_LEN = 16;
    public static final String CA = "ca";

    private final KeygenClient keygenClient;
    private final ServerProperties serverProperties;

    private String baseKeystore;
    private String baseKeystorePassword;
    private String baseKeystoreType;

    @Value("${keygen.token}")
    private String keygenToken;
    @Getter
    private Ssl ssl;
    @Getter
    private KeyStore keyStore;
    @Getter
    private KeyStore sslKeystore;


    @PostConstruct
    public void initialize() {
        ssl = serverProperties.getSsl();

        baseKeystore = ssl.getKeyStore();
        baseKeystorePassword = ssl.getKeyStorePassword();
        baseKeystoreType = ssl.getKeyStoreType();

        addKeystoreDataToSslConfig(ssl);
    }

    private void addKeystoreDataToSslConfig(Ssl ssl) {
        ssl.setKeyAlias(KEY_ALIAS_DEFAULT);

        String password = RandomStringUtils.random(PASSWORD_LEN, true, true);
        ssl.setKeyStorePassword(password);
        ssl.setKeyPassword(password);

        ssl.setEnabled(true);
    }

    public void createKeyStore() throws Exception {
        GenerateRequest generateRequest = new GenerateRequest(keygenToken);
        SignedKeyDto signedKeyDto = keygenClient.generateKey(generateRequest);

        PrivateKey privateKey = KeyUtils.decodeToPrivate(signedKeyDto.getPrivateKey(), Algorithms.RSA);
        Certificate certificate = KeyUtils.decodeToCertificate(signedKeyDto.getClientCertificate());
        Certificate caCertificate = KeyUtils.decodeToCertificate(signedKeyDto.getCaCertificate());

        KeyStore keyStore = KeystoreHelper.create(ssl.getKeyStorePassword());

        keyStore.setCertificateEntry(CA, caCertificate);
        keyStore.setKeyEntry(ssl.getKeyAlias(), privateKey, ssl.getKeyPassword().toCharArray(), new Certificate[]{certificate, caCertificate});

        sslKeystore = KeystoreHelper.create(ssl.getKeyStorePassword());
        copyEntities(keyStore, sslKeystore, ssl.getKeyPassword().toCharArray());

        if (nonNull(baseKeystore)) {
            KeyStore baseKeyStore = KeyStore.getInstance(baseKeystoreType);
            char[] password = nonNull(baseKeystorePassword) ? baseKeystorePassword.toCharArray() : null;
            baseKeyStore.load(new FileInputStream(ResourceUtils.getFile(baseKeystore)), password);
            copyEntities(baseKeyStore, keyStore, password);
        }

        this.keyStore = keyStore;
    }

    private void copyEntities(KeyStore from, KeyStore to, char[] password) throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException {
        Enumeration<String> aliases = from.aliases();

        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            if (from.isCertificateEntry(alias)) {
                to.setCertificateEntry(alias, from.getCertificate(alias));
            } else if (from.isKeyEntry(alias)) {
                Key key = from.getKey(alias, password);
                Certificate[] certificateChain = from.getCertificateChain(alias);
                to.setKeyEntry(alias, key, password, certificateChain);
            }
        }
    }

    @SneakyThrows
    @Override
    public KeyStore getKeyStore() {
        if (keyStore == null)
            createKeyStore();

        return keyStore;
    }

    @Override
    public KeyStore getTrustStore() throws Exception {
        KeyStore truststore = KeyStore.getInstance("JKS");
        truststore.load(new FileInputStream(ResourceUtils.getFile(ssl.getTrustStore())), ssl.getTrustStorePassword().toCharArray());
        return truststore;
    }

}
