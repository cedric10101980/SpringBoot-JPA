# Stage 1: Build the application
FROM openjdk:17-slim AS build
WORKDIR /app
COPY . .

COPY zscaler.cer .

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Copy the Zscaler certificate
COPY zscaler.cer /usr/local/share/ca-certificates/zscaler.crt

# Install ca-certificates package and update the certificate authorities
RUN apt-get update && apt-get install -y ca-certificates && update-ca-certificates

# Add the Zscaler certificate to the Java trust store
RUN keytool -importcert -file /usr/local/share/ca-certificates/zscaler.crt -alias zscaler -cacerts -storepass changeit -noprompt

# Use the Gradle wrapper to build the project
RUN ./gradlew build --no-daemon --console=plain > /dev/stdout

# Stage 2: Run the application
FROM openjdk:17-slim-buster
WORKDIR /app
COPY --from=build /app/build/libs/dboperations-0.0.1-SNAPSHOT.jar dboperations-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "dboperations-0.0.1-SNAPSHOT.jar"]