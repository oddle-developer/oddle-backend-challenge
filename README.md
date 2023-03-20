# TECHNICAL RELEASE NOTE
The technical is using from this project
- Back-End: Spring Boot, Spring JPA, Hibernate, Spring Rest
- Database: mysql

# = SET UP ENVIROMENT
1. Import database:
- Please run script with init sql from .../src/main/resource/Database

2. Import project to eclipse
   *Change MySQL config from "application.properties"
   spring.datasource.username = {}
   spring.datasource.password = {}

*Change your port from "application.properties" (if any)
server.port={}

*Run As -> Java Application from WeatherAppApplication.java

*Open a browser and navigate to http://localhost:{port}/

3. APIs from our system
   GET http://localhost:{port}/weather?city= => find weather by city name
   POST http://localhost:{port}/weather/save => save weather data for retrieval
   GET http://localhost:{port}/weather/past?city={cityName}&startDate={startDate}&endDate={endDate} => look for weather data from past periods
   PUT http://localhost:{port}/weather => update an existing weather record
   DELETE http://localhost:{port}/weather/{id} => delete weather by id

4. Import Postman for testing
- Open postman then click button import, choose the file from .../src/main/resource/postman/oddle.postman_collection.json
