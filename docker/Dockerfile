FROM eclipse-temurin:21-jre-jammy AS final
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
