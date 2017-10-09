# rankings


## about

Alexa rank web sites according to popularity. Below are some instructions on how to run an application that will provide the rankings as a RESTful API. Also see [![fnarg blog](https://www.fnarg.net/blog/)]

## dependencies

* Mongo 3.4.9
* Java 1.8
* apache-maven-3.5.0

Development was done on an elderly macbook, Darwin Kernel Version 16.7.0.

## database setup

Import one million records into mongo.

```
bash-3.2$ sudo mongoimport --type=csv --headerline -c=rankings --file=src/main/resources/top-1m.csv
```

Let's start the mongo daemon
```
mongod
```

And then use the mongo shell to run a test query that the API will need to support.

```
> db.rankings.find( { domain: /^bbc.co/ } )
```

There should be two records in the result set. 

## application

The key depencies in the pom are show below.
```
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.7.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb</artifactId>
        </dependency>
    </dependencies>
```

Once built with maven as a clean package, the app was run as a jar
```
java -jar target/gs-rest-service-0.1.0.jar
```

The console output from Spring Boot should have something similar to
```
number of records found: 1000000
2017-10-08 22:17:42.987  INFO 807 --- [main] net.fnarg.api.Application: Started Application in 17.238 seconds
```

This is to tell you that Spring was able to connect to mongo and find the rankings collection.

## testing

To test that Spring Boot is running okay, let's run our same query over http.

```
bash-3.2$ curl -v http://localhost:8080/rankings?query=bbc.co

> GET /rankings?query=bbc.co HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*

< HTTP/1.1 200 
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Sun, 08 Oct 2017 21:39:33 GMT

[{
  "rank": 102,
  "domain": "bbc.co.uk"
}, {
  "rank": 106,
  "domain": "bbc.com"
}]
```

Happy searching!