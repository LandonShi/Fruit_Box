/*
Navicat MySQL Data Transfer

Source Server         : share
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : sharedata

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-03-13 11:20:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `property`
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_property_category` (`cid`),
  CONSTRAINT `fk_property_category` FOREIGN KEY (`cid`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of property
-- ----------------------------
INSERT INTO `property` VALUES ('1', '13', '软件大小');
INSERT INTO `property` VALUES ('2', '13', '软件语言');
INSERT INTO `property` VALUES ('3', '13', '软件平台');
INSERT INTO `property` VALUES ('4', '13', '用户人群');
INSERT INTO `property` VALUES ('5', '13', '软件厂商');
INSERT INTO `property` VALUES ('6', '13', '渠道来源');
INSERT INTO `property` VALUES ('7', '13', '开发作者');
