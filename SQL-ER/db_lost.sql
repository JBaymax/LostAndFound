/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : db_lost

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2016-03-30 23:28:34
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
  `found_title` varchar(200) DEFAULT NULL,
  `found_content` varchar(2000) NOT NULL,
  `found_time` varchar(500) DEFAULT NULL,
  `found_image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`found_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_found
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_lost`
-- ----------------------------
DROP TABLE IF EXISTS `tb_lost`;
CREATE TABLE `tb_lost` (
  `lost_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `lost_title` varchar(200) DEFAULT NULL,
  `lost_content` varchar(2000) NOT NULL,
  `lost_time` varchar(500) DEFAULT NULL,
  `lost_image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`lost_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_lost
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `user_password` varchar(50) DEFAULT NULL,
  `user_telephone` varchar(11) DEFAULT NULL,
  `user_sex` char(4) DEFAULT NULL,
  `user_head_image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '哈哈', '4QrcOUm6Wau+VuBX8g+IPg==', '18365265051', null, null);
INSERT INTO `tb_user` VALUES ('2', '嘿嘿', 'wzNncBURtPYCDsYd7TUgWQ==', '18365265053', null, null);
