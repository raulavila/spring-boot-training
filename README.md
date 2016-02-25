# Spring Boot training

We wrote this app to learn how to test drive a Spring Boot app. We started with a REST API, added web pages, and then security.

## Run the app

Start a mysql server locally and configure in `src/main/resources/application-local.yml`. The app
requires one database.

Set the local environment variables:

```
export SPRING_PROFILES_ACTIVE=local  # To tell Spring to use the local profile.

export SECURITY_OAUTH2_CLIENT_ID=REPLACE_WITH_GITHUB_CLIENT_ID
export SECURITY_OAUTH2_CLIENT_SECRET=REPLACE_WITH_GITHUB_CLIENT_SECRET
```

Or copy `.envrc.example to `.envrc`, fill in your GitHub client secrets and run `direnv allow`.

`./gradlew bootRun`

## Run the tests

- `./gradlew clean test` to run unit tests only
- `./gradlew clean integrationTest` to run integration tests only
- `./gradlew clean test integrationTest` to run all tests

## Deploy to Cloud Foundry

First time set up:

- Create a MySQL service called "mysql"
- `cf set-env SECURITY_OAUTH2_CLIENT_CLIENT_ID <clientId>`
- `cf set-env SECURITY_OAUTH2_CLIENT_CLIENT_SECRET <clientSecret>`

For each deployment:

- `./gradlew clean bootRepackage`
- `cf push`