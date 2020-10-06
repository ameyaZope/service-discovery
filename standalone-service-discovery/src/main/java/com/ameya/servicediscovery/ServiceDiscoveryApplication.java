package com.ameya.servicediscovery;

import com.ameya.servicediscovery.health.ServiceDiscoveryHealthCheck;
import com.ameya.servicediscovery.resources.ServiceDiscoveryResource;
import com.hubspot.dropwizard.guicier.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import java.io.IOException;

public class ServiceDiscoveryApplication extends Application<ServiceDiscoveryConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ServiceDiscoveryApplication().run(args);
    }

    @Override
    public String getName() {
        return "ServiceDiscovery";
    }

    @Override
    public void initialize(final Bootstrap<ServiceDiscoveryConfiguration> bootstrap) {
        // TODO: application initialization

        bootstrap.addBundle(new SwaggerBundle<ServiceDiscoveryConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                    ServiceDiscoveryConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });

        /*GuiceBundle<ServiceDiscoveryConfiguration> guiceBundle =
                GuiceBundle.defaultBuilder(ServiceDiscoveryConfiguration.class)
                .modules(new ServiceDiscoveryModule())
                .build();

        bootstrap.addBundle(guiceBundle);*/
    }

    @Override
    public void run(final ServiceDiscoveryConfiguration configuration,
                    final Environment environment) throws IOException, InterruptedException {
        // TODO: implement application

        final ServiceDiscoveryResource serviceDiscoveryResource =
                new ServiceDiscoveryResource(configuration.host, configuration.port);
        final ServiceDiscoveryHealthCheck serviceDiscoveryHealthCheck =
                new ServiceDiscoveryHealthCheck();
        environment.healthChecks().register("healthcheck",
                serviceDiscoveryHealthCheck);
        environment.jersey().register(serviceDiscoveryResource);
    }

}
