FROM openjdk:17-alpine

WORKDIR /app

RUN apk update && \
    apk add maven

COPY pom.xml .

RUN mvn dependency:go-offline

COPY . .

RUN mvn clean install -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/ShopComputerPTD-0.0.1-SNAPSHOT.jar"]