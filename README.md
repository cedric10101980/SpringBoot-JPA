# SpringBoot-JPA
Contains Projects for connecting Springboot to PostGress  DB etc

# Getting Started
#
# Step-by-Step Guide to Create a Spring Boot Application with Flyway, Entity, Repository, Service, Controller, Swagger, Docker, and Docker Compose

## 1. Create a Spring Boot Application using Spring Initializr
- Go to [Spring Initializr](https://start.spring.io/).
- Fill in the project metadata:
    - **Project**: Gradle Groovy Project
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
- Open `src/main/resources/application.properties`[application.properties](Basic-Postgress/src/main/resources/application.properties) and add the following configuration:
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
## 3. Create Entity, Repository, Service, and Controller Layers

### Entity
- Create a package `com.example.demo.entity`.
  - Add a class `User.java`: [Users.java](Basic-Postgress/src/main/java/com/jpa/postgress/dboperations/entity/Users.java)
    ```java
    @Entity
    @Table(name = "users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private String name;
    
        private String email;
    
        // Getters and Setters
    }
    ``` 
### Repository
- Create a package `com.example.demo.repository`.
- Add an interface `UserRepository.java`: [UsersRepo.java](Basic-Postgress/src/main/java/com/jpa/postgress/dboperations/repo/UsersRepo.java)
    ```java
    public interface UserRepository extends JpaRepository<User, Long> {
    }
    ```
### Service
- Create a package `com.example.demo.service`.
- Add a class `UserService.java`: [UsersService.java](Basic-Postgress/src/main/java/com/jpa/postgress/dboperations/service/UsersService.java)
    ```java
    @Service
    public class UserService {
        private final UserRepository userRepository;
    
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
    
        public List<User> getUsers() {
            return userRepository.findAll();
        }
    
        public User createUser(User user) {
            return userRepository.save(user);
        }
    }
    ```

### Controller
- Create a package `com.example.demo.controller`.
- Add a class `UserController.java`: [UsersController.java](Basic-Postgress/src/main/java/com/jpa/postgress/dboperations/controller/UsersController.java)
    ```java
    @RestController
    @RequestMapping("/users")
    public class UserController {
        private final UserService userService;
    
        public UserController(UserService userService) {
            this.userService = userService;
        }
    
        @GetMapping
        public List<User> getUsers() {
            return userService.getUsers();
        }
    
        @PostMapping
        public User createUser(@RequestBody User user) {
            return userService.createUser(user);
        }
    }
    ```
## 4. Add Swagger for API Documentation
- Add the following dependency to `build.gradle`:
    ```groovy
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0'
    ```
## 5. Create a Multi-Stage Dockerfile
- Create a `Dockerfile` in the root directory: [Dockerfile](Basic-Postgress/Dockerfile)
  - Add the following content:
      ```dockerfile
      # Build Stage
      FROM openjdk:17-jdk-slim AS build
      WORKDIR application
      COPY build.gradle settings.gradle gradlew ./
      COPY gradle ./gradle
      RUN ./gradlew dependencies
      COPY src ./src
      RUN ./gradlew build

      # Package Stage
      FROM openjdk:17-jdk-slim
      COPY --from=build /application/build/libs/demo-0.0.1-SNAPSHOT.jar demo.jar
      ENTRYPOINT ["java", "-jar", "demo.jar"]
      ```
 ## 6. Manage PostgreSQL and Spring Boot Applications with Docker Compose
- Create a `docker-compose.yml` file in the root directory: [docker-compose.yaml](Basic-Postgress/docker-compose.yaml)
    - Add the following content:
        ```yaml
        version: '3.8'
    
        services:
            db:
            image: postgres
            container_name: postgres
            environment:
                POSTGRES_DB: testdb
                POSTGRES_USER: postgres
                POSTGRES_PASSWORD: test
            ports:
                - "5432:5432"
    
            app:
            build: .
            container_name: springboot
            depends_on:
                - db
            ports:
                - "8080:8080"
        ```
## 6. Install JDK in Ubuntu
- Run the following commands to install OpenJDK 17 in Ubuntu:
    ```shell
    sudo apt update
    sudo apt install gnupg ca-certificates curl
    curl -s https://repos.azul.com/azul-repo.key | sudo gpg --dearmor -o /usr/share/keyrings/azul.gpg
    echo "deb [signed-by=/usr/share/keyrings/azul.gpg] https://repos.azul.com/zulu/deb stable main" | sudo tee /etc/apt/sources.list.d/zulu.list
    sudo apt update
    sudo apt install zulu17-jdk
    echo 'export JAVA_HOME=/usr/lib/jvm/zulu17' >> ~/.bashrc
    echo 'export PATH=$PATH:$JAVA_HOME/bin' >> ~/.bashrc
    source ~/.bashrc
    sudo keytool -importcert -alias myCA -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -file zscaler.cer
    ```
  
## 7. Install zscaler in colima for macos
  Start Colima:  
  ```shell
  colima start
  ```
  Copy the Zscaler certificate to the Colima VM's home directory:  
  ```shell 
  scp zscaler.cer colima:~/zscaler.cer
  ```
  Move the certificate to the correct location with elevated privileges:  
  ```shell 
  colima ssh
  sudo mkdir -p /usr/local/share/ca-certificates
  sudo mv ~/zscaler.cer /usr/local/share/ca-certificates/zscaler.crt
  sudo update-ca-certificates
  exit
  ```
  Restart Colima:  
  ```shell 
  colima stop
  colima start
  ```

## 7. Build and Run the Application
- Build the application using the following command:
    ```shell
    ./gradlew build
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

