# Spring Boot training

We wrote this app to learn how to test drive a Spring Boot app. We started with a REST API, added web pages, and then security.

## Run the app

Start a mysql server locally and configure in `src/main/resources/application-local.yml`. The app
requires one database.

`export SPRING_PROFILES_ACTIVE=local`, or run `direnv allow`. To tell Spring to use the local profile.

`./gradlew bootRun`
or
`mvn spring-boot:run`

## Run the tests

Unit tests
`./gradlew clean test` or `mvn test`

Integration tests
`./gradlew clean integrationTest` or `mvn clean verify -P integration-test`

All tests
`./gradlew clean test integrationTest` or `mvn clean test && mvn clean verify -P integration-test`

## Deploy to Cloud Foundry

First time set up:

- Create a MySQL service called "mysql"
- `cf set-env SECURITY_OAUTH2_CLIENT_CLIENT_ID <clientId>`
- `cf set-env SECURITY_OAUTH2_CLIENT_CLIENT_SECRET <clientSecret>`

For each deployment:

- `./gradlew clean bootRepackage` or `mvn clean package`
- `cf push`