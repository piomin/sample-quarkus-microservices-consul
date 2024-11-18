package pl.piomin.services.quarkus.employee;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Map;


public class DisableExternalProfile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of(
                "quarkus.consul-config.enabled", "false",
                "quarkus.datasource.db-kind", "h2",
                "quarkus.hibernate-orm.database.generation", "drop-and-create");
    }
}
