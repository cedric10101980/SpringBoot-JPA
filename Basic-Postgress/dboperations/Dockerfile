# Stage 1: Build the application
FROM gradle:7.5.1-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Stage 2: Run the application
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/build/libs/dboperations-0.0.1-SNAPSHOT.jar dboperations-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "dboperations-0.0.1-SNAPSHOT.jar"]