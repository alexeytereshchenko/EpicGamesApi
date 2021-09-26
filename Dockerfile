FROM maven:3.8.1-jdk-11-slim AS builder
WORKDIR /project
COPY . .
RUN ["mvn", "clean", "package", "-DskipTests"]

FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine
WORKDIR /app
COPY --from=builder /project/target/EpicGamesApi.jar app.jar
CMD ["java", "-jar", "app.jar"]
