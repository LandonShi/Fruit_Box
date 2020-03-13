/*
Navicat MySQL Data Transfer

Source Server         : share
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : sharedata

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-03-13 11:19:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('4', '前端CSS');
INSERT INTO `category` VALUES ('5', '接单利器');
INSERT INTO `category` VALUES ('6', '操作系统');
INSERT INTO `category` VALUES ('7', '图片操作');
INSERT INTO `category` VALUES ('8', '日常工具');
INSERT INTO `category` VALUES ('9', '文档处理');
INSERT INTO `category` VALUES ('10', '开发工具');
INSERT INTO `category` VALUES ('11', '加密解密');
INSERT INTO `category` VALUES ('12', '编码转换');
INSERT INTO `category` VALUES ('13', '热门下载');
