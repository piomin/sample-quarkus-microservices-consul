# Quarkus Microservices with Consul Discovery Demo Project [![Twitter](https://img.shields.io/twitter/follow/piotr_minkowski.svg?style=social&logo=twitter&label=Follow%20Me)](https://twitter.com/piotr_minkowski)

In this project I'm demonstrating you the most interesting features of [Quarkus Project](https://quarkus.io/) for building microservice-based architecture.


## Getting Started 
Currently you may find here some examples of microservices implementation using different projects from Quarkus. Here's a full list of available examples in this repository:
1. Using Quarkus for building microservices that may be easily deployed outside Kubernetes. Integrating Quarkus with Consul discovery and KV store. The example is available in the branch [master](https://github.com/piomin/sample-quarkus-microservices-consul/tree/master). A detailed guide may be find in the following article: Detailed description can be found here: [Quarkus Microservices with Consul Discovery](https://piotrminkowski.com/2020/11/24/quarkus-microservices-with-consul-discovery/)

## Usage
1. Maven 3.6.3+
2. JDK 11+
3. Docker - run Consul:\
$ docker run -d --name=consul -e CONSUL_BIND_INTERFACE=eth0 -p 8500:8500 consul
4. Run applications:
$ mvn compile quarkus:dev

## Architecture
Our sample microservices-based system consists of the following modules:
- **gateway-service** - a module that uses Spring Cloud Gateway for running Spring Boot application that acts as a proxy/gateway in our architecture.
- **employee-service** - a module containing the first of our sample microservices that allows to perform CRUD operation on in-memory repository of employees
- **department-service** - a module containing the second of our sample microservices that allows to perform CRUD operation on in-memory repository of departments. It communicates with employee-service. 
- **organization-service** - a module containing the third of our sample microservices that allows to perform CRUD operation on in-memory repository of organizations. It communicates with both employee-service and department-service.

The following picture illustrates the architecture described above.

<img src="https://i1.wp.com/piotrminkowski.com/wp-content/uploads/2020/11/quarkus-consul-arch.png?w=782&ssl=1" title="Architecture"><br/>