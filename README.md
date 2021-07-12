# UrlShortner

A Simple App for url shortner

# Problem
Build a URL shortener application without using any external service. 
As a user I will be able to go to a page, type in a URL and get a short URL out of it. I will also  be shown all existing short URLs and the number of times it was used. When anyone opens  the short URL, they will be redirected to the initial URL that was used to generate a short  URL. 


## Description

Easy  url managment ,Completetly free.
This single instance application is designed in a such a way that . It can be extended to multi node distributed system. 
To avoid duplicates / collision between two or more URLs and to maintain consistency and fault tolerance . We have made use of a counter range (in this case it's just a variable for simplification purpose) which will be distributed across the nodes . Hence we don't have encode our actual long URL instead we will encode our counter value . So this way of architecture would prevent any duplicates / overriding of data.
It can be seen at -https://github.com/manishk901/UrlShortner/blob/master/src/main/java/com/learning/urlshortner/util/Base62Util.java
Test Coverages with 86% -https://github.com/manishk901/UrlShortner/blob/master/testcoverage.png

## Getting Started

we need to use mvn spring-boot:run

### Dependencies

For building and running the application you need:
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- IDE of Your Choice

### Installing

We are using spring libarys
mvn clean build

### Executing program

* mvn spring:run
* http://localhost:8080/ or http://localhost:8080/swagger-ui.html
```
you can use swagger also directly.
```

## Running the application locally

Clone the project in local.
Run ```mvn clean install```

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.learning.urlshortner.UrlShortnerApplication.java` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Help

Any advise for common problems or issues. Please report to manishk901@gmail.com


## Authors

Contributors names and contact info
Manish kumar sinha
manishk901@gmail.com


## Version History


* 0.1
    * Initial Release



## Acknowledgments

Inspiration, code snippets, etc.
* Spring.io
