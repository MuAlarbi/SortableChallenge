FROM maven:3.6.1-jdk-14

RUN mkdir /app
WORKDIR /app

COPY pom.xml pom.xml
COPY src src
RUN mvn clean compile assembly:single

CMD ["java", "-jar", "target/auction-1.0-SNAPSHOT-jar-with-dependencies.jar"]