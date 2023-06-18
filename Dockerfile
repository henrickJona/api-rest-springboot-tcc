FROM openjdk:17-jdk-slim

COPY --from=build /target/api-rest-springboot-tcc.jar api-rest-springboot-tcc.jar
#ADD target/api-rest-springboot-tcc.jar api-rest-springboot-tcc.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/api-rest-springboot-tcc.jar"]