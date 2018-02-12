FROM openjdk:8-jre

COPY build/libs/poc-agent-*.jar /usr/app/poc-agent.jar

WORKDIR /usr/app/

EXPOSE 8888

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "poc-agent.jar"]