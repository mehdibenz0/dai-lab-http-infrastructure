FROM amazoncorretto:21

WORKDIR /app
COPY api-server/target/api-1.0-jar-with-dependencies.jar /app/api.jar

CMD ["java", "-jar", "/app/api.jar"]
