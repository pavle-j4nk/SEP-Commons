package rs.ac.uns.ftn.sep.commons.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.server.Ssl;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.ftn.sep.commons.client.KeygenClient;
import rs.ac.uns.ftn.sep.commons.factory.RestTemplateFactory;

@SuppressWarnings("ALL")
@Configuration
@RequiredArgsConstructor
public class ClientConfiguration {
    private final LoadBalancerClient loadBalancerClient;

    @Bean
    public KeygenClient keygenClient(ServerProperties serverProperties) throws Exception {
        Ssl ssl = serverProperties.getSsl();
        RestTemplateFactory restTemplateFactory = new RestTemplateFactory();
        return new KeygenClient(loadBalancerClient, restTemplateFactory.create(ssl));
    }

}
