CREATE DATABASE weather;

USE weather;

CREATE TABLE `hibernate_sequence` (
    `next_val` bigint DEFAULT NULL
);

CREATE TABLE weather(
	`weather_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`base` VARCHAR(200) DEFAULT '',
	`visibility` INT DEFAULT '0',
	`date_time_calculation` TIMESTAMP NULL,
	`timezone` INT DEFAULT '0',
	`name` VARCHAR(200) DEFAULT '',
	`cod` INT  DEFAULT 0,
	`weather_coordinates_id` BIGINT NOT NULL,
	`weather_main_id` BIGINT,
	`weather_wind_id` BIGINT,
	`weather_cloud_id` BIGINT ,
	`weather_rain_id` BIGINT,
	`weather_snow_id` BIGINT ,
	`weather_sun_id` BIGINT,
	`created_date` TIMESTAMP NULL DEFAULT NOW(),
	`updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE weather_coordinates(
	`weather_coordinates_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`latitude` DECIMAL(10,4) DEFAULT '0.0000',
	`longitude` DECIMAL(10,4) DEFAULT '0.0000',
	`created_date` TIMESTAMP NULL DEFAULT NOW(),
	`updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE weather_code(
	`weather_code_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `weather_code_main` ENUM ("RAIN","SNOW","EXTREME","THUNDERSTORM","DRIZZLE","MIST","SMOKE","HAZE","DUST","FOG","SAND","ASH","SQUALL","TORNADO","CLEAR","CLOUD"),
    `weather_description` VARCHAR(200),
    `weather_icon` VARCHAR(20),
	`created_date` TIMESTAMP NULL DEFAULT NOW(),
	`updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE weather_map_weather_code(
	`weather_id` BIGINT,
	`weather_code_id` BIGINT,
	`created_date` TIMESTAMP NULL DEFAULT NOW(),
	`updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(weather_id,weather_code_id)
);

CREATE TABLE weather_main(
	`weather_main_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `temperature` DECIMAL(10,2) DEFAULT 0.00,
    `feels_like` DECIMAL(10,2) DEFAULT 0.00,
	`temperature_min` DECIMAL(10,2) DEFAULT 0.00,
	`temperature_max` DECIMAL(10,2) DEFAULT 0.00,
    `pressure` INT DEFAULT 0,
    `humidity` INT DEFAULT 0,
	`sea_level` INT DEFAULT 0,
    `ground_level` INT DEFAULT 0,
	`created_date` TIMESTAMP NULL DEFAULT NOW(),
	`updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE weather_wind(
	`weather_wind_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `speed` DECIMAL(10,2) DEFAULT 0.00,
    `gust` DECIMAL(10,2) DEFAULT 0.00,
	`direction` DECIMAL(10,2) DEFAULT 0.00,
	`created_date` TIMESTAMP NULL DEFAULT NOW(),
	`updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE weather_cloud(
	`weather_cloud_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `all_percentages` DECIMAL(10,2) DEFAULT 0.00,
	`created_date` TIMESTAMP NULL DEFAULT NOW(),
	`updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE weather_rain(
	`weather_rain_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `rain_one_hour` DECIMAL(10,2) DEFAULT 0.00,
	`rain_three_hours` DECIMAL(10,2) DEFAULT 0.00,
	`created_date` TIMESTAMP NULL DEFAULT NOW(),
	`updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE weather_snow(
	`weather_snow_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `snow_one_hour` DECIMAL(10,2) DEFAULT 0.00,
	`snow_three_hours` DECIMAL(10,2) DEFAULT 0.00,
	`created_date` TIMESTAMP NULL DEFAULT NOW(),
	`updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE weather_sun(
	`weather_sun_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `type` INT DEFAULT 0,
	`id` BIGINT DEFAULT 0,
	`country_code` VARCHAR(2) NOT NULL,
	`message` TEXT,
	`sun_rise` TIMESTAMP,
	`sun_set` TIMESTAMP,
	`created_date` TIMESTAMP NULL DEFAULT NOW(),
	`updated_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



