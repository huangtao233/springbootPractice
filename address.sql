/*
SQLyog v10.2 
MySQL - 5.6.24 : Database - db_shoe
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_shoe` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_shoe`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `ADDR_ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '地址ID',
  `ADDR_CONTENT` varchar(400) DEFAULT NULL COMMENT '地址',
  `USER_ID` int(10) DEFAULT NULL COMMENT '用户ID',
  `TRUE_NAME` varchar(400) DEFAULT NULL COMMENT '真实姓名',
  `TELEPHONE` varchar(400) DEFAULT NULL COMMENT '手机',
  `IS_DEFAULT` int(1) DEFAULT NULL COMMENT '1默认地址，0非默认',
  PRIMARY KEY (`ADDR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `address` */

insert  into `address`(`ADDR_ID`,`ADDR_CONTENT`,`USER_ID`,`TRUE_NAME`,`TELEPHONE`,`IS_DEFAULT`) values (1,'福建省 福州市 闽侯县 上街镇 福建省福州市闽侯上街大学新区学园路3号福建工程学院北区 123',1,'黄涛','15859410267',1),(2,'福建省 福州市 闽侯县 上街镇 福建省福州市闽侯上街大学新区学园路3号福建工程学院北区 ',1,'黄涛2','15859410268',0),(19,'test123',1,'黄涛3','15859410264',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
