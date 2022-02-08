CREATE DATABASE `weather`;

CREATE TABLE weather.`weathers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `weather` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);
