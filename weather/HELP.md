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
up your mysql server localhost with port 3306 and go to application.properties, change the username and password connection to your local database and make sure the value of spring.profiles.active is local for auto generation of tables. if you dont want to auto generate tables, go to application-local.properties and change the value of spring.jpa.hibernate.ddl-auto to none and run script.sql found in the project.

for the api blueprint run the springboot application and go to localhost:8080/swagger-ui.html

