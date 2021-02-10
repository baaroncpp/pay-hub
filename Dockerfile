FROM openjdk:11-ea-13-jre

LABEL maintainer="starnapho@gmail.com"
LABEL version="1.0"
LABEL description="Pay Hub"

COPY ./core/build/libs/*.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]