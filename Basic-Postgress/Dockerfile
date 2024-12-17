# Use an official Gradle image to build the application
FROM gradle:8.11.1-jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and project files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copy the source code
COPY src src

# Copy the Zscaler certificate and install ca-certificates package
COPY zscaler.cer /usr/local/share/ca-certificates/zscaler.crt
RUN apt-get update && apt-get install -y ca-certificates \
    && chmod a+r /usr/local/share/ca-certificates/zscaler.crt \
    && update-ca-certificates \
    && keytool -importcert -file /usr/local/share/ca-certificates/zscaler.crt -alias zscaler -cacerts -storepass changeit -noprompt

# Build the application, skipping tests
RUN ./gradlew build --no-daemon --console=plain --stacktrace -x test

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/dboperations-0.0.1-SNAPSHOT.jar dboperations-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "dboperations-0.0.1-SNAPSHOT.jar"]