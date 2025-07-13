FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests
#RUN mvn clean package -Dspring-boot.run.profiles=prod -DskipTests

FROM openjdk:21

WORKDIR /app

COPY --from=builder /app/target/*.jar ifind.jar

EXPOSE 8080

COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

ENTRYPOINT ["/wait-for-it.sh", "meu_mysql:3306", "--", "java", "-Dspring.profiles.active=prod", "-jar", "ifind.jar"]