package pl.piomin.services.quarkus.organization.client;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;

public class LoadBalancedFilter implements ClientRequestFilter {

//    private Consul consulClient;
//    private AtomicInteger counter = new AtomicInteger();
//
//    public LoadBalancedFilter(Consul consulClient) {
//        this.consulClient = consulClient;
//    }

    @Override
    public void filter(ClientRequestContext ctx) {
//        URI uri = ctx.getUri();
//        HealthClient healthClient = consulClient.healthClient();
//        List<ServiceHealth> instances = healthClient
//                .getHealthyServiceInstances(uri.getHost()).getResponse();
//        ServiceHealth instance = instances.get(counter.getAndIncrement());
//        URI u = UriBuilder.fromUri(uri)
//                .host(instance.getService().getAddress())
//                .port(instance.getService().getPort())
//                .build();
//        ctx.setUri(u);
    }

}
