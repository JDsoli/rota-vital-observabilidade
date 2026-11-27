# Compila o projeto e executa os testes
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -B -ntp clean verify

# Executa a aplicaÃ§Ã£o
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/rota-vital-0.0.1-SNAPSHOT.jar app.jar

ENV SERVER_ADDRESS=0.0.0.0

EXPOSE 8080

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.31.0/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar

ENTRYPOINT ["java", "-jar", "app.jar"]