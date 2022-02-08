# API documentation

# 1. Get today weather of a specific city
* path : "/weather"
* HTTP Method: GET
* parameter : 
    city (required)
* Request body : none
* Response body :
    {
        "date": "2022-02-09 00:21:07",
        "message": "OK",
        "data": {
            "id": null,
            "city": "Denmark",
            "weather": "Clouds",
            "description": "overcast clouds",
            "icon": "04d"
        }
    }

# 2. Save weather data
* path : "/weather"
* HTTP Method: POST
* Parameter : none
* Request body :
    {
        "city": "Badung",
        "weather": "Clouds",
        "description": "broken clouds",
        "icon": "04n"
    }
* Response body :
    {
        "date": "2022-02-09 00:25:47",
        "message": "Data successfully created!",
        "data": {
            "id": 1,
            "city": "Badung",
            "weather": "Clouds",
            "description": "broken clouds",
            "icon": "04n"
        }
    }

# 3. Get historical weather data
* path : "/weathers"
* HTTP Method: GET
* Parameter : 
    page (optional, default=1)
    limit (optional, default=10)
* Request body : none
* Response body :
    {
        "date": "2022-02-09 00:28:02",
        "message": "OK",
        "data": [
            {
                "id": 1,
                "city": "Badung",
                "weather": "Clouds",
                "description": "broken clouds",
                "icon": "04n"
            }
        ]
    }

# 4. Update historical weather data
* path : "/weather/{id}"
* HTTP Method: PUT
* Parameter : none
* Request body : 
    {
        "city": "Badung",
        "weather": "Rain",
        "description": "heavy rain",
        "icon": "04d"
    }
* Response body :
    {
        "date": "2022-02-09 00:30:58",
        "message": "Data successfully updated!",
        "data": {
            "id": 1,
            "city": "Badung",
            "weather": "Rain",
            "description": "heavy rain",
            "icon": "04d"
        }
    }

# 5. Delete historical weather data
* path : "/weather/{id}"
* HTTP Method: DELETE
* Parameter : none
* Request body : none
* Response body :
    {
        "date": "2022-02-09 00:32:52",
        "message": "Data successfully removed!",
        "data": {
            "id": 1,
            "city": "Badung",
            "weather": "Rain",
            "description": "heavy rain",
            "icon": "04d"
        }
    }