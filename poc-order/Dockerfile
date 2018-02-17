FROM openjdk:8-jre

COPY build/libs/poc-order-*.jar /usr/app/poc-order.jar

WORKDIR /usr/app/

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "poc-order.jar"]