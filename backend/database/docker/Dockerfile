FROM openjdk:17.0.1-jdk-slim
WORKDIR /database
ARG JAR_FILE="database-0.0.1-SNAPSHOT.jar"
COPY ${JAR_FILE} database.jar
ENTRYPOINT [ "java", "-jar", "database.jar" ]

