FROM openjdk:11
EXPOSE 9000
ADD target/spring-docker.war spring-docker.war
ENTRYPOINT ["java","-war","/spring-docker.war"]