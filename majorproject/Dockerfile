FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package -Dmaven.test.skip=true -Pprod
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD build/target/majorproject-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
EXPOSE 3306
ENTRYPOINT ["java", "-jar", "-Dspring.datasource.url=jdbc:mariadb://${MARIADB_HOST}:3306/project?createDatabaseIfNotExist=true", "majorproject-0.0.1-SNAPSHOT.jar"]