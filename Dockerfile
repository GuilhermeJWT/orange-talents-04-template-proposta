FROM openjdk:11-jdk
LABEL maintainer="Guilherme Joaquim dos Santos"
ARG JAR_FILE=target/proposta-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} proposta.jar
ENTRYPOINT ["java","-jar","/proposta.jar"]
EXPOSE $PROPOSTA_SERVER_PORT