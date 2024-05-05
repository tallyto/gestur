# Estágio 1: Constrói o pacote JAR com o Maven
FROM maven:3.8.3-openjdk-17 AS builder

# Define o diretório de trabalho dentro do contêiner
WORKDIR /build

# Copia o arquivo pom.xml para o diretório de trabalho
COPY pom.xml .

# Copia todo o código-fonte para o diretório de trabalho
COPY src ./src

# Executa o comando Maven para empacotar o aplicativo
RUN mvn clean package -DskipTests

# Estágio 2: Cria a imagem Docker
FROM openjdk:17.0.1-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR construído no estágio anterior para o diretório de trabalho
COPY --from=builder /build/target/*.jar app.jar

# Expõe a porta 8080 do contêiner para acesso externo
EXPOSE 8080

# Comando a ser executado quando o contêiner for iniciado
CMD ["java", "-jar", "app.jar"]
