FROM openjdk:8-jdk-alpine

# Create temporary volume directory
VOLUME /tmp

# Add the executable file
ADD ./target/app-0.6.1.jar ./app/

# Add profile-specific external configuration for production environment
COPY ./src/main/resources/application-production.yml ./app/

# Run the application
CMD ["java", "-jar", "./app/app-0.6.1.jar"]
