CREATE TABLE `city` (
  `Name` varchar(255) NOT NULL,
  `Country` varchar(255) DEFAULT NULL,
  `Latitude` decimal(19,2) DEFAULT NULL,
  `Longitude` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`Name`)
);

CREATE TABLE `weather` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `Date` varchar(255) DEFAULT NULL,
  `Sunrise` bigint DEFAULT NULL,
  `Sunset` bigint DEFAULT NULL,
  `Timezone` bigint DEFAULT NULL,
  `Visibility` decimal(19,2) DEFAULT NULL,
  `City` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_weather_city` (`City`),
  CONSTRAINT `FK_weather_city` FOREIGN KEY (`City`) REFERENCES `city` (`Name`)
);

CREATE TABLE `weather_condition` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `Code` int DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Main` varchar(255) DEFAULT NULL,
  `WeatherID` bigint NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_weather_master` (`WeatherID`),
  CONSTRAINT `FK_weather_master` FOREIGN KEY (`WeatherID`) REFERENCES `weather` (`ID`)
);

CREATE TABLE `weather_main` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `FeelsLike` decimal(19,2) DEFAULT NULL,
  `Humidity` decimal(19,2) DEFAULT NULL,
  `Pressure` decimal(19,2) DEFAULT NULL,
  `TempMax` decimal(19,2) DEFAULT NULL,
  `TempMin` decimal(19,2) DEFAULT NULL,
  `Temperature` decimal(19,2) DEFAULT NULL,
  `WeatherID` bigint NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_weather_main_master` (`WeatherID`),
  CONSTRAINT `FK_weather_main_master` FOREIGN KEY (`WeatherID`) REFERENCES `weather` (`ID`)
);

CREATE TABLE `weather_wind` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `Direction` decimal(19,2) DEFAULT NULL,
  `Speed` decimal(19,2) DEFAULT NULL,
  `WeatherID` bigint NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_weather_wind_master` (`WeatherID`),
  CONSTRAINT `FK_weather_wind_master` FOREIGN KEY (`WeatherID`) REFERENCES `weather` (`ID`)
);