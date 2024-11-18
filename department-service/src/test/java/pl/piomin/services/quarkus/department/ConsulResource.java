package pl.piomin.services.quarkus.department;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.consul.ConsulContainer;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;

import java.util.Map;

public class ConsulResource implements QuarkusTestResourceLifecycleManager {

    private ConsulContainer consulContainer;

    @Override
    public Map<String, String> start() {
        consulContainer = new ConsulContainer("hashicorp/consul:latest")
                .withConsulCommand(
                """
                        kv put config/department-service - <<EOF
                        department.name=abc
                        quarkus.datasource.db-kind=h2
                        quarkus.hibernate-orm.database.generation=drop-and-create
                        quarkus.stork.department-service.service-registrar.type=consul
                        EOF
                        """
                );

        consulContainer.start();

        String url = consulContainer.getHost() + ":" + consulContainer.getFirstMappedPort();

        return ImmutableMap.of(
                "quarkus.consul-config.agent.host-port", url,
                "consul.host", consulContainer.getHost(),
                "consul.port", consulContainer.getFirstMappedPort().toString()
        );
    }

    @Override
    public void stop() {
        consulContainer.stop();
    }
}
