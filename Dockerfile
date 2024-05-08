FROM maven:latest

RUN mkdir /app
WORKDIR /app

COPY . /src

COPY pom.xml ./

RUN mvn clean package -e

ENTRYPOINT ["java", "-Xmx2048M", "-jar", "target/mailer-0.0.1-SNAPSHOT.jar"]
