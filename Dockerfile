FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/Noter-1.0-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} noter.jar
ENTRYPOINT ["java","-jar","noter.jar"]