FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY infra/docker/book-inventory-service/build-context.xml ./pom.xml
COPY book-inventory-service/pom.xml ./book-inventory-service/
COPY shared/pom.xml ./shared/

RUN mvn -B dependency:go-offline -DskipTests

COPY book-inventory-service ./book-inventory-service
COPY shared ./shared

RUN mvn clean install -DskipTests

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/book-inventory-service/target/book-inventory-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]
