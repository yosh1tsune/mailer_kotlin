FROM maven:latest

RUN mkdir /app
WORKDIR /app

COPY ./src ./src

COPY pom.xml ./

RUN mvn clean package -e
