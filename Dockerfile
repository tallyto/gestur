FROM openjdk:17.0.1-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
