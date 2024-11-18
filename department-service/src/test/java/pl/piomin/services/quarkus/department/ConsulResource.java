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
                        EOF
                        """
//                        "kv put config/department-service department.name=abc",
//                        "kv put config/department-service quarkus.datasource.db-kind=h2",
//                        "kv put config/department-service quarkus.hibernate-orm.database.generation=drop-and-create"
                );

        consulContainer.start();

        String url = consulContainer.getHost() + ":" + consulContainer.getFirstMappedPort();

        return ImmutableMap.of("quarkus.consul-config.agent.host-port", url);
    }

    @Override
    public void stop() {
        consulContainer.stop();
    }
}
