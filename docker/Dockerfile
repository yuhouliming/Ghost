
FROM java:8
VOLUME /tmp
COPY /target/docker-0.0.1-SNAPSHOT.jar docker.jar
RUN bash -c "touch /docker.jar"
EXPOSE 9994
ENTRYPOINT ["java","-jar","docker.jar"]