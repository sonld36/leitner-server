FROM openjdk:17
VOLUME /tmp
WORKDIR /app
EXPOSE 8080
ARG JAR_FILE=build/libs/leitner-system-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} leitner-system.jar
ENTRYPOINT ["java","-jar","leitner-system.jar"]
CMD ["/bin/sh -c"]
LABEL authors="sonld"