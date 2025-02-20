FROM openjdk:21-jdk-slim
COPY target/*.jar gestionClient.jar
EXPOSE 8080
CMD ["java", "-jar", "/gestionClient.jar"]
