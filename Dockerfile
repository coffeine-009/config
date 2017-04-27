#- Base image -#
FROM java:8

# Author
MAINTAINER TheCoffeine, Inc <vitaliy.tsutsman@musician-virtuoso.com>

#- Default run command -#
CMD ["java", "-jar", "/opt/config.jar"]

#- Add new application version -#
ADD build/libs/config-*.jar /opt/config.jar
