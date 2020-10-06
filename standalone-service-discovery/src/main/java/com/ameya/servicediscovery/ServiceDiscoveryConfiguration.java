package com.ameya.servicediscovery;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class ServiceDiscoveryConfiguration extends Configuration {
    // TODO: implement service configuration

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty("port")
    public int port;

    @JsonProperty("host")
    public String host;
}
