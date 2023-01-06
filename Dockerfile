FROM openjdk:8-jdk-alpine
WORKDIR /target/blogging-application.jar
COPY . /target/blogging-application.jar
CMD java -jar target/blogging-application.jar