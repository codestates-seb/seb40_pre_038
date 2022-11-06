FROM adoptopenjdk/openjdk11
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE=build/libs/pre038-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} pre-project.jar
ENTRYPOINT ["java", "-jar", "/pre-project.jar"]