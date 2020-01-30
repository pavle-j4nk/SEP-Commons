package rs.ac.uns.ftn.sep.commons.client;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rs.ac.uns.ftn.sep.commons.dto.seller.CreatePaymentMethodDto;
import rs.ac.uns.ftn.sep.commons.dto.seller.SupportedPaymentMethodDto;
import rs.ac.uns.ftn.sep.commons.enums.Service;

import java.util.List;

@Component
public class SellerClient extends AbstractClient {
    private final List<String> PATH_CREATE_PAYMENT_METHOD = List.of("api", "methods");

    public SellerClient(LoadBalancerClient loadBalancerClient, RestTemplate restTemplate) {
        super(Service.SELLER, loadBalancerClient, restTemplate);
    }

    public SupportedPaymentMethodDto createPaymentMethod(CreatePaymentMethodDto request) {
        return post(PATH_CREATE_PAYMENT_METHOD, request, SupportedPaymentMethodDto.class);
    }

}
