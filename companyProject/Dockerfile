FROM maven:3.9.4 AS builder

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /build/target/companyProject-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=9000
EXPOSE 9000

ENTRYPOINT ["java", "-jar", "app.jar"]
