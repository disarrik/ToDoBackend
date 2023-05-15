FROM gradle:7.6.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/ToDoBackend-0.0.1-SNAPSHOT-plain.jar /app/
RUN bash -c 'touch /app/ToDoBackend-0.0.1-SNAPSHOT-plain.jar'
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/ToDoBackend-0.0.1-SNAPSHOT-plain.jar"]