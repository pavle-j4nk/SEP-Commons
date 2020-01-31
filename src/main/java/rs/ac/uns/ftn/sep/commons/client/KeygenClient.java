package rs.ac.uns.ftn.sep.commons.client;

import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.client.RestTemplate;
import rs.ac.uns.ftn.sep.commons.crypto.dto.CertificateDto;
import rs.ac.uns.ftn.sep.commons.crypto.dto.GenerateRequest;
import rs.ac.uns.ftn.sep.commons.crypto.dto.SignRequest;
import rs.ac.uns.ftn.sep.commons.crypto.dto.SignedKeyDto;
import rs.ac.uns.ftn.sep.commons.enums.Service;

import java.util.List;

public class KeygenClient extends AbstractClient {
    private static final String API_ROOT = "api";
    private static final List<String> PATH_GENERATE = List.of(API_ROOT, "generate");
    private static final List<String> PATH_SIGN = List.of(API_ROOT, "sign");

    public KeygenClient(LoadBalancerClient loadBalancerClient, RestTemplate restTemplate) {
        super(Service.KEYGEN, loadBalancerClient, restTemplate);
    }

    public SignedKeyDto generateKey(GenerateRequest request) {
        return waitAndPost(PATH_GENERATE, request, SignedKeyDto.class);
    }

    public CertificateDto sign(SignRequest signRequest) {
        return waitAndPost(PATH_SIGN, signRequest, CertificateDto.class);
    }

}
