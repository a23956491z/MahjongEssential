-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: database_mje_test
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `match_information`
--

DROP TABLE IF EXISTS `match_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `match_information` (
  `match_serial_number` bigint(20) NOT NULL AUTO_INCREMENT,
  `match_created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `account_number` varchar(24) NOT NULL,
  PRIMARY KEY (`match_serial_number`),
  KEY `account_number` (`account_number`),
  CONSTRAINT `match_information_ibfk_1` FOREIGN KEY (`account_number`) REFERENCES `user_attribute` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_information`
--

LOCK TABLES `match_information` WRITE;
/*!40000 ALTER TABLE `match_information` DISABLE KEYS */;
INSERT INTO `match_information` VALUES (1,'2020-08-03 04:48:04','test_acc_1');
/*!40000 ALTER TABLE `match_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_record`
--

DROP TABLE IF EXISTS `match_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `match_record` (
  `match_serial_number` bigint(20) NOT NULL,
  `round_number` int(11) NOT NULL,
  `role_name` varchar(24) NOT NULL,
  `score_change` int(11) NOT NULL,
  UNIQUE KEY `u_match_round_name` (`match_serial_number`,`round_number`,`role_name`),
  CONSTRAINT `match_record_ibfk_1` FOREIGN KEY (`match_serial_number`) REFERENCES `match_information` (`match_serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_record`
--

LOCK TABLES `match_record` WRITE;
/*!40000 ALTER TABLE `match_record` DISABLE KEYS */;
INSERT INTO `match_record` VALUES (1,1,'abby',-5),(1,1,'ruby',5),(1,2,'diana',-3),(1,2,'ruby',3),(1,3,'abby',-4),(1,3,'diana',-3),(1,3,'mickey',10),(1,3,'ruby',-3);
/*!40000 ALTER TABLE `match_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_attribute`
--

DROP TABLE IF EXISTS `role_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_attribute` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(24) NOT NULL,
  `account_number` varchar(24) NOT NULL,
  `score_balance` int(11) DEFAULT '0',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `u_account_role` (`account_number`,`role_name`),
  CONSTRAINT `role_attribute_ibfk_1` FOREIGN KEY (`account_number`) REFERENCES `user_attribute` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_attribute`
--

LOCK TABLES `role_attribute` WRITE;
/*!40000 ALTER TABLE `role_attribute` DISABLE KEYS */;
INSERT INTO `role_attribute` VALUES (1,'abby','test_acc_1',0),(3,'diana','test_acc_1',0),(4,'mikey','test_acc_2',0),(9,'mikey','test_acc_1',0),(10,'ruby','test_acc_1',0);
/*!40000 ALTER TABLE `role_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_attribute`
--

DROP TABLE IF EXISTS `user_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_attribute` (
  `account_number` varchar(24) NOT NULL,
  `password` varchar(24) NOT NULL,
  `account_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_attribute`
--

LOCK TABLES `user_attribute` WRITE;
/*!40000 ALTER TABLE `user_attribute` DISABLE KEYS */;
INSERT INTO `user_attribute` VALUES ('i dont have a name','87878',NULL),('test_acc_1','30678','abby'),('test_acc_2','1234','miiiiike');
/*!40000 ALTER TABLE `user_attribute` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-03  6:12:05
