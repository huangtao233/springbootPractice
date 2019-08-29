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

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `USER_ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USER_NAME` varchar(100) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(400) DEFAULT NULL COMMENT '密码',
  `TELEPHONE` varchar(400) DEFAULT NULL COMMENT '手机',
  `USER_ROLE` int(1) DEFAULT NULL COMMENT '0买家，1卖家',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `GENDER` int(1) DEFAULT NULL COMMENT '0男1女',
  `IS_VIP` int(1) DEFAULT NULL COMMENT '1是vip,0非VIP',
  `VIP_LEFT_DATE` datetime DEFAULT NULL COMMENT 'VIP到期时间',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_NAME_UNIQUE` (`USER_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `userinfo` */

insert  into `userinfo`(`USER_ID`,`USER_NAME`,`PASSWORD`,`TELEPHONE`,`USER_ROLE`,`EMAIL`,`GENDER`,`IS_VIP`,`VIP_LEFT_DATE`) values (0,'admin','202cb962ac59075b964b07152d234b70',NULL,1,NULL,NULL,NULL,'0000-00-00 00:00:00'),(1,'huangtao','202cb962ac59075b964b07152d234b70','212',0,'577922356@qq.com',1,0,'2019-07-04 15:15:14'),(4,'xqshzmdhfz',NULL,'7989148189',NULL,NULL,NULL,NULL,NULL),(5,'pfjvxzxova',NULL,'5029914271',NULL,NULL,NULL,NULL,NULL),(6,'gdmjgpuvxu',NULL,'9700087978',NULL,NULL,NULL,NULL,NULL),(7,'pghqezkquz',NULL,'8440792126',NULL,NULL,NULL,NULL,NULL),(8,'rbmiahpfod',NULL,'5758830583',NULL,NULL,NULL,NULL,NULL),(9,'qiwwulomar',NULL,'9714101452',NULL,NULL,NULL,NULL,NULL),(10,'cxlrohzzow',NULL,'9993119427',NULL,NULL,NULL,NULL,NULL),(11,'lnevwifaqg',NULL,'6003256052',NULL,NULL,NULL,NULL,NULL),(12,'angpbycqyd',NULL,'9834411860',NULL,NULL,NULL,NULL,NULL),(13,'oikwyoxgmz',NULL,'9834296159',NULL,NULL,NULL,NULL,NULL),(14,'ouypzybaoh',NULL,'9851846880',NULL,NULL,NULL,NULL,NULL),(15,'uquytdjkjj',NULL,'8420209322',NULL,NULL,NULL,NULL,NULL),(16,'oirgjqkotm',NULL,'8638359812',NULL,NULL,NULL,NULL,NULL),(17,'ntuvnmxxqc',NULL,'5594423624',NULL,NULL,NULL,NULL,NULL),(18,'wazriyugup',NULL,'0117511972',NULL,NULL,NULL,NULL,NULL),(19,'ygzhsygead',NULL,'4059362319',NULL,NULL,NULL,NULL,NULL),(20,'acqsspiluu',NULL,'2760683535',NULL,NULL,NULL,NULL,NULL),(21,'ymqzofagxf',NULL,'9190081080',NULL,NULL,NULL,NULL,NULL),(22,'azuelecitw',NULL,'9309060749',NULL,NULL,NULL,NULL,NULL),(23,'xwjggkmdnn',NULL,'3431712911',NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
