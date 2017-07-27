/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : examdb

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2017-07-24 16:25:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `optionA` varchar(50) DEFAULT NULL,
  `optionB` varchar(50) DEFAULT NULL,
  `optionC` varchar(50) DEFAULT NULL,
  `optionD` varchar(50) DEFAULT NULL,
  `answer` char(2) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `modifyTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '合法变量名', '51JOB88888', 'SNAME', 'TT%YY', '123ooo', 'B', 'tom', null);
INSERT INTO `question` VALUES ('5', '1+1=?', '21', '2', '11', '13', 'b', 'tom', null);
INSERT INTO `question` VALUES ('6', '诸葛亮挥泪斩了谁', '路飞', '李逵', '马谡', '路人甲', 'c', 'yy', null);
INSERT INTO `question` VALUES ('7', '诸葛亮挥泪斩了谁', '路飞', '李逵', '马谡', '路人甲', 'c', 'laozhang', null);
INSERT INTO `question` VALUES ('10', '4*8=?', '32', '12', '64', '36', 'a', 'tom', null);
INSERT INTO `question` VALUES ('11', '3*2=？', '6', '5', '32', '23', 'A', null, null);
INSERT INTO `question` VALUES ('12', '6*6=?', '12', '66', '6', '36', 'D', null, null);
INSERT INTO `question` VALUES ('13', '5', '1', '2', '3', '4', 'C', null, null);
INSERT INTO `question` VALUES ('14', 'D', 'D', 'D', 'D', 'D', 'C', null, null);
INSERT INTO `question` VALUES ('15', 'd', 'd', 'd', 'd', 'd', 'A', null, null);
INSERT INTO `question` VALUES ('16', 'd', 'd', 'd', 'd', 'd', 'C', null, null);
INSERT INTO `question` VALUES ('17', 'd', 'd', 'd', 'd', 'd', 'B', null, null);
INSERT INTO `question` VALUES ('18', 'd', 'd', 'd', 'd', 'd', 'B', null, null);
INSERT INTO `question` VALUES ('19', '4*90=?', '490', '360', '230', '49', 'B', null, null);
INSERT INTO `question` VALUES ('24', 'yyyyy', '10', '20', '30', '40', 'C', null, null);
INSERT INTO `question` VALUES ('25', 'yyyyy', 'y', 'y', 'y', 'y', 'D', null, null);
INSERT INTO `question` VALUES ('26', '10*8=?', '108', '80', '18', '9', 'B', null, null);

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `userId` int(11) DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `userScore` int(11) DEFAULT NULL,
  `examTime` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('15', '李明', '97', '2017-06-11');
INSERT INTO `score` VALUES ('15', '李明', '78', '2017-06-13');
INSERT INTO `score` VALUES ('15', '李明', '100', '2017-09-18');
INSERT INTO `score` VALUES ('15', '李明', '97', '2017-06-10');
INSERT INTO `score` VALUES ('13', 'tomcat', '0', '2017-05-18');
INSERT INTO `score` VALUES ('15', '李明', '100', '2017-02-18');
INSERT INTO `score` VALUES ('15', '李明', '100', '2017-02-18');
INSERT INTO `score` VALUES ('15', '李明', '0', '2017-03-16');
INSERT INTO `score` VALUES ('15', '李明', '0', '2017-03-16');
INSERT INTO `score` VALUES ('15', '李明', '100', '2017-02-18');
INSERT INTO `score` VALUES ('15', '李明', '100', '2017-05-18');
INSERT INTO `score` VALUES ('16', 'ww', '0', '2017-07-24');
INSERT INTO `score` VALUES ('16', 'ww', '100', '2017-07-24');
INSERT INTO `score` VALUES ('16', 'ww', '50', '2017-07-24');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userCode` varchar(20) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('9', '302', '付老师', '11123', 'teacher_Fu@163.com', '1921345566', '0');
INSERT INTO `users` VALUES ('10', '303', '英俊的胖子', '123456', 'yz@163.com', '010-4401368', '0');
INSERT INTO `users` VALUES ('11', '304', 'mike', '111', '王@163.com', '010-4401368', '0');
INSERT INTO `users` VALUES ('13', '306', 'tomcat', '111', 'tomcat@163.com', '010-4401368', '1');
INSERT INTO `users` VALUES ('15', '333', '李明', '111', 'liming@163.com', '123456', '1');
INSERT INTO `users` VALUES ('16', 'ww', 'ww', 'ww', 'ww', 'ww', '1');
INSERT INTO `users` VALUES ('17', '909', 'vvv', '123', 'vv', 'vv', '0');
INSERT INTO `users` VALUES ('18', '5566', '杜老师', '11', 'laodu@163.com', '123456', '0');
