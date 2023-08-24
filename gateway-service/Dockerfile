FROM maven:3.8.5-openjdk-17-slim AS builder
COPY ./ ./
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine
COPY --from=builder /target/gateway-service-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]