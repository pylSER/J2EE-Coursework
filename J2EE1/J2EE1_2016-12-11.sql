# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: localhost (MySQL 5.6.28)
# Database: J2EE1
# Generation Time: 2016-12-11 14:25:30 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Exam
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Exam`;

CREATE TABLE `Exam` (
  `id` int(11) unsigned NOT NULL,
  `examname` varchar(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Exam` WRITE;
/*!40000 ALTER TABLE `Exam` DISABLE KEYS */;

INSERT INTO `Exam` (`id`, `examname`, `year`)
VALUES
	(0,'计算系统基础',2014),
	(1,'离散数学(大一班)',2015),
	(2,'现代音乐流派',2015);

/*!40000 ALTER TABLE `Exam` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ExtraInfo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ExtraInfo`;

CREATE TABLE `ExtraInfo` (
  `examid` int(11) unsigned DEFAULT NULL,
  `studentid` varchar(11) DEFAULT NULL,
  `info` text,
  `id` int(11) unsigned NOT NULL DEFAULT '0',
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ofk` (`examid`),
  KEY `bfk` (`studentid`),
  CONSTRAINT `bfk` FOREIGN KEY (`studentid`) REFERENCES `Student` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `ofk` FOREIGN KEY (`examid`) REFERENCES `Exam` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ExtraInfo` WRITE;
/*!40000 ALTER TABLE `ExtraInfo` DISABLE KEYS */;

INSERT INTO `ExtraInfo` (`examid`, `studentid`, `info`, `id`, `score`)
VALUES
	(1,'141250099','集合论: 25;图论:25;代数结构:25;数理逻辑:24',0,99),
	(0,'141250099','上机考试:40;笔试:60',1,100),
	(2,'141250099','平时成绩:0;笔试成绩:59',2,59),
	(1,'141250002','集合论: 10;图论:10;代数结构:10;数理逻辑:12',3,42);

/*!40000 ALTER TABLE `ExtraInfo` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Student
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Student`;

CREATE TABLE `Student` (
  `id` varchar(11) NOT NULL DEFAULT '',
  `password` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;

INSERT INTO `Student` (`id`, `password`)
VALUES
	('141250002','123456'),
	('141250099','1234');

/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
