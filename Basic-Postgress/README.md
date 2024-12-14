# SpringBoot-JPA
Contains Projects for connecting Springboot to PostGress  DB etc

# Getting Started
#
# Step-by-Step Guide to Create a Spring Boot Application with Flyway, Entity, Repository, Service, Controller, Swagger, Docker, and Docker Compose

## 1. Create a Spring Boot Application using Spring Initializr
- Go to Spring Initializr.
- Fill in the project metadata:
    - **Project**: Gradle Project
    - **Language**: Java
    - **Spring Boot**: 3.3.6
    - **Group**: com.example
    - **Artifact**: demo
    - **Name**: demo
    - **Package name**: com.example.demo
    - **Packaging**: Jar
    - **Java**: 17
- Add dependencies:
    - Spring Web
    - Spring Data JPA
    - PostgreSQL Driver
    - Flyway Migration
    - Spring Boot DevTools
- Click "Generate" to download the project.
- Extract the downloaded zip file and open it in IntelliJ IDEA.

## 2. Configure Flyway for Database Versioning
- Open `src/main/resources/application.properties` and add the following configuration:
  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5432/testdb
  spring.datasource.username=postgres
  spring.datasource.password=test
  spring.jpa.hibernate.ddl-auto=none
  spring.flyway.enabled=true
  spring.flyway.locations=classpath:db/migration

- Create a migration script:  
  - Create a directory in `src/main/resources/db/migration`.  
  - Add a file `V1__Create_users_table.sql` with the following content:
```sql
  CREATE TABLE users (
      id SERIAL PRIMARY KEY,
      name VARCHAR(100) NOT NULL,
      email VARCHAR(100) NOT NULL
  );
  ```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.6/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.6/gradle-plugin/packaging-oci-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.3.6/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/3.3.6/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

