package rs.ac.uns.ftn.sep.commons.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.ftn.sep.commons.ssl.SslKeys;

@Configuration
@RequiredArgsConstructor
public class WebServerSslCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    private final SslKeys sslKeys;

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.setSslStoreProvider(sslKeys);
    }

}
