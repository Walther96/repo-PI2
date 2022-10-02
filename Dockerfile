FROM openjdk:11.0.4-jre-slim
COPY "./target/rutas-0.0.1-SNAPSHOT.jar" "appRutas.jar"
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "appRutas.jar"]