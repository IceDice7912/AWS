FROM openjdk:11-jdk as builder
ARG JAR_FILE=./project.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]