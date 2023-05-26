FROM maven:3.9.2-eclipse-temurin-20 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src /app/src
RUN mvn package
RUN mvn install

FROM openjdk:21-ea-22-jdk-oraclelinux8
WORKDIR /app
COPY --from=build /app/target/ConKUeror-1.0-SNAPSHOT.jar ConKUeror-1.0-SNAPSHOT.jar
CMD ["java", "-jar", "ConKUeror-1.0-SNAPSHOT.jar"]
