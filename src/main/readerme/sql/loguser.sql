/*
Navicat MySQL Data Transfer

Source Server         : share
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : sharedata

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2020-03-13 11:19:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `loguser`
-- ----------------------------
DROP TABLE IF EXISTS `loguser`;
CREATE TABLE `loguser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginDate` datetime DEFAULT NULL,
  `logoutDate` datetime DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `typeLogin` varchar(255) DEFAULT NULL,
  `deviceType` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loguser
-- ----------------------------
INSERT INTO `loguser` VALUES ('45', '2020-03-04 22:17:54', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('46', '2020-03-05 00:43:41', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('47', '2020-03-05 00:44:36', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('48', '2020-03-05 00:46:40', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('49', '2020-03-05 00:58:38', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('50', '2020-03-05 00:59:15', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('51', '2020-03-05 12:41:58', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('52', '2020-03-05 17:07:20', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('53', '2020-03-05 22:20:23', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('54', '2020-03-05 22:28:44', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('55', '2020-03-06 00:26:59', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('56', '2020-03-06 01:30:16', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('57', '2020-03-06 12:19:00', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('58', '2020-03-06 13:59:09', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('59', '2020-03-06 14:37:34', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('60', '2020-03-06 16:10:10', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('61', '2020-03-06 16:16:14', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('62', '2020-03-06 16:26:40', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('63', '2020-03-06 16:36:10', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('64', '2020-03-06 17:14:58', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('65', '2020-03-06 17:20:21', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('66', '2020-03-06 17:31:57', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('67', '2020-03-06 17:42:06', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('68', '2020-03-06 17:44:59', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('69', '2020-03-06 17:47:20', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('70', '2020-03-06 23:49:00', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('71', '2020-03-07 09:58:02', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('72', '2020-03-07 10:16:06', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('73', '2020-03-07 10:33:22', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('74', '2020-03-07 11:01:22', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('75', '2020-03-07 11:11:21', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('76', '2020-03-07 11:38:00', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('77', '2020-03-07 12:43:54', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('78', '2020-03-07 13:57:15', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('79', '2020-03-08 10:23:47', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('80', '2020-03-08 11:32:53', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('81', '2020-03-08 21:28:05', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('82', '2020-03-08 22:54:18', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('83', '2020-03-08 23:21:07', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('84', '2020-03-08 23:44:20', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('85', '2020-03-09 00:05:59', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('86', '2020-03-09 09:49:30', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('87', '2020-03-09 12:00:31', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('88', '2020-03-09 12:42:18', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('89', '2020-03-09 13:41:32', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('90', '2020-03-09 13:49:08', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('91', '2020-03-09 18:49:07', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('92', '2020-03-09 19:05:57', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('93', '2020-03-09 21:02:37', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('94', '2020-03-10 09:13:34', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('95', '2020-03-10 10:05:37', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('96', '2020-03-10 10:36:01', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('97', '2020-03-10 11:00:03', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('98', '2020-03-10 11:11:49', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('99', '2020-03-10 11:41:31', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('100', '2020-03-10 12:45:00', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('101', '2020-03-10 13:12:26', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('102', '2020-03-10 14:12:24', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('103', '2020-03-10 20:30:30', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('104', '2020-03-10 20:41:40', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('105', '2020-03-10 20:59:40', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('106', '2020-03-10 21:04:28', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('107', '2020-03-10 21:21:52', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('108', '2020-03-10 21:22:33', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('109', '2020-03-10 21:25:11', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('110', '2020-03-11 01:05:18', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('111', '2020-03-11 13:58:35', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('112', '2020-03-11 19:21:15', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('113', '2020-03-11 19:29:03', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('114', '2020-03-11 19:38:10', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('115', '2020-03-11 19:51:48', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('116', '2020-03-11 20:08:13', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('117', '2020-03-11 20:55:05', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('118', '2020-03-11 21:00:15', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('119', '2020-03-11 21:03:14', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('120', '2020-03-12 09:23:05', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('121', '2020-03-12 09:39:53', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('122', '2020-03-12 10:15:56', null, '192.168.228.1', '站点登录', 'PC', '17');
INSERT INTO `loguser` VALUES ('123', '2020-03-13 11:00:12', null, '192.168.228.1', '站点登录', 'PC', '17');
