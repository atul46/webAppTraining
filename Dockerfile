FROM openjdk:8-jdk-alpine
ADD  /var/lib/jenkins/workspace/jenikinsTraning/target/empdetails.jar  empdetails.jar 
EXPOSE 8085
ENTRYPOINT ["java","-jar","empdetails.jar"]
