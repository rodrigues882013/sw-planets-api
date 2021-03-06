FROM anapsix/alpine-java

WORKDIR /app
ADD target/sw-planets-api.jar /app/sw-planets-api.jar

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/sw-planets-api.jar"]

EXPOSE 8080