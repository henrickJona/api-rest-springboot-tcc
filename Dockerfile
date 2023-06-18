FROM openjdk:17
EXPOSE 8080
COPY --from=build /target/api-rest-springboot-tcc.jar api-rest-springboot-tcc.jar
#ADD target/api-rest-springboot-tcc.jar api-rest-springboot-tcc.jar
ENTRYPOINT ["java","-jar", "/api-rest-springboot-tcc.jar"]