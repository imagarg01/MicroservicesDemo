FROM openjdk:17-slim
COPY target/inventory-service-1.0.0.jar /app.jar
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:ActiveProcessorCount=1", "-XX:+UseG1GC", "-Xmx1536m", "-Xss512k", "-jar", "/app.jar"]
