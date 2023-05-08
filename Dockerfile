FROM openjdk:20-bullseye
ADD delivery/infrastructures/target/infrastructures-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar infrastructures-0.0.1-SNAPSHOT.jar

