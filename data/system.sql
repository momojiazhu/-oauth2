-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.25-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 system 的数据库结构
CREATE DATABASE IF NOT EXISTS `system` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `system`;

-- 导出  表 system.common_log 结构
CREATE TABLE IF NOT EXISTS `common_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL,
  `opt_object` varchar(32) NOT NULL,
  `opt_type` varchar(32) NOT NULL,
  `message` varchar(128) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- 正在导出表  system.common_log 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `common_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `common_log` ENABLE KEYS */;

-- 导出  表 system.dept 结构
CREATE TABLE IF NOT EXISTS `dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(32) DEFAULT NULL,
  `is_del` tinyint(4) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- 正在导出表  system.dept 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;

-- 导出  表 system.file_upload 结构
CREATE TABLE IF NOT EXISTS `file_upload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(128) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- 正在导出表  system.file_upload 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `file_upload` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_upload` ENABLE KEYS */;

-- 导出  表 system.search_log 结构
CREATE TABLE IF NOT EXISTS `search_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) DEFAULT NULL,
  `url` varchar(120) DEFAULT NULL,
  `parameter` varchar(320) DEFAULT NULL,
  `result` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- 正在导出表  system.search_log 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `search_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `search_log` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
