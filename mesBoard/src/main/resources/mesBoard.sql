/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.28-log : Database - ssm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ssm`;

/*Table structure for table `blacklist` */

DROP TABLE IF EXISTS `blacklist`;

CREATE TABLE `blacklist` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `questioner` varchar(20) DEFAULT NULL,
  `questioned` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `blacklist` */

insert  into `blacklist`(`id`,`questioner`,`questioned`) values (1,'小明','caoxin'),(3,'caoxin','caoxin');

/*Table structure for table `complain` */

DROP TABLE IF EXISTS `complain`;

CREATE TABLE `complain` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `reporter` varchar(50) NOT NULL,
  `informant` varchar(50) NOT NULL,
  `reason` varchar(1000) DEFAULT NULL,
  `dealAnswer` varchar(1000) DEFAULT '未处理',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `complain` */

insert  into `complain`(`id`,`reporter`,`informant`,`reason`,`dealAnswer`) values (1,'111','222','违规','错了错了'),(2,'caoxin','小明','违规了哈哈哈','未处理');

/*Table structure for table `musers` */

DROP TABLE IF EXISTS `musers`;

CREATE TABLE `musers` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uName` varchar(20) NOT NULL,
  `uPassword` char(48) NOT NULL,
  `uPhoto` varchar(500) DEFAULT NULL,
  `uMail` char(20) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  `codeUrl` varchar(50) DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT '0',
  `acceptQues` tinyint(1) DEFAULT '1',
  `isBan` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `musers` */

insert  into `musers`(`id`,`uName`,`uPassword`,`uPhoto`,`uMail`,`isActive`,`codeUrl`,`isDelete`,`acceptQues`,`isBan`) values (1,'笑话','abcdefg','999999','175034056@qq.com',1,NULL,0,1,1),(2,'麻子','123456',NULL,'1750340568@qq.com',0,'1',NULL,NULL,NULL),(3,'涉资','123456',NULL,'175034056@qq.com',0,'7e1b44a4-3be7-4b65-a08d-d816292abeb4',0,NULL,NULL),(4,'涉资2','123456',NULL,'954876645@qq.com',0,'1efb2ea3-7884-4b9d-b3f0-cb554f05a623',0,NULL,NULL),(5,'md5','17ab7c61e957907031c2e84cb1e294529906487652d68f01',NULL,'954876646@qq.com',0,'83ff1d4b-1058-41e5-b384-ccf04f3de242',0,NULL,NULL),(6,'验证码','123456',NULL,'954876646@qq.com',0,'5e18def0-b3dc-41ef-a913-269530710868',0,1,0),(7,'test','b46754d83f70327c92e8aa2098a090645686029b40d31125',NULL,'954876648@qq.com',0,'44ba39cc-732b-4dfc-b6db-b117f074711b',0,1,0),(9,'test2','a5297226f22c54039945fe6252fd2a92a08033a468c67299',NULL,'123456@qq.com',1,'05ffcb9d-7d05-4e7c-8f40-c083be7d3bdc',0,1,0),(12,'test3','517a4fe41a8347917503550e62069610b76b36b19446dc5c',NULL,'12131@qq.com',0,'b541c1a7-6b6c-429d-becb-0a889a1e57ca',0,1,0),(13,'test9','912407952d55a4c38577eb47b39b72913041667d3648719c',NULL,'22222@qq.com',0,'d39c0f3f-7375-46bb-9ccd-c82c3fece789',0,1,0),(14,'test10','13079b97fb05b93a31037f19e7410d331c9cb8731fe04593',NULL,'5555@qq.com',0,'6b0484ab-1094-4787-b9c0-2fcd499d570f',0,1,0),(16,'test11','28cc17519d47c38c9d03303943ef70d04c25766279429b61',NULL,'9999@qq.com',0,'a09fb9ca-637a-4721-a10d-3b4adbef22ac',0,1,0),(20,'李武','f32f1a61ac3625865522055e876340f4da9ad4079e743849',NULL,'12131',0,'3bb56741-8eb4-4f89-ae41-00839cdd2b3f',0,1,0),(21,'caoxin','541065a6eb19a4f36594e051169577514c4291d756d3ba28','E:\\java idea\\mesBoard\\out\\artifacts\\mesBoard_Web_exploded\\image\\c8697dd8-17b0-4304-ab85-e0f7e471b8f91101.png','1750340569@qq.com',1,'be978adc-018a-474b-af7e-39aa5e3538ec',0,1,0),(22,'李武1','c69353a73e49f1615652da26f35c8be81d2b44d29962056c',NULL,'9999888@qq.com',0,'dd567e1e-3716-4f56-8ba0-1d1ca70de624',0,1,0),(23,'admin','c6664e61840bf86500c2547017c342a9714303a81203ea0e',NULL,'caoxin9629@163.com',0,'d2f88e68-00c6-4cd0-aed9-dad1ce50ee25',0,0,0),(24,'test99','87bd5aa73c77140c6a577970890f17663914231876c7180d',NULL,'954876644@qq.com',0,'bc24f492-2393-456c-a94b-866ea5d89188',0,0,0);

/*Table structure for table `question` */

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `questioner` varchar(20) NOT NULL,
  `questioned` varchar(20) NOT NULL,
  `question` varchar(1000) DEFAULT NULL,
  `isDelete` tinyint(1) DEFAULT NULL,
  `answer` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `question` */

insert  into `question`(`id`,`questioner`,`questioned`,`question`,`isDelete`,`answer`) values (1,'小明','caoxin','这是一个匿名信箱子吗？',0,'哈哈哈，这是第四次更改了啊'),(2,'小明','caoxin','这不是一个匿名信箱子吗？',1,'是的饿'),(3,'caoxin','caoxin','这是一个好玩的游戏啊',0,NULL),(4,'caoxin','caoxin','这是一个好玩的游戏啊',1,NULL),(5,'caoxin','caoxin','这是一个好玩的游戏啊',0,NULL),(6,'caoxin','caoxin','这是一个好玩的游戏啊',0,NULL),(7,'caoxin','笑话','你是笑话？',0,'fsfasfasfasd'),(8,'caoxin','笑话','你是笑话222',0,NULL),(9,'caoxin','笑话','这是给您的第三条提问',0,NULL);

/*Table structure for table `riskmanagement` */

DROP TABLE IF EXISTS `riskmanagement`;

CREATE TABLE `riskmanagement` (
  `rmId` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `wrongLoginN` int(10) DEFAULT NULL,
  `judgeCity` int(10) DEFAULT '0',
  PRIMARY KEY (`rmId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `riskmanagement` */

insert  into `riskmanagement`(`rmId`,`uid`,`city`,`wrongLoginN`,`judgeCity`) values (1,1,'北京',1,0),(2,1,'北京',1,0),(3,NULL,NULL,0,0),(4,NULL,NULL,0,0),(5,21,'未知位置',0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
