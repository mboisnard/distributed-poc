FROM openjdk:8-jre

COPY build/libs/poc-customer-*.jar /usr/app/poc-customer.jar

WORKDIR /usr/app/

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "poc-customer.jar"]