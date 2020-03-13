/*
Navicat MySQL Data Transfer

Source Server         : share
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : sharedata

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-03-13 11:19:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `kit`
-- ----------------------------
DROP TABLE IF EXISTS `kit`;
CREATE TABLE `kit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `downloadTimes` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_kit_category` (`cid`),
  CONSTRAINT `fk_kit_category` FOREIGN KEY (`cid`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kit
-- ----------------------------
INSERT INTO `kit` VALUES ('3', 'IDEA', 'E:\\share\\src\\main\\webapp\\uploaded\\1581954234933IDEA3.rar', '已审核', '转载', '0', '最简单代码编辑器 轻巧极速 强大的语法提示 护眼精灵 最简单代码编辑器 轻巧极速 强大的语法提示 护眼精灵', '13', '2020-02-17 23:43:02', '17', '2020-02-17 23:43:54');
INSERT INTO `kit` VALUES ('4', 'IDEA5.9', 'E:\\share\\src\\main\\webapp\\uploaded\\1582088564978IDEA59.rar', '已审核', '转载', '0', '最简单代码编辑器 轻巧极速 强大的语法提示 护眼精灵', '13', '2020-02-19 13:02:44', '17', '2020-02-19 13:02:44');
INSERT INTO `kit` VALUES ('5', 'Eclipse', 'E:\\share\\src\\main\\webapp\\uploaded\\1582113016801Eclipse.rar', '已审核', '转载', '0', '最简单代码编辑器 轻巧极速 强大的语法提示 护眼精灵', '13', '2020-02-19 19:50:16', '17', '2020-02-19 19:50:16');
INSERT INTO `kit` VALUES ('6', 'Json转换器', 'E:\\share\\src\\main\\webapp\\uploaded\\1582113096769Json转换器.rar', '已审核', '原创', '0', 'Json字符串转换 轻便高效 快速上手 提高效率', '13', '2020-02-19 19:51:36', '17', '2020-02-19 19:51:36');
INSERT INTO `kit` VALUES ('7', 'Hbuilder', 'E:\\share\\src\\main\\webapp\\uploaded\\1582113154419Hbuilder.rar', '已审核', '转载', '0', '最简单代码编辑器 轻巧极速 强大的语法提示 护眼精灵', '13', '2020-02-19 19:52:34', '17', '2020-02-19 19:52:34');
INSERT INTO `kit` VALUES ('8', 'IDEA6.0', 'E:\\share\\src\\main\\webapp\\uploaded\\1583858140935Eclipse.rar', '已审核', '转载', '0', '水水水水水水水水水水水水水水水水水水水', '13', '2020-03-11 00:35:40', '17', '2020-03-11 00:35:40');
INSERT INTO `kit` VALUES ('11', '格式工程', 'E:\\share\\src\\main\\webapp\\uploaded\\1583931125008Eclipse.rar', '已审核', '原创', '0', '轻松转换各种格式，轻松转换各种格式，轻松转换各种格式，轻松转换各种格式', '13', '2020-03-11 20:06:36', '17', '2020-03-11 20:52:05');
INSERT INTO `kit` VALUES ('12', 'WebStorm', 'E:\\share\\src\\main\\webapp\\uploaded\\1583931424690Eclipse.rar', '已审核', '转载', '0', 'WebStorm 是jetbrains公司旗下一款JavaScript 开发工具。已经被广大中国JS开发者誉为“Web前端开发神器”、“最强大的HTML5编辑器”、“最智能的JavaScript IDE”等。与IntelliJ IDEA同源，继承了IntelliJ IDEA强大的JS部分的功能。', '13', '2020-03-11 20:57:04', '17', '2020-03-11 20:57:04');
