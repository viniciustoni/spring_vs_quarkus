# spring_vs_quarkus

Repository that will cover a small comparison between Spring and Quarkus.

## Stack

- Quarkus 3.17.x
- Spring boot 3.4.x
- Docker
- Postgres
- Hibernate
- Testcontainers
- JUnit 5
- Mockito
- MapStruct

## How to run the code:

- Make sure that you have docker installed and configured on your local machine
- Run `docker compose -f docker/docker-compose.yml up`
- Spring app will run under port `8181`
    - Swagger URL: http://localhost:8181/swagger-ui/index.html
- Quarkus app will run under port: `8080`
    - Swagger URL: http://localhost:8080/q/swagger-ui/
    - DevUI: http://localhost:8080/q/dev-ui/extensions

## Quick info

Before you start going to the code I would like to give you some info of what is covered, what is planned, what is not
covered and some quick guideline of how to check the comparison between both framework on this repo.

### How to compare the code

- Both projects are a simple CRUD that saves a Product and get some system info, which they are divided into 2 modules:
    - quarkus-product-service: it's a Quarkus application
    - spring-product-service: it's a spring-boot application
- To make the comparison easy, we have the same class names on both projects - apart from the ones that I needed to
  create extra in order to make it work properly.
- There are comments on the code with `// DIFF`: which show some info about the diff between both framework

### What is cover at this moment

- RestAPI
    - Controller
    - ExceptionHandler
- Repository/DAO
    - Specifications
- Usual services bean
- Tests
    - Testcontainer
    - Mockito
- OpenApi

### What is plan to be covered soon

- Kotlin - actually my idea is to converter this entire project to kotlin, it's only in java for initial meetup
  presentation
- Native image on Spring
- Scheduler/batch jobs

### What will NOT BE covered

- Benchmark/Performance test between then - there are a lot of articles about it on the internet and the difference
  between then are not that big, plus it can maybe change based on your needs, so to be honest with you, I'll spend time
  with other stuff than simple benchmark about ~1-2Mb less between then ;)
 