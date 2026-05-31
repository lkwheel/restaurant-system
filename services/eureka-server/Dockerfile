# Stage 1: Build
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# 1. Copy ONLY the files eureka needs (No other microservice poms required!)
COPY pom.xml .
COPY services/eureka-server/pom.xml services/eureka-server/
COPY services/eureka-server/mvnw services/eureka-server/
COPY services/eureka-server/.mvn services/eureka-server/.mvn

# 2. Grant execution permissions and cache internet dependencies
RUN chmod +x ./services/eureka-server/mvnw && \
    ./services/eureka-server/mvnw dependency:go-offline -pl services/eureka-server -am

# 3. Copy the actual source code for eureka-server
COPY services/eureka-server/src services/eureka-server/src

# 4. Package the application inside the container using cached layers
RUN ./services/eureka-server/mvnw clean package -DskipTests -pl services/eureka-server -am

# Stage 2: Runtime
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy the final executable artifact
COPY --from=build /app/services/eureka-server/target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
