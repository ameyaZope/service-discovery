# Standalone Service Discovery Server using Dropwizard and Apache Zookeeper

How to start the ServiceDiscovery application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/servicediscovery-1.0.0.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

---------

## Purpose

In microservice architecture, servers may go down and new servers may come up dynamically.
In this scenario, we want a way to keep track of all the servers associated with a service
(So that when you want to call that service, you know the host and port of the server to which 
the request needs to be directed to). This is where service discovery comes in. This project
aims to provide a server which can act as a service discovery.

When a new instance of your service spanws up, it can register itself with this service discovery 
server. Other services who need to call your service can simply ask this service discovery server
for the locations for your service.   
