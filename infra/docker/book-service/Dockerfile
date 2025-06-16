FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY infra/docker/book-service/build-context.xml ./pom.xml
COPY book-service/pom.xml ./book-service/
COPY shared/pom.xml ./shared/

RUN mvn -B dependency:go-offline -DskipTests

COPY book-service ./book-service
COPY shared ./shared

RUN mvn clean install -DskipTests

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/book-service/target/book-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
