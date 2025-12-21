# Challenge App

A lightweight Spring Boot REST API for managing monthly challenges (create, read, update, delete).

## Table of contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Project structure](#project-structure)
- [Install & build](#install--build)
- [Run](#run)
- [API endpoints](#api-endpoints)
- [Testing](#testing)
- [Tech stack](#tech-stack)
- [Author](#author)
- [License](#license)

## Overview

Challenge App stores monthly challenges (month + description). It exposes simple REST endpoints and uses an in-memory list in the service layer for demonstration and testing.

## Prerequisites

- Java 11+
- Maven 3.6+
- Git (optional)

## Project structure

- `src/main/java/com/soumya/ChallengeApp/`
  - `Challenge.java` — entity model
  - `ChallengeService.java` — business logic
  - `ChallengeController.java` — REST controller
- `src/test/java/com/soumya/ChallengeApp/` — unit tests
- `pom.xml` — Maven config
- `README.md` — this file

## Install & build

Clone and build:

```bash
git clone https://github.com/soumyaranjan12321/Java-spring-boot-challenge-app.git
cd Java-spring-boot-challenge-app
mvn clean install
```
## Run
Start with Maven:
```bash 
mvn spring-boot:run
```
Or run the packaged jar (after mvn package):
```bash 
java -jar target/challenge-app.jar
```
Default base URL: http://localhost:8080

## API endpoints
Base path: /challenges
- Get all challenges\
  GET /challenges\
  Response: 200 OK with JSON array of challenges.
- Add a challenge
  POST /challenges\
  Request JSON:\
```bash 
{ "month": "January", "description": "Learn Java" }
```
Response: 200 OK on success, 400 Bad Request on failure.
- Get challenges by month (case-insensitive)\
GET /challenges/{month}
Response: 200 OK with matching list or 404 Not Found.
- Update a challenge\
PUT /challenges/{id}\
Request JSON:
```bash 
{ "month": "February", "description": "Build a project" }
```
Response: 200 OK on success, 404 Not Found if id not found.
- Delete a challenge\
DELETE /challenges/{id}\
Response: 200 OK on success, 404 Not Found if id not found.

## Testing
Run unit tests:
```bash 
mvn test
```
Run a specific test class:
```bash 
mvn -Dtest=com.soumya.ChallengeApp.ChallengeServiceTest test
```
In IntelliJ IDEA: open the test class and run from the editor.

## Tech stack
- Java
- Spring Boot
- Maven
- JUnit 5

## Author
soumyaranjan12321
GitHub: https://github.com/soumyaranjan12321

## License
MIT
