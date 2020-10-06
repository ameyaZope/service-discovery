# Service Discovery


Library for Client Side Service Discovery
---------
This is a library that can provide client side service discovery. Upload this to your artifactory, 
include this in every service that you host and voila, service discovery is taken care of. This
library uses Apache Zookeeper to ensure high availability of service discovery and is a 
distributed solution and has no single point of failure.


Standalone Service Discover Server
---------
This is a server that can provide service discovery. It internally uses Apache Zookeeper for 
high availability.