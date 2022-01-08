CREATE TABLE `weathers` (
  `id` bigint NOT NULL,
  `city` varchar(200) NOT NULL,
  `json_data` varchar(200) NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`id`)
);