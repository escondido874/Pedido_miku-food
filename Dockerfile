
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/Pedido_miku-food-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
