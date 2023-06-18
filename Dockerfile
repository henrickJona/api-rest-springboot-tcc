FROM maven:3.6.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

COPY --from=build /target/api-rest-springboot-tcc.jar api-rest-springboot-tcc.jar
#ADD target/api-rest-springboot-tcc.jar api-rest-springboot-tcc.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/api-rest-springboot-tcc.jar"]