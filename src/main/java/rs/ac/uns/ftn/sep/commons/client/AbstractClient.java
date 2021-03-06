package rs.ac.uns.ftn.sep.commons.client;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import rs.ac.uns.ftn.sep.commons.client.exception.RequestFailed;
import rs.ac.uns.ftn.sep.commons.client.exception.UnknownInstance;
import rs.ac.uns.ftn.sep.commons.enums.Service;
import rs.ac.uns.ftn.sep.commons.helper.UrlHelper;

import java.util.List;

import static java.util.Objects.isNull;

public abstract class AbstractClient {
    private final Service service;
    private final LoadBalancerClient loadBalancerClient;
    private final RestTemplate restTemplate;

    public AbstractClient(Service service, LoadBalancerClient loadBalancerClient, RestTemplate restTemplate) {
        this.service = service;
        this.loadBalancerClient = loadBalancerClient;
        this.restTemplate = restTemplate;
    }

    protected <T> T waitAndGet(List<String> path, Class<T> responseType, Object... params) {
        String url = UrlHelper.addPathVariables(waitForUrl(), path.toArray(new String[0]));
        return get(url, responseType, params);
    }

    protected <T> T get(List<String> path, Class<T> responseType, Object... params) {
        String url = UrlHelper.addPathVariables(chooseUrl(), path.toArray(new String[0]));
        return get(url, responseType, params);
    }

    protected <T> T get(String url, Class<T> responseType, Object... params) {

        ResponseEntity<T> response = restTemplate.getForEntity(url, responseType, params);
        HttpStatus status = response.getStatusCode();

        if (!status.is2xxSuccessful()) {
            throw new RequestFailed(status);
        }

        return response.getBody();
    }

    protected <T> T post(List<String> path, Object request, Class<T> responseType) {
        String url = UrlHelper.addPathVariables(chooseUrl(), path.toArray(new String[0]));
        return post(url, request, responseType);
    }

    protected <T> T waitAndPost(List<String> path, Object request, Class<T> responseType) {
        String url = UrlHelper.addPathVariables(waitForUrl(), path.toArray(new String[0]));
        return post(url, request, responseType);
    }

    protected <T> T post(String url, Object request, Class<T> responseType) {
        ResponseEntity<T> response = restTemplate.postForEntity(url, request, responseType);
        HttpStatus status = response.getStatusCode();

        if (!status.is2xxSuccessful()) {
            throw new RequestFailed(status);
        }

        return response.getBody();
    }

    /**
     * Chooses url for service based eureka registry, and load balancer.
     *
     * @return URL
     */
    public String chooseUrl() {
        ServiceInstance instance = loadBalancerClient.choose(service.getServiceName());
        if (isNull(instance)) {
            throw new UnknownInstance(service.getServiceName());
        }

        return instance.getUri().toString();
    }

    /**
     * Chooses url for service based eureka registry, and load balancer.
     * Call will block until required service is available.
     *
     * @return URL
     */
    public String waitForUrl() {
        while (true) {
            try {
                return chooseUrl();
            } catch (UnknownInstance unknownInstance) {
                System.out.println("Instance now available: " + service.getServiceName());
                System.out.println("Waiting...");
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    throw unknownInstance;
                }
            }
        }
    }

}
