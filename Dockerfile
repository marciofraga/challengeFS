FROM maven:3.6-jdk-14 as MAVEN_BUILD

COPY pom.xml /tmp
COPY src /tmp/src/

WORKDIR /tmp/
RUN mvn package

FROM openjdk:14-jdk-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /tmp/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]