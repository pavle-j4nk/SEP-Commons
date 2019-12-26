package rs.ac.uns.ftn.sep.commons.factory;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.client.RestTemplate;

import java.security.KeyStore;

public class RestTemplateFactory {

    public static RestTemplate createRestTemplate(ServerProperties serverProperties) {
        RestTemplate restTemplate = new RestTemplate();

//        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial())
        return null;
    }

}
