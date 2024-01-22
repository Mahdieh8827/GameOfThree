# Game of Three (GOT)

## Goal
The goal is to implement a game of skill and strategy where two independent units, referred to as players, communicate with each other using socket.

## Description
- Each player incepts a random whole number and sends it to the second player to start the game.
- The receiving player can choose to add either 1, 0, or -1 to make the number divisible by 3 and then divides it by three.
- The resulting whole number is then sent back to the original sender, and the process continues until one player reaches the number 1 after division.

Example:
- Player A starts with 27, adds -1 to make it 26, divides by 3, and sends 9 to Player B.
- Player B adds 1 to make it 10, divides by 3, and sends 3 to Player A.
- The game continues until one player reaches 1.

For each move, the system generates sufficient output, including the added value and the resulting number. Players can choose to play automatically or manually.

## Getting Started

These instructions will help you set up the project on your local machine for development and testing purposes. See deployment for notes on deploying the project on a live system.

### Prerequisites
- [Java Development Kit (JDK) 17 or later](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop)
- if you want to work with UI then it's better to install Node.js, but I put node_modules in repository.
```shell
  mvn npm install -g npm
```

### Installing & Running

#### Using Spring Boot
```shell
  mvn spring-boot:run
```

#### Using Docker-compose
```shell
mvn clean install
docker-compose -f docker-compose.yml up -d --build 
```
## Play Game
- Two players can play together on different ports in the browser with the nice UI.
  * Player 1: http://localhost:8084/
  * Player 2: http://localhost:8085/