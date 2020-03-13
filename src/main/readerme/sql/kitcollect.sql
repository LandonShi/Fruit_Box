/*
Navicat MySQL Data Transfer

Source Server         : share
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : sharedata

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-03-13 11:19:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `kitcollect`
-- ----------------------------
DROP TABLE IF EXISTS `kitcollect`;
CREATE TABLE `kitcollect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_collectkit_user` (`uid`),
  CONSTRAINT `fk_collectkit_user` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kitcollect
-- ----------------------------
INSERT INTO `kitcollect` VALUES ('1', '3', '17');
INSERT INTO `kitcollect` VALUES ('2', '7', '17');
