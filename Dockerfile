FROM openjdk:17
LABEL authors="mahdieh"
WORKDIR /app
COPY ./game-of-three-controller/target/game-of-three-controller-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8084
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "game-of-three-controller-0.0.1-SNAPSHOT.jar"]




