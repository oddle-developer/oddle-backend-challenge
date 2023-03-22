# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/gradle-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

#########--How to Use--######
Make sure you set "spring.profile.active" is "dev" to get the config from application-dev.properties. However, you still can set profile to the one you want and remember to add config to the corresponding application properties.
And then update config username and password in properties file to your local Mysql account

if you don't want table to be auto generated then set "spring.jpa.hibernate.ddl-auto" to none and run script.sql

For API Documentation,you can go to localhost:8500/swagger-ui.html for all informations

