FROM openjdk:8-jre

COPY build/libs/poc-eureka-*.jar /usr/app/poc-eureka.jar

WORKDIR /usr/app/

EXPOSE 8000

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "poc-eureka.jar"]