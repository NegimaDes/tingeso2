FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} paserv-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/paserv-0.0.1-SNAPSHOT.jar"]