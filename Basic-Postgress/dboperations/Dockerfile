# Stage 1: Build the application
FROM openjdk:17-slim AS build
WORKDIR /app
COPY . .

COPY zscaler.cer .

# Install ca-certificates package
RUN apt-get update && apt-get install -y ca-certificates

# Change permissions of the .cer file to be readable by all users
RUN chmod a+r zscaler.cer

# Convert the .cer file to .pem
COPY zscaler.cer /usr/local/share/ca-certificates/zscaler.crt

# Update the certificate authorities
RUN update-ca-certificates

# Use the Gradle wrapper to build the project
RUN ./gradlew build --no-daemon --console=plain > /dev/stdout

# Stage 2: Run the application
FROM openjdk:17-slim-buster
WORKDIR /app
COPY --from=build /app/build/libs/dboperations-0.0.1-SNAPSHOT.jar dboperations-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "dboperations-0.0.1-SNAPSHOT.jar"]