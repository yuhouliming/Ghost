
FROM java:8
VOLUME /tmp
COPY /target/dockerDemo.jar dockerDemo.jar
RUN bash -c "touch /dockerDemo.jar"
EXPOSE 8889 9998
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9998","-jar","dockerDemo.jar"]