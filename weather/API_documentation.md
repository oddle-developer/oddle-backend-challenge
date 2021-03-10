# API Documentation

### GET check status

url:

    http://localhost:8080/api/v1/weather/status

### GET current weather

url:
http://localhost:8080/api/v1/weather/current/{city_name}

### GET weather history

url:

    http://localhost:8080/api/v1/weather/list

### POST weather

url:

    http://localhost:8080/api/v1/weather/save

body request (json):

```json
{
  "cityName": "London",
  "temperature": 10.1,
  "mainWeather": "Clear",
  "descWeather": "clear sky"
}
```

### PUT weather history

url:

    http://localhost:8080/api/v1/weather/update/{id}

body request (json):

```json
{
  "cityName": "Utrecht",
  "temperature": 7.73,
  "mainWeather": "Clouds",
  "descWeather": "overcast clouds"
}
```

### DELETE weather history

url:

    http://localhost:8080/api/v1/weather/delete/{id}
