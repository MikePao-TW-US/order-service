FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/orderservice-0.0.1-SNAPSHOT.jar orderservice.jar
EXPOSE 8080
CMD ["java","-jar","orderservice.jar"]
