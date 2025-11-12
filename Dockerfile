# Dockerfile
# Стадия сборки
FROM gradle:8.4.0-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle build -x test

# Стадия запуска
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
# Копируем сертификаты из исходников
COPY src/main/resources/certs/ ./certs/

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]