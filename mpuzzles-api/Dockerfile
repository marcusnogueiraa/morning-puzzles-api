 
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .

RUN apt-get install maven -y
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /target/mpuzzles-api-0.0.1-SNAPSHOT.jar app.jar
COPY --from=build target/classes/sudoku_set.txt sudoku_set.txt
COPY --from=build target/classes/last_sudoku.json last_sudoku.json

ENTRYPOINT ["java", "--add-opens=java.base/java.time=ALL-UNNAMED", "-jar", "app.jar"]