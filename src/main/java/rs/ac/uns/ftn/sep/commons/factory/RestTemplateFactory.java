package rs.ac.uns.ftn.sep.commons.factory;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.web.server.Ssl;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyStore;

public class RestTemplateFactory {

    public RestTemplate create(Ssl ssl) throws Exception {
        return createWithAuthentication(ssl, null, new char[0]);
    }

    public RestTemplate createWithAuthentication(Ssl ssl, KeyStore keyStore, char[] keyStorePassword) throws Exception {
        SSLContextBuilder sslContextBuilder = SSLContextBuilder
                .create()
                .loadTrustMaterial(ResourceUtils.getFile(ssl.getTrustStore()), ssl.getTrustStorePassword().toCharArray());

        if (keyStore != null) {
            sslContextBuilder.loadKeyMaterial(keyStore, keyStorePassword, (map, socket) -> ssl.getKeyAlias());
        }

        SSLContext sslContext = sslContextBuilder.build();

        HttpClient client = HttpClients.custom()
                .setSSLContext(sslContext)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(client);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

}
