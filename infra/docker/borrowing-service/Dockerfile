FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY infra/docker/borrowing-service/build-context.xml ./pom.xml
COPY borrowing-service/pom.xml ./borrowing-service/
COPY shared/pom.xml ./shared/

RUN mvn -B dependency:go-offline -DskipTests

COPY borrowing-service ./borrowing-service
COPY shared ./shared

RUN mvn clean install -DskipTests

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/borrowing-service/target/borrowing-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]
