FROM java:8

ADD build/libs/config-1.0.0.SNAPSHOT.jar /opt/config.jar

CMD ["java", "-jar", "/opt/config.jar"]
