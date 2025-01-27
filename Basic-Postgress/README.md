# Spring Boot Application with PostgreSQL Database

This project demonstrates how to set up and run a Spring Boot application with a PostgreSQL database. The application leverages Docker and Docker Compose for managing dependencies and containerized deployment.

## Prerequisites

Before starting, ensure you have the following installed:

- Docker
- Docker Compose
- Java 17
- Gradle

If Docker is not installed, follow the instructions in the [Docker Installation Guide](https://docs.docker.com/get-docker/) or refer to this [PDF Guide for Docker Installation](https://github.com/cedric10101980/k8sDeploymets/blob/main/Docker/Docs/Installation/Docker%20Installation.pdf).

## Steps to Run the PostgreSQL Database Container and the Application Locally

### Running the PostgreSQL Database Container

1. Clone the repository:
   ```sh
   git clone https://github.com/cedric10101980/SpringBoot-JPA.git
   cd SpringBoot-JPA
   ```

2. Start the PostgreSQL container:
   ```sh
   docker-compose up -d bootstrap-postgress
   ```
   This command will start the PostgreSQL container and expose it on port `5432`.

### Running the Application Locally

1. Ensure the PostgreSQL container is running.

2. Update the `application.properties` file with the correct database connection details:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/postgresdb
   spring.datasource.username=postgres
   spring.datasource.password=test
   ```

3. Build the application using Gradle:
   ```sh
   ./gradlew build
   ```

4. Run the application:
   ```sh
   ./gradlew bootRun
   ```
   The application will start and be accessible at [http://localhost:8080](http://localhost:8080).

## Running the Application with Docker Compose

To run the entire application stack (Spring Boot application and PostgreSQL database) using Docker Compose:

1. Ensure Docker Compose is installed and running.

2. Start the stack:
   ```sh
   docker-compose up
   ```
   This command will start both the Spring Boot application and the PostgreSQL database.

3. Verify the application is running at [http://localhost:8080](http://localhost:8080).

## Stopping the Application

To stop the containers, press `Ctrl+C` in the terminal running `docker-compose up` or use the following command in a new terminal:
```sh
   docker-compose down
```

## Additional Notes

- For database migrations or schema setup, ensure appropriate scripts are configured in the project.
- Customize the `docker-compose.yml` file as needed for your specific environment.
- For troubleshooting, check the logs of the services using:
  ```sh
  docker-compose logs
  ```

Feel free to reach out for support or further guidance!

