/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : db_lost

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2016-04-10 13:11:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `tb_comment`
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `comment_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `comment_time` varchar(50) DEFAULT NULL COMMENT '评论的时间',
  `comment_content` varchar(500) DEFAULT NULL COMMENT '论评的内容',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_found`
-- ----------------------------
DROP TABLE IF EXISTS `tb_found`;
CREATE TABLE `tb_found` (
  `found_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `user_telephone` varchar(11) DEFAULT NULL,
  `found_title` varchar(200) DEFAULT NULL,
  `found_content` varchar(2000) NOT NULL,
  `found_time` varchar(500) DEFAULT NULL,
  `found_image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`found_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_found
-- ----------------------------
INSERT INTO `tb_found` VALUES ('1', '1', '咔咔', '18365265051', '◑▂◑你好', '◑▂◑你好', '1459983344615', null);
INSERT INTO `tb_found` VALUES ('2', '1', '咔咔', '18365265051', '(๑•ั็ω•็ั๑)', '(｡•́︿•̀｡)', '1459983572735', null);

-- ----------------------------
-- Table structure for `tb_lost`
-- ----------------------------
DROP TABLE IF EXISTS `tb_lost`;
CREATE TABLE `tb_lost` (
  `lost_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `user_telephone` varchar(11) DEFAULT NULL COMMENT '用户手机号',
  `lost_title` varchar(200) DEFAULT NULL COMMENT '失物招领：标题',
  `lost_content` varchar(2000) NOT NULL COMMENT '失物招领：内容',
  `lost_time` varchar(500) DEFAULT NULL,
  `lost_image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`lost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_lost
-- ----------------------------
INSERT INTO `tb_lost` VALUES ('1', '1', '咔咔', '18365265051', '失物招领', '失物招领', '1459870271606', null);
INSERT INTO `tb_lost` VALUES ('2', '2', '嘿嘿', '18365265053', '失物招领二', '失物招领二', '1459870344915', null);
INSERT INTO `tb_lost` VALUES ('3', '1', '咔咔', '18365265051', '●ｖ●', '(๑• . •๑)', '1459983439220', null);
INSERT INTO `tb_lost` VALUES ('4', '1', '咔咔', '18365265051', '红色钱包', '捡到红色钱包', '1460253320198', null);
INSERT INTO `tb_lost` VALUES ('5', '1', '咔咔', '18365265051', '你好', '( •ิ_• ิ)', '1460253843128', null);

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL COMMENT '用户名',
  `user_password` varchar(50) DEFAULT NULL,
  `user_telephone` varchar(11) DEFAULT NULL COMMENT '用户手机号',
  `user_sex` char(4) DEFAULT NULL,
  `user_head_image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '咔咔', '4QrcOUm6Wau+VuBX8g+IPg==', '18365265051', '男', null);
INSERT INTO `tb_user` VALUES ('2', '嘿嘿', 'wzNncBURtPYCDsYd7TUgWQ==', '18365265053', null, null);
