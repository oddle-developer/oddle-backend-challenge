-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.18-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for weather
DROP DATABASE IF EXISTS `weather`;
CREATE DATABASE IF NOT EXISTS `weather` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `weather`;

-- Dumping structure for table weather.hibincr
DROP TABLE IF EXISTS `hibincr`;
CREATE TABLE IF NOT EXISTS `hibincr` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table weather.hibincr: ~1 rows (approximately)
DELETE FROM `hibincr`;
/*!40000 ALTER TABLE `hibincr` DISABLE KEYS */;
INSERT INTO `hibincr` (`next_val`) VALUES
	(6);
/*!40000 ALTER TABLE `hibincr` ENABLE KEYS */;

-- Dumping structure for table weather.master_city
DROP TABLE IF EXISTS `master_city`;
CREATE TABLE IF NOT EXISTS `master_city` (
  `id_` bigint(20) NOT NULL,
  `city_code` varchar(255) DEFAULT NULL,
  `city_name` varchar(255) DEFAULT NULL,
  `lattitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table weather.master_city: ~4 rows (approximately)
DELETE FROM `master_city`;
/*!40000 ALTER TABLE `master_city` DISABLE KEYS */;
INSERT INTO `master_city` (`id_`, `city_code`, `city_name`, `lattitude`, `longitude`) VALUES
	(1, 'JKT', 'Jakarta', -6.210615138218265, 106.83431255201131),
	(2, 'BDG', 'Bandung', -6.919122431828124, 107.62255521444817),
	(4, 'SGP', 'Singapore', 1.3524378613687702, 103.85247775769068),
	(5, 'SGP', 'Singapore', 1.3524378613687702, 103.85247775769068);
/*!40000 ALTER TABLE `master_city` ENABLE KEYS */;

-- Dumping structure for table weather.weather_history
DROP TABLE IF EXISTS `weather_history`;
CREATE TABLE IF NOT EXISTS `weather_history` (
  `id_` varchar(255) NOT NULL,
  `city_code` varchar(255) DEFAULT NULL,
  `city_name` varchar(255) DEFAULT NULL,
  `date_added` datetime DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `detail_weather` varchar(255) DEFAULT NULL,
  `lattitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `main_weather` varchar(255) DEFAULT NULL,
  `weather_icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table weather.weather_history: ~4 rows (approximately)
DELETE FROM `weather_history`;
/*!40000 ALTER TABLE `weather_history` DISABLE KEYS */;
INSERT INTO `weather_history` (`id_`, `city_code`, `city_name`, `date_added`, `date_modified`, `is_deleted`, `detail_weather`, `lattitude`, `longitude`, `main_weather`, `weather_icon`) VALUES
	('4028e4407f3b20bf017f3b5b524c0000', 'JKT', 'Jakarta', '2022-02-27 20:25:14', '2022-02-27 20:25:14', b'0', 'overcast clouds', -6.210615138218265, 106.83431255201131, 'Clouds', '04n'),
	('4028e4407f3b82c2017f3b8405e00000', 'BDG', 'Bandung', '2022-02-27 21:09:42', '2022-02-27 21:09:42', b'1', 'overcast clouds', -6.9191, 107.6226, 'Clouds', '04n'),
	('4028e4407f3b82c2017f3b851bf60001', 'SBY', 'Surabaya', '2022-02-27 21:10:53', '2022-02-28 14:17:24', b'1', 'broken clouds', -7.2579, 112.7459, 'Clouds', '04n'),
	('4028e4407f3ed8d4017f3f3158cf0000', 'SBY', 'Surabaya', '2022-02-28 14:17:52', '2022-02-28 14:17:52', b'0', 'few clouds', -7.2579, 112.7459, 'Clouds', '02n');
/*!40000 ALTER TABLE `weather_history` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
