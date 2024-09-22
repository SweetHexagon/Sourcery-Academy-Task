FROM openjdk:17-jdk-slim

WORKDIR /app

#COPY out/artifacts/library_jar/library.jar app.jar

COPY build/libs/library-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]