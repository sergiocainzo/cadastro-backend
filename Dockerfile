# Etapa 1: Buildando o maven ao container
FROM maven:3.9.6-eclipse-temurin-17 as build

# Diretorio de trabalho
WORKDIR /app

# Copia codigo completo para container
COPY . .

# Comando para executar o deploy (Sem realizar testes)
RUN ./mvnw clean package -DskipTests

# Etapa 2:
FROM eclipse-temurin:17-jdk-alpine

# Diretorio de trabalho
WORKDIR /app

# Copia codigo completo do build anterior
COPY --from=build /app/target/*.jar app.jar

# Porta de exposição
EXPOSE 8080

# Comando para execução
ENTRYPOINT ["java", "-jar", "app.jar"]