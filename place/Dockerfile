FROM openjdk:8-jre-alpine
EXPOSE 8081
ADD build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]