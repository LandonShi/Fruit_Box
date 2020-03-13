/*
Navicat MySQL Data Transfer

Source Server         : share
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : sharedata

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-03-13 11:20:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `propertyvalue`
-- ----------------------------
DROP TABLE IF EXISTS `propertyvalue`;
CREATE TABLE `propertyvalue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_propertyvalue_property` (`pid`),
  KEY `fk_propertyvalue_kit` (`kid`),
  CONSTRAINT `fk_propertyvalue_property` FOREIGN KEY (`pid`) REFERENCES `property` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of propertyvalue
-- ----------------------------
INSERT INTO `propertyvalue` VALUES ('2', '1', '2', '中文简体');
INSERT INTO `propertyvalue` VALUES ('3', '1', '3', 'Windows10');
INSERT INTO `propertyvalue` VALUES ('4', '1', '4', '开发者');
INSERT INTO `propertyvalue` VALUES ('5', '1', '5', 'JetBrains');
INSERT INTO `propertyvalue` VALUES ('6', '1', '1', '450M');
INSERT INTO `propertyvalue` VALUES ('7', '2', '1', '1GB');
INSERT INTO `propertyvalue` VALUES ('8', '2', '2', '英文');
INSERT INTO `propertyvalue` VALUES ('9', '2', '3', 'Win10');
INSERT INTO `propertyvalue` VALUES ('10', '2', '4', '开发者');
INSERT INTO `propertyvalue` VALUES ('11', '2', '5', 'Jetbrains');
INSERT INTO `propertyvalue` VALUES ('12', '2', '6', 'www.jetbrains.com');
INSERT INTO `propertyvalue` VALUES ('13', '3', '1', '458M');
INSERT INTO `propertyvalue` VALUES ('14', '3', '2', '英文');
INSERT INTO `propertyvalue` VALUES ('15', '3', '3', 'Win10');
INSERT INTO `propertyvalue` VALUES ('16', '3', '4', '开发者');
INSERT INTO `propertyvalue` VALUES ('17', '3', '5', 'JetBrains');
INSERT INTO `propertyvalue` VALUES ('18', '3', '6', 'www.ghw.com');
INSERT INTO `propertyvalue` VALUES ('19', '4', '1', '520M');
INSERT INTO `propertyvalue` VALUES ('20', '4', '2', '英文');
INSERT INTO `propertyvalue` VALUES ('21', '4', '3', 'Win10');
INSERT INTO `propertyvalue` VALUES ('22', '4', '4', '开发者');
INSERT INTO `propertyvalue` VALUES ('23', '4', '5', 'JetBrains');
INSERT INTO `propertyvalue` VALUES ('24', '4', '6', 'www.ghw.com');
INSERT INTO `propertyvalue` VALUES ('25', '5', '1', '75M');
INSERT INTO `propertyvalue` VALUES ('26', '5', '2', '英文');
INSERT INTO `propertyvalue` VALUES ('27', '5', '3', 'Win10');
INSERT INTO `propertyvalue` VALUES ('28', '5', '4', '开发者');
INSERT INTO `propertyvalue` VALUES ('29', '5', '5', 'Oracle');
INSERT INTO `propertyvalue` VALUES ('30', '5', '6', 'www.ghw.com');
INSERT INTO `propertyvalue` VALUES ('31', '6', '1', '5M');
INSERT INTO `propertyvalue` VALUES ('32', '6', '2', '中文简体');
INSERT INTO `propertyvalue` VALUES ('33', '6', '3', 'Win10');
INSERT INTO `propertyvalue` VALUES ('34', '6', '4', '开发者');
INSERT INTO `propertyvalue` VALUES ('35', '6', '5', '果函网');
INSERT INTO `propertyvalue` VALUES ('36', '6', '6', 'www.ghw.com');
INSERT INTO `propertyvalue` VALUES ('37', '7', '1', '278M');
INSERT INTO `propertyvalue` VALUES ('38', '7', '2', '中文简体');
INSERT INTO `propertyvalue` VALUES ('39', '7', '3', 'Win10');
INSERT INTO `propertyvalue` VALUES ('40', '7', '4', '开发者');
INSERT INTO `propertyvalue` VALUES ('41', '7', '5', '数字天堂网络科技');
INSERT INTO `propertyvalue` VALUES ('42', '7', '6', 'www.ghw.com');
INSERT INTO `propertyvalue` VALUES ('43', '7', '7', '清风明月');
INSERT INTO `propertyvalue` VALUES ('44', '6', '7', '清风明月');
INSERT INTO `propertyvalue` VALUES ('45', '5', '7', '清风明月');
INSERT INTO `propertyvalue` VALUES ('46', '4', '7', '清风明月');
INSERT INTO `propertyvalue` VALUES ('47', '3', '7', '清风明月');
INSERT INTO `propertyvalue` VALUES ('48', '9', '1', '500M');
INSERT INTO `propertyvalue` VALUES ('49', '9', '2', '英文');
INSERT INTO `propertyvalue` VALUES ('50', '9', '3', 'Win10');
INSERT INTO `propertyvalue` VALUES ('51', '9', '4', '开发者');
INSERT INTO `propertyvalue` VALUES ('52', '9', '5', '果函网');
INSERT INTO `propertyvalue` VALUES ('53', '9', '6', 'www.ghw.com');
INSERT INTO `propertyvalue` VALUES ('54', '9', '7', '清风明月');
INSERT INTO `propertyvalue` VALUES ('55', '8', '1', '500M');
INSERT INTO `propertyvalue` VALUES ('56', '8', '2', '英语');
INSERT INTO `propertyvalue` VALUES ('57', '8', '3', 'Win10');
INSERT INTO `propertyvalue` VALUES ('58', '8', '4', '开发者');
INSERT INTO `propertyvalue` VALUES ('59', '8', '5', '果函网');
INSERT INTO `propertyvalue` VALUES ('60', '8', '6', 'www.ghw.com');
INSERT INTO `propertyvalue` VALUES ('61', '8', '7', '清风明月');
INSERT INTO `propertyvalue` VALUES ('62', '10', '1', '500M');
INSERT INTO `propertyvalue` VALUES ('63', '10', '2', null);
INSERT INTO `propertyvalue` VALUES ('64', '10', '3', null);
INSERT INTO `propertyvalue` VALUES ('65', '10', '4', null);
INSERT INTO `propertyvalue` VALUES ('66', '10', '5', null);
INSERT INTO `propertyvalue` VALUES ('67', '10', '6', null);
INSERT INTO `propertyvalue` VALUES ('68', '10', '7', null);
INSERT INTO `propertyvalue` VALUES ('69', '11', '1', '450M');
INSERT INTO `propertyvalue` VALUES ('70', '11', '2', '中文');
INSERT INTO `propertyvalue` VALUES ('71', '11', '3', 'Win10');
INSERT INTO `propertyvalue` VALUES ('72', '11', '4', '开发者');
INSERT INTO `propertyvalue` VALUES ('73', '11', '5', '果函网');
INSERT INTO `propertyvalue` VALUES ('74', '11', '6', 'www.ghw.com');
INSERT INTO `propertyvalue` VALUES ('75', '11', '7', '清风明月');
INSERT INTO `propertyvalue` VALUES ('76', '12', '1', '120M');
INSERT INTO `propertyvalue` VALUES ('77', '12', '2', '中文');
INSERT INTO `propertyvalue` VALUES ('78', '12', '3', 'Win10/ Linux');
INSERT INTO `propertyvalue` VALUES ('79', '12', '4', '开发者');
INSERT INTO `propertyvalue` VALUES ('80', '12', '5', '果函网');
INSERT INTO `propertyvalue` VALUES ('81', '12', '6', 'www.ghw.com');
INSERT INTO `propertyvalue` VALUES ('82', '12', '7', '清风明月');
