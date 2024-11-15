package pl.piomin.services.quarkus.employee.config;

import io.quarkus.arc.lookup.LookupIfProperty;
import io.quarkus.arc.properties.IfBuildProperty;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class EmployeeBeanProducer {

    @ConfigProperty(name = "consul.host", defaultValue = "localhost")  String host;
    @ConfigProperty(name = "consul.port", defaultValue = "8500") int port;

    @Produces
    @LookupIfProperty(name = "quarkus.stork.employee-service.service-registrar.type", stringValue = "consul")
    public ConsulClient consulClient(Vertx vertx) {
        return ConsulClient.create(vertx, new ConsulClientOptions()
                .setHost(host)
                .setPort(port));
    }

}
