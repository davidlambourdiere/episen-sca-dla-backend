FROM openjdk:13-jdk-alpine

WORKDIR /var/back

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/var/back/app.jar"]
