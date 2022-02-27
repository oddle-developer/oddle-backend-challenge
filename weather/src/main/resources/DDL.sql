CREATE TABLE `weather` (
  `date` date NOT NULL,
  `city` varchar(255) NOT NULL,
  `info` text,
  PRIMARY KEY (`date`,`city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
