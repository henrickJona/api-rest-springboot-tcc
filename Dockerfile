FROM maven:3.6.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

COPY --from=build /target/spring-boot-docker spring-boot-docker
#ADD target/spring-boot-docker spring-boot-docker
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/spring-boot-docker"]