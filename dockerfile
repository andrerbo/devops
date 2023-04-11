FROM openjdk:17-oracle
VOLUME /home/myclass
EXPOSE 8080
ARG JAR_FILE=target/myclass-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} myclass.jar
ENTRYPOINT ["java","-jar","/myclass.jar"]