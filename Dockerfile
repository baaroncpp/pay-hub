FROM openjdk:13-ea-16-jdk-alpine3.9

LABEL maintainer="starnapho@gmail.com"
LABEL version="1.0"
LABEL description="Pay Hub"

COPY ./core/build/libs/*.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]