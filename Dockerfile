FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21

WORKDIR /opt/app
COPY --from=builder /app/target/books.jar books.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "books.jar"]
