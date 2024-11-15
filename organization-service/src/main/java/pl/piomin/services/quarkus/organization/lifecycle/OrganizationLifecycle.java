package pl.piomin.services.quarkus.organization.lifecycle;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ServiceOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Instance;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class OrganizationLifecycle {

    @ConfigProperty(name = "quarkus.application.name")
    private String appName;
    private int port;

    private Logger logger;
    private Instance<ConsulClient> consulClient;
    private ScheduledExecutorService executor;

    public OrganizationLifecycle(Logger logger,
                                 Instance<ConsulClient> consulClient,
                                 ScheduledExecutorService executor) {
        this.logger = logger;
        this.consulClient = consulClient;
        this.executor = executor;
    }

    void onStart(@Observes StartupEvent ev) {
        if (consulClient.isResolvable()) {
            executor.schedule(() -> {
                port = ConfigProvider.getConfig().getValue("quarkus.http.port", Integer.class);
                consulClient.get().registerService(new ServiceOptions()
                                .setPort(port)
                                .setAddress("localhost")
                                .setName(appName)
                                .setId(appName  + "-" + port),
                        result -> logger.infof("Service %s registered", appName));
            }, 3000, TimeUnit.MILLISECONDS);
        }
    }

    void onStop(@Observes ShutdownEvent ev) {
        if (consulClient.isResolvable()) {
            consulClient.get().deregisterService(appName + "-" + port,
                    result -> logger.infof("Service %s deregistered", appName));
        }
    }
}
