FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY infra/docker/user-profile-service/build-context.xml ./pom.xml
COPY user-profile-service/pom.xml ./user-profile-service/
COPY shared/pom.xml ./shared/

RUN mvn -B dependency:go-offline -DskipTests

COPY user-profile-service ./user-profile-service
COPY shared ./shared

RUN mvn clean install -DskipTests

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/user-profile-service/target/user-profile-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]
