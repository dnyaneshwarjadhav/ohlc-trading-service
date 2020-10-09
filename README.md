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

# Flow!
### 1. On start up springboot application
  - Default init method will trigger
  - This init method is purpose to start a separate thread for reading file4
  - And to trigger all of Workers thread (Worker-1,2,3)

### 2. Worker Thread
  - Worker-1 will take up data from file & store into DB by calling to FSM service
  - Worker-2 is taking up all the data which was generated into system in span of last 15 minutes
  - Construct a trade bar of all collective Chart bars from above, and this will generate a new bar_num which is unique
  - Worker-3 is responsible for taking clients subscriptions and publish the trade bars. Worker-3 get all the trade bars from Database which was not published but newly generated into system in last 10 minutes

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
 - Hard coded file names while reading the data (trades.json & client_subscription.json)

License
----

UPSTOX


**Free Software, Hell Yeah!**
