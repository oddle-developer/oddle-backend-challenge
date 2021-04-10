DELIMITER $$
CREATE PROCEDURE addCity(OUT cityId VARCHAR(36))
BEGIN
    DECLARE currentId VARCHAR(36);
    SELECT id INTO currentId FROM city WHERE UPPER(name) = 'HANOI';
    IF currentId is null THEN
        SET cityId = UUID();
        INSERT INTO oddle.city(id, latitude, longitude, name, create_time, update_time)
        VALUES (cityId, 21.028511, 105.804817, 'Hanoi', UTC_TIMESTAMP(), UTC_TIMESTAMP());
    ELSE
        SET cityId = currentId;
    END IF;
END $$
DELIMITER ;

CALL addCity(@city_id);

INSERT INTO oddle.weather(id, condition_description, humidity, temp_avg, temp_max, temp_min, visibility,
                          weather_condition, wind_speed, city_id, create_time, update_time)
VALUES (UUID(), 'broken clouds', 78, 296.15, 296.15, 296.15, 10000, 'Clouds', 5.14, @city_id, UTC_TIMESTAMP(), UTC_TIMESTAMP());


INSERT INTO oddle.weather(id, condition_description, humidity, temp_avg, temp_max, temp_min, visibility,
                          weather_condition, wind_speed, city_id, create_time, update_time)
VALUES (UUID(), 'broken clouds', 78, 296.15, 296.15, 296.15, 10000, 'Clouds', 5.14, @city_id, UTC_TIMESTAMP(), UTC_TIMESTAMP());


INSERT INTO oddle.weather(id, condition_description, humidity, temp_avg, temp_max, temp_min, visibility,
                          weather_condition, wind_speed, city_id, create_time, update_time)
VALUES (UUID(), 'broken clouds', 91, 296.15, 296.15, 296.15, 10000, 'Clouds', 5.14, @city_id, UTC_TIMESTAMP(), UTC_TIMESTAMP());


INSERT INTO oddle.weather(id, condition_description, humidity, temp_avg, temp_max, temp_min, visibility,
                          weather_condition, wind_speed, city_id, create_time, update_time)
VALUES (UUID(), 'heavy rain', 78, 296.15, 296.15, 296.15, 10000, 'Clouds', 5.14, @city_id, UTC_TIMESTAMP(), UTC_TIMESTAMP());


INSERT INTO oddle.weather(id, condition_description, humidity, temp_avg, temp_max, temp_min, visibility,
                          weather_condition, wind_speed, city_id, create_time, update_time)
VALUES (UUID(), 'broken clouds', 95, 296.15, 296.15, 296.15, 10000, 'Clouds', 5.14, @city_id, UTC_TIMESTAMP(), UTC_TIMESTAMP());


INSERT INTO oddle.weather(id, condition_description, humidity, temp_avg, temp_max, temp_min, visibility,
                          weather_condition, wind_speed, city_id, create_time, update_time)
VALUES (UUID(), 'broken clouds', 78, 296.42, 240.20, 296.15, 10000, 'Clouds', 5.14, @city_id, UTC_TIMESTAMP(), UTC_TIMESTAMP());


INSERT INTO oddle.weather(id, condition_description, humidity, temp_avg, temp_max, temp_min, visibility,
                          weather_condition, wind_speed, city_id, create_time, update_time)
VALUES (UUID(), 'heavy rain', 70, 296.15, 280.89, 296.00, 10000, 'Clouds', 5.14, @city_id, UTC_TIMESTAMP(), UTC_TIMESTAMP());


INSERT INTO oddle.weather(id, condition_description, humidity, temp_avg, temp_max, temp_min, visibility,
                          weather_condition, wind_speed, city_id, create_time, update_time)
VALUES (UUID(), 'wet', 78, 296.15, 289.15, 296.00, 10000, 'Clouds', 5.14, @city_id, UTC_TIMESTAMP(), UTC_TIMESTAMP());


INSERT INTO oddle.weather(id, condition_description, humidity, temp_avg, temp_max, temp_min, visibility,
                          weather_condition, wind_speed, city_id, create_time, update_time)
VALUES (UUID(), 'sunny', 78, 290.15, 293.15, 298.15, 9000, 'Clouds', 3.14, @city_id, UTC_TIMESTAMP(), UTC_TIMESTAMP());
