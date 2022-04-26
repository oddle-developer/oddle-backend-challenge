CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `weather` (
  `id_weather` bigint NOT NULL,
  `dtime_created` timestamp NULL DEFAULT NULL,
  `dtime_updated` timestamp NULL DEFAULT NULL,
  `base` varchar(200) DEFAULT NULL,
  `date_time_calculated` timestamp NULL DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  `timezone` int DEFAULT '0',
  `visibility` int DEFAULT '0',
  `id_weather_cloud` bigint DEFAULT NULL,
  `id_weather_coordinates` bigint DEFAULT NULL,
  `id_weather_rain` bigint DEFAULT NULL,
  `id_weather_snow` bigint DEFAULT NULL,
  `id_weather_sun` bigint DEFAULT NULL,
  `id_weather_wind` bigint DEFAULT NULL,
  `id_weather_main` bigint DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  PRIMARY KEY (`id_weather`),
  KEY `FKcwj3e5pldd3km85d30ila4wcc` (`id_weather_coordinates`),
  KEY `FKknl43gj45626oopkkihdp41x7` (`id_weather_rain`),
  KEY `FKk34i1e6rxs4rkjjxwrftnby99` (`id_weather_snow`),
  KEY `FKkl6130wwdh656wj8eg2tg0sdn` (`id_weather_sun`),
  KEY `FKdm8gk17x86i6t7on2pf090wpn` (`id_weather_wind`),
  KEY `FKphji33bmp4m53ef9p4eav028k` (`id_weather_cloud`),
  KEY `FKj7gwjfxk3y5u5rehq7n3nylyb` (`id_weather_main`),
  CONSTRAINT `FKcwj3e5pldd3km85d30ila4wcc` FOREIGN KEY (`id_weather_coordinates`) REFERENCES `weather_coordinates` (`id_weather_coordinates`),
  CONSTRAINT `FKdm8gk17x86i6t7on2pf090wpn` FOREIGN KEY (`id_weather_wind`) REFERENCES `weather_wind` (`id_weather_wind`),
  CONSTRAINT `FKj7gwjfxk3y5u5rehq7n3nylyb` FOREIGN KEY (`id_weather_main`) REFERENCES `weather_main` (`id_weather_main`),
  CONSTRAINT `FKk34i1e6rxs4rkjjxwrftnby99` FOREIGN KEY (`id_weather_snow`) REFERENCES `weather_snow` (`id_weather_snow`),
  CONSTRAINT `FKkl6130wwdh656wj8eg2tg0sdn` FOREIGN KEY (`id_weather_sun`) REFERENCES `weather_sun` (`id_weather_sun`),
  CONSTRAINT `FKknl43gj45626oopkkihdp41x7` FOREIGN KEY (`id_weather_rain`) REFERENCES `weather_rain` (`id_weather_rain`),
  CONSTRAINT `FKphji33bmp4m53ef9p4eav028k` FOREIGN KEY (`id_weather_cloud`) REFERENCES `weather_cloud` (`id_weather_cloud`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `weather_cloud` (
  `id_weather_cloud` bigint NOT NULL,
  `dtime_created` timestamp NULL DEFAULT NULL,
  `dtime_updated` timestamp NULL DEFAULT NULL,
  `all_percentage` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id_weather_cloud`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `weather_code` (
  `id_weather_code` bigint NOT NULL,
  `dtime_created` timestamp NULL DEFAULT NULL,
  `dtime_updated` timestamp NULL DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `main` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_weather_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `weather_coordinates` (
  `id_weather_coordinates` bigint NOT NULL,
  `dtime_created` timestamp NULL DEFAULT NULL,
  `dtime_updated` timestamp NULL DEFAULT NULL,
  `latitude` decimal(10,4) DEFAULT '0.0000',
  `longitude` decimal(10,4) DEFAULT '0.0000',
  PRIMARY KEY (`id_weather_coordinates`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `weather_join_weather_code` (
  `id_weather` bigint NOT NULL,
  `id_weather_code` bigint NOT NULL,
  UNIQUE KEY `UK_su6719f4r90khfkrjboal6p6t` (`id_weather_code`),
  KEY `FKddo70elgfust85207x1gtf1um` (`id_weather`),
  CONSTRAINT `FKb6mi6ntcpbk6x8odj8aqyprac` FOREIGN KEY (`id_weather_code`) REFERENCES `weather_code` (`id_weather_code`),
  CONSTRAINT `FKddo70elgfust85207x1gtf1um` FOREIGN KEY (`id_weather`) REFERENCES `weather` (`id_weather`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `weather_main` (
  `id_weather_main` bigint NOT NULL,
  `dtime_created` timestamp NULL DEFAULT NULL,
  `dtime_updated` timestamp NULL DEFAULT NULL,
  `feels_like` double DEFAULT NULL,
  `ground_level` decimal(19,2) DEFAULT NULL,
  `humidity` double DEFAULT NULL,
  `maximum_temperature` double DEFAULT NULL,
  `minimum_temperature` double DEFAULT NULL,
  `pressure` decimal(19,2) DEFAULT NULL,
  `seal_level` decimal(19,2) DEFAULT NULL,
  `temperature` double DEFAULT NULL,
  PRIMARY KEY (`id_weather_main`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `weather_rain` (
  `id_weather_rain` bigint NOT NULL,
  `dtime_created` timestamp NULL DEFAULT NULL,
  `dtime_updated` timestamp NULL DEFAULT NULL,
  `one_hour` decimal(10,2) DEFAULT '0.00',
  `three_hours` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id_weather_rain`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `weather_snow` (
  `id_weather_snow` bigint NOT NULL,
  `dtime_created` timestamp NULL DEFAULT NULL,
  `dtime_updated` timestamp NULL DEFAULT NULL,
  `one_hour` decimal(10,2) DEFAULT '0.00',
  `three_hours` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id_weather_snow`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `weather_sun` (
  `id_weather_sun` bigint NOT NULL,
  `dtime_created` timestamp NULL DEFAULT NULL,
  `dtime_updated` timestamp NULL DEFAULT NULL,
  `country` varchar(10) NOT NULL,
  `sunrise` timestamp NULL DEFAULT NULL,
  `sunset` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id_weather_sun`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `weather_wind` (
  `id_weather_wind` bigint NOT NULL,
  `dtime_created` timestamp NULL DEFAULT NULL,
  `dtime_updated` timestamp NULL DEFAULT NULL,
  `degree` decimal(10,2) DEFAULT '0.00',
  `gust` decimal(10,2) DEFAULT '0.00',
  `speed` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id_weather_wind`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
