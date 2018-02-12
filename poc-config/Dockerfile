FROM openjdk:8-jre

COPY build/libs/poc-config-*.jar /usr/app/poc-config.jar

WORKDIR /usr/app/

EXPOSE 8888

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "poc-config.jar"]