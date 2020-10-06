package com.ameya.servicediscovery.resources;

import com.ameya.servicediscovery.api.Server;
import com.ameya.servicediscovery.api.Service;
import com.ameya.servicediscovery.core.ServiceDiscovery;
import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(value = "Server Side Service Discovery")
@Path("/serviceDiscovery")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON) // without this annotation all the Api functions cannot return non json response
public class ServiceDiscoveryResource {

    ServiceDiscovery serviceDiscovery;


    public ServiceDiscoveryResource(String host, int port) throws IOException, InterruptedException {
        serviceDiscovery = new ServiceDiscovery(host, port);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @ApiOperation(value = "Fetches the host and port on which the service is running")
    @Timed
    @GET
    @Path(value = "/service")
    public List<Server> findService(
            final @QueryParam("serviceName") String serviceName) {
        return serviceDiscovery.getServers(serviceName);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @ApiOperation(value = "Registers your service with zookeeper")
    @Timed
    @POST
    @Path(value = "/register")
    public boolean registerService(Service service) {
        return serviceDiscovery.register(service);
    }
}
