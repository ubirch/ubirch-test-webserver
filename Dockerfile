FROM openjdk:8-jre
MAINTAINER Denis Lavrushko <denis.lavrushko@iits-consulting.de>

EXPOSE 8081
ENTRYPOINT ["java", "-XX:+PrintFlagsFinal", "-jar", "/usr/share/ubirch/test-webserver.jar"]


ADD target/lib /usr/share/ubirch/lib
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/ubirch/test-webserver.jar
