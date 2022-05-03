FROM openjdk:17-oracle
VOLUME /tmp
ARG JAR_FILE=target/event-service-sender-1.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]