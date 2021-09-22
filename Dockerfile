FROM openjdk:8-jre
MAINTAINER Denis Lavrushko <denis.lavrushko@iits-consulting.de>

ENV JAVA_HOME /usr/local/openjdk-8
EXPOSE 8081

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/myservice/myservice.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
ADD target/lib           /usr/share/myservice/lib
# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/myservice/myservice.jar
