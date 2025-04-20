FROM openjdk:17-slim
COPY target/order-service-1.0.0.jar /app.jar
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:ActiveProcessorCount=2", "-XX:+UseG1GC", "-Xmx3g", "-Xss512k", "-jar", "/app.jar"]
