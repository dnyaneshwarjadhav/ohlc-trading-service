# OHLC

OHLC is a spring-boot microservice for handling worker nodes to perform task in parallel.

### Tech

OHLC uses a number of open source projects to work properly:

* [Maven](http://maven.apache.org/) - Build Tool
* [Spring-Boot](https://spring.io/projects/spring-boot) - Spring REST
* [H2-Database](https://www.h2database.com/html/main.html) - Storing Data


### Installation

OHLS requires [Java-Jdk-1.8](https://www.oracle.com/in/java/technologies/javase/javase-jdk8-downloads.html/) to run.

Install the dependencies and devDependencies and start the server.

## Architecture flow
![alt text](https://github.com/dnyaneshwarjadhav/ohlc-trading-service/blob/master/ohlc.png)

## Once Sprint Boot Application will be started successfully then we 
can call following Endpoints by using POSTMAN

### 1. To subscript new clients call following endpoint with GET Request
```
  http://localhost:8080/register
```
### set content type as in header as `application/json`
### set request body as raw with JSON payload
```
  {
    "name": "Client"
  }
```

### Todos

 - A File Listener to continuosely read file without interrupt
 - Notify the individual threads

License
----

UPSTOX


**Free Software, Hell Yeah!**
