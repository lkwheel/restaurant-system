# Stage 1: Build
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# 1. Copy the root pom and ALL module poms so Maven can resolve the multi-module tree
COPY pom.xml .
COPY common/common-lib/pom.xml common/common-lib/
COPY services/eureka-server/pom.xml services/eureka-server/
COPY services/restaurant-service/pom.xml services/restaurant-service/
COPY services/food-catalog-service/pom.xml services/food-catalog-service/
COPY services/order-service/pom.xml services/order-service/
COPY services/user-service/pom.xml services/user-service/

# 2. Copy the wrapper tool and configuration from your service
COPY services/food-catalog-service/mvnw services/food-catalog-service/
COPY services/food-catalog-service/.mvn services/food-catalog-service/.mvn

# 3. Copy the source of common-lib (Required if food-catalog depends on it)
COPY common/common-lib/src common/common-lib/src

# 4. Grant permissions and cache dependencies safely
RUN chmod +x ./services/food-catalog-service/mvnw && \
    ./services/food-catalog-service/mvnw dependency:go-offline -pl services/food-catalog-service -am

# 5. Copy the actual source code for food-catalog-service
COPY services/food-catalog-service/src services/food-catalog-service/src

# 6. Package the application
RUN ./services/food-catalog-service/mvnw clean package -DskipTests -pl services/food-catalog-service -am

# Stage 2: Runtime
FROM eclipse-temurin:25-jre
WORKDIR /app

COPY --from=build /app/services/food-catalog-service/target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
