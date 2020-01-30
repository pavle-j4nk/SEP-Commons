package rs.ac.uns.ftn.sep.commons.configuration;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.server.Ssl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import rs.ac.uns.ftn.sep.commons.factory.RestTemplateFactory;
import rs.ac.uns.ftn.sep.commons.ssl.SslKeys;

import java.security.KeyStore;

@SuppressWarnings("ALL")
@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(ServerProperties serverProperties, SslKeys sslKeys) throws Exception {
        Ssl ssl = serverProperties.getSsl();
        RestTemplateFactory restTemplateFactory = new RestTemplateFactory();
        KeyStore keyStore = sslKeys.getSslKeystore();
        char[] keyStorePassword = ssl.getKeyStorePassword().toCharArray();

        return restTemplateFactory.createWithAuthentication(serverProperties.getSsl(), keyStore, keyStorePassword);
    }

}
