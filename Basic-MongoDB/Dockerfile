# Use an official Gradle image to build the application
FROM gradle:8.11.1-jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and project files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Copy the Zscaler certificate
COPY zscaler.cer /usr/local/share/ca-certificates/zscaler.crt

# Install ca-certificates package and update the certificate authorities
RUN apt-get update && apt-get install -y ca-certificates && update-ca-certificates

# Add the Zscaler certificate to the Java trust store
RUN keytool -importcert -file /usr/local/share/ca-certificates/zscaler.crt -alias zscaler -cacerts -storepass changeit -noprompt

# Build the application
RUN ./gradlew build

# Use an official OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built application from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]