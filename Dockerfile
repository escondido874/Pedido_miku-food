FROM openjdk:19-jdk AS build
WORKDIR /app

COPY pom.xml .
COPY src src 
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests


FROM openjdk:19-jdk-slim
WORKDIR /app


COPY target/*.jar app.jar

COPY env.properties .
COPY wallet/ ./wallet/



EXPOSE 8081


ENTRYPOINT ["java", "-Dspring.config.import=file:env.properties", "-jar", "app.jar"]