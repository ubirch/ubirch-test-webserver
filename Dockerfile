FROM openjdk:8-jre
MAINTAINER Denis Lavrushko <denis.lavrushko@iits-consulting.de>

EXPOSE 8081

ENTRYPOINT ["java", "-XX:+PrintFlagsFinal", "$JAVA_OPTIONS", "-jar", "/usr/share/ubirch/test-webserver.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
ADD target/lib           /usr/share/ubirch/lib
# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/ubirch/test-webserver.jar
