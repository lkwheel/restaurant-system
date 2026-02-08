# Stage 1: Build
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# Copy the entire monorepo context
COPY . .

# Update the path to chmod and run the specific service's wrapper
# We use the -pl flag to tell Maven which project to build
RUN chmod +x ./services/restaurant-service/mvnw && \
    ./services/restaurant-service/mvnw clean package -DskipTests -pl services/restaurant-service -am

# Stage 2: Runtime
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy the jar from the specific service target folder
COPY --from=build /app/services/restaurant-service/target/*.jar app.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]