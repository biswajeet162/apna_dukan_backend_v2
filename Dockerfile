# Build stage
FROM maven:3.8.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
# Download dependencies first (cached layer)
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Create data directory for H2 file database
RUN mkdir -p /app/data

COPY --from=build /app/target/*.jar app.jar

# Render uses the PORT environment variable
# EXPOSE is just documentation - Render will use the PORT env var
EXPOSE 8080

# Run with production profile
# Use shell form to properly expand ${PORT} environment variable
# Render automatically sets PORT env var
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8080} -Dspring.profiles.active=prod -jar app.jar"]

