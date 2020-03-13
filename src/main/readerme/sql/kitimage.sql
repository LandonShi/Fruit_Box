/*
Navicat MySQL Data Transfer

Source Server         : share
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : sharedata

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-03-13 11:19:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `kitimage`
-- ----------------------------
DROP TABLE IF EXISTS `kitimage`;
CREATE TABLE `kitimage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kid` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_kitimage_kit` (`kid`),
  CONSTRAINT `fk_kitimage_kit` FOREIGN KEY (`kid`) REFERENCES `kit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kitimage
-- ----------------------------
INSERT INTO `kitimage` VALUES ('5', '3', 'single');
INSERT INTO `kitimage` VALUES ('6', '3', 'detail');
INSERT INTO `kitimage` VALUES ('8', '4', 'detail');
INSERT INTO `kitimage` VALUES ('9', '4', 'single');
INSERT INTO `kitimage` VALUES ('10', '5', 'single');
INSERT INTO `kitimage` VALUES ('11', '5', 'detail');
INSERT INTO `kitimage` VALUES ('12', '6', 'single');
INSERT INTO `kitimage` VALUES ('13', '6', 'detail');
INSERT INTO `kitimage` VALUES ('14', '7', 'single');
INSERT INTO `kitimage` VALUES ('15', '7', 'detail');
INSERT INTO `kitimage` VALUES ('19', '8', 'single');
INSERT INTO `kitimage` VALUES ('20', '8', 'detail');
INSERT INTO `kitimage` VALUES ('21', '8', 'detail');
INSERT INTO `kitimage` VALUES ('24', '11', 'single');
INSERT INTO `kitimage` VALUES ('25', '11', 'detail');
INSERT INTO `kitimage` VALUES ('26', '12', 'single');
INSERT INTO `kitimage` VALUES ('27', '12', 'detail');
