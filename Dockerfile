FROM maven:3.8.1-jdk-11-slim AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests


FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine
RUN mkdir /app
COPY --from=build /project/target/EpicGamesApi-0.0.1-SNAPSHOT.jar /app/application.jar
WORKDIR /app
CMD "java" "-jar" "application.jar"