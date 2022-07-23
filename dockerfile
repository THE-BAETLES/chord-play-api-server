FROM adoptopenjdk/openjdk11:jdk-11.0.15_10
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]