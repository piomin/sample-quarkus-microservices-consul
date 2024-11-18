package pl.piomin.services.quarkus.department;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Map;


public class DisableExternalProfile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of(
                "quarkus.consul-config.enabled", "false",
                "department.name", "abc",
                "quarkus.datasource.db-kind", "h2",
                "quarkus.hibernate-orm.database.generation", "drop-and-create");
    }
}
