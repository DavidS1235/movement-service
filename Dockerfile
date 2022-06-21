FROM openjdk:8
VOLUME /tmp
EXPOSE 8006
ADD ./target/movement-service-0.0.1-SNAPSHOT.jar movement-service.jar
ENTRYPOINT ["java", "-jar", "movement-service.jar"]