# Reactive Streams - Async for the Masses

This repository contains all sources and slides to replay the Tutoring-Session *Reactive Streams - Async for the Masses*.

## Prerequisites

* Unrestricted Internet Access for Maven usage
* Local IDE installation, eg. IntelliJ or Eclipse (optional). You can download IntelliJ CE here: https://www.jetbrains.com/idea/download/
* Java 11+ (might be included in IDE)
* Maven 3+ (might be included in IDE)

## Run

* Run `mvn clean install` to build the project
* Run `mvn quarkus:dev` to run the application OR
* Run class `Main` from the root of the project

## Test

* Call http://localhost:8080/beer for the Quarkus Reactive RESTEasy application
* Call http://localhost:8080/fruits for the Quarkus Reactive RESTEasy & Reactive JDBC with Panache application

## Test Spring Boot Reactive Beer Consumer

* Start Quarkus Application with `mvn quarkus:run` or with the `Main` class in your IDE.
* Run Spring Boot Application with `mvn spring-boot:run` or with the `Application` class in your IDE.

## Credits

* Fruits - Getting Started (RESTEasy & Panache): https://quarkus.io/guides/getting-started-reactive
* Reactive Beer: https://redhat-developer-demos.github.io/quarkus-tutorial/quarkus-tutorial/reactive.html
* Spring Beer Consumer: https://spring.io/guides/gs/reactive-rest-service/