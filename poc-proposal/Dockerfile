FROM openjdk:8-jre

COPY build/libs/poc-proposal-*.jar /usr/app/poc-proposal.jar

WORKDIR /usr/app/

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "poc-proposal.jar"]