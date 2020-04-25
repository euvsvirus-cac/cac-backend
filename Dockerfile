FROM adoptopenjdk/openjdk11:alpine-slim

RUN addgroup -S spring && adduser -S spring -G spring && \
    mkdir -p /var/app && chown -R spring:spring /var/app
USER spring:spring

WORKDIR /var/app

EXPOSE 8080

ARG JAR_FILE
COPY target/${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]
