FROM openjdk
EXPOSE 8080
COPY ./build/libs/ToDoBackend-0.0.1-SNAPSHOT-plain.jar /app/
RUN bash -c 'touch /app/ToDoBackend-0.0.1-SNAPSHOT-plain.jar'
ENTRYPOINT ["java", "-jar", "/app/ToDoBackend-0.0.1-SNAPSHOT-plain.jar"]