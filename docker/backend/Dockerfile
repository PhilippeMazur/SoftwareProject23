# Use a base image with Java 16 installed
FROM adoptopenjdk/openjdk16

# Set the working directory to /app
WORKDIR /app

# Copy the .env file to the container
COPY target/classes/.env .

# Copy the packaged Spring Boot JAR file to the container
COPY target/backend-spring-0.0.1-SNAPSHOT.jar .

# Expose port 8080 for the Spring Boot application to listen on
EXPOSE 8080

# Set the database connection properties as environment variables
ENV SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/db_badge_of_honour
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=groep-8

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "backend-spring-0.0.1-SNAPSHOT.jar"]