/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : sns

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 14/05/2020 11:49:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for friend_circle_comment
-- ----------------------------
DROP TABLE IF EXISTS `friend_circle_comment`;
CREATE TABLE `friend_circle_comment` (
  `comment_id` bigint NOT NULL AUTO_INCREMENT,
  `msg_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for friend_circle_img
-- ----------------------------
DROP TABLE IF EXISTS `friend_circle_img`;
CREATE TABLE `friend_circle_img` (
  `msg_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应msg_id',
  `img_url` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img_order` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for friend_circle_like
-- ----------------------------
DROP TABLE IF EXISTS `friend_circle_like`;
CREATE TABLE `friend_circle_like` (
  `msg_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息Id',
  `user_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '点赞者id',
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for friend_circle_message
-- ----------------------------
DROP TABLE IF EXISTS `friend_circle_message`;
CREATE TABLE `friend_circle_message` (
  `msg_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'msg_id，动态自增Id',
  `user_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布者Id',
  `msg_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `location` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '位置',
  `see_area` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  PRIMARY KEY (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for friend_circle_reply
-- ----------------------------
DROP TABLE IF EXISTS `friend_circle_reply`;
CREATE TABLE `friend_circle_reply` (
  `reply_id` bigint NOT NULL,
  `comment_id` bigint NOT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1',
  `msg_id` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for friend_circle_timeline
-- ----------------------------
DROP TABLE IF EXISTS `friend_circle_timeline`;
CREATE TABLE `friend_circle_timeline` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `msg_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_own` int NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for pri_message
-- ----------------------------
DROP TABLE IF EXISTS `pri_message`;
CREATE TABLE `pri_message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reply_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `datetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_read` int DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uid_and_rid` (`user_id`,`reply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_aiais` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
  `user_image` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像',
  `user_logon` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录名（邮箱)',
  `user_passwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实姓名',
  `user_skin` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_qq` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_school` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_work` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_tel` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_info` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '个性签名',
  `user_datetime` datetime DEFAULT NULL,
  `user_blog` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'clim', '/static/images/2.jpg', '111', '123456', '1', 'asd', '123', NULL, NULL, NULL, NULL, 'fff', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('10', '101010', '/static/images/1.jpg', '100', '123456', '1', 'asd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('11', '11', '/static/images/3.jpg', '101', '123456', '1', 'asd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('12', '12', '/static/images/3.jpg', '102', '123456', '1', 'asd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('13', '13', '/static/images/3.jpg', '103', '123456', '1', '123', NULL, NULL, NULL, NULL, NULL, '123', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('14', '14', '/static/images/1.jpg', '104', '123456', '1', '123', NULL, NULL, NULL, NULL, NULL, '哈哈', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('15', '15', '/static/images/2.jpg', '105', '123456', '1', '123', NULL, NULL, NULL, NULL, NULL, '咳咳', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('16', 'AAA', '/static/images/1.jpg', 'testA', '123456', '1', 'asd', 'asd', NULL, NULL, NULL, NULL, 'hello', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('17', 'BBB', '/static/images/2.jpg', 'testB', '123456', '1', 'asd', 'asd', NULL, NULL, NULL, NULL, 'i am testB', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('18', 'CCC', '/static/images/3.jpg', 'testC', '123456', '1', 'asd', 'asd', NULL, NULL, NULL, NULL, 'i am testC', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('2', 'zzl', '/static/images/1.jpg', '222', '123456', '1', NULL, NULL, NULL, NULL, NULL, NULL, 'fff', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('3', 'ccc', '/static/images/3.jpg', '333', '123456', '2', 'asd', '123', NULL, NULL, NULL, NULL, 'fff', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('4', 'dddd', '/static/images/1.jpg', '444', '123456', '1', 'asd', '123', NULL, NULL, NULL, NULL, 'fff', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('5', '555', '/static/images/3.jpg', '555', '123456', '2', 'asd', 'asd', NULL, NULL, NULL, NULL, 'qqq5', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('6', '666cc', '/static/images/1.jpg', '666', '123456', '1', 'asd', 'asd', NULL, NULL, NULL, NULL, 'qqq6', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('7', '777', '/static/images/1.jpg', '777', '123456', '1', 'asd', NULL, NULL, NULL, NULL, NULL, '777a', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('8', '888', '/static/images/2.jpg', '888', '123456', '1', 'asd', 'asd', NULL, NULL, NULL, NULL, '888a', NULL, NULL, NULL, '1');
INSERT INTO `user` VALUES ('9', '999', '/static/images/2.jpg', '999', '123456', '1', 'asd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1');
COMMIT;

-- ----------------------------
-- Table structure for useratten
-- ----------------------------
DROP TABLE IF EXISTS `useratten`;
CREATE TABLE `useratten` (
  `user_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `atten_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int NOT NULL DEFAULT '1' COMMENT '好友状态，1.好友 2.删除',
  `offline` int NOT NULL COMMENT '离线状态，无法推送，1代表离线，2代表在线',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of useratten
-- ----------------------------
BEGIN;
INSERT INTO `useratten` VALUES ('1', '5', 2, 2, '2020-04-13 23:51:07');
INSERT INTO `useratten` VALUES ('5', '1', 2, 2, '2020-04-13 23:51:07');
INSERT INTO `useratten` VALUES ('11', '9', 2, 1, '2020-05-05 15:26:27');
INSERT INTO `useratten` VALUES ('9', '11', 2, 2, '2020-05-05 15:26:27');
INSERT INTO `useratten` VALUES ('11', '10', 2, 2, '2020-05-05 15:27:25');
INSERT INTO `useratten` VALUES ('10', '11', 2, 2, '2020-05-05 15:27:25');
INSERT INTO `useratten` VALUES ('10', '9', 2, 1, '2020-05-05 16:46:57');
INSERT INTO `useratten` VALUES ('9', '10', 2, 2, '2020-05-05 16:46:57');
INSERT INTO `useratten` VALUES ('12', '11', 2, 2, '2020-05-05 17:48:17');
INSERT INTO `useratten` VALUES ('11', '12', 2, 2, '2020-05-05 17:48:17');
INSERT INTO `useratten` VALUES ('10', '12', 2, 2, '2020-05-05 17:49:50');
INSERT INTO `useratten` VALUES ('12', '10', 2, 2, '2020-05-05 17:49:50');
INSERT INTO `useratten` VALUES ('13', '14', 2, 2, '2020-05-08 19:20:18');
INSERT INTO `useratten` VALUES ('14', '13', 2, 2, '2020-05-08 19:20:18');
INSERT INTO `useratten` VALUES ('13', '1', 2, 2, '2020-05-09 17:18:24');
INSERT INTO `useratten` VALUES ('1', '13', 2, 2, '2020-05-09 17:18:24');
INSERT INTO `useratten` VALUES ('1', '16', 2, 2, '2020-05-10 08:56:43');
INSERT INTO `useratten` VALUES ('16', '1', 2, 2, '2020-05-10 08:56:43');
INSERT INTO `useratten` VALUES ('17', '16', 2, 2, '2020-05-10 08:58:40');
INSERT INTO `useratten` VALUES ('16', '17', 2, 2, '2020-05-10 08:58:40');
INSERT INTO `useratten` VALUES ('17', '1', 2, 2, '2020-05-10 08:59:34');
INSERT INTO `useratten` VALUES ('1', '17', 2, 2, '2020-05-10 08:59:34');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
