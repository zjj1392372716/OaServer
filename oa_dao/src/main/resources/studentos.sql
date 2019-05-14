/*
 Navicat Premium Data Transfer

 Source Server         : contact
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : studentos

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 14/05/2019 16:29:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminId` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `token` varchar(100) NOT NULL COMMENT 'token',
  `createTime` varchar(20) NOT NULL COMMENT '创建时间',
  `roleId` int(10) NOT NULL COMMENT '角色id',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1为有效0为无效',
  PRIMARY KEY (`adminId`),
  KEY `role_admin_FK` (`roleId`),
  CONSTRAINT `role_admin_FK` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES (1, 'admin', 'f3f1d7e6f11726a598c16570617b69a42a0f9514', '1556457509513', 1, 1);
INSERT INTO `admin` VALUES (2, '1610111', '0fc44af205a760cff64966bd107be20e49be8c5e', '1556594455647', 2, 1);
INSERT INTO `admin` VALUES (3, '1610111', '0fc44af205a760cff64966bd107be20e49be8c5e', '1556594785165', 2, 1);
INSERT INTO `admin` VALUES (6, '16103322', '30d3982f7dc14c1bdda02f925b9e4093b83ed670', '1556759448984', 2, 1);
INSERT INTO `admin` VALUES (7, '10123211', 'e94d455a99aaf79451a0f3872f4e3d60acf5d9e4', '1556759678217', 2, 1);
INSERT INTO `admin` VALUES (8, 'TEA2019050112', '844a8a2a245d675c20c762479815d4dfd8087075', '1556759930383', 3, 1);
INSERT INTO `admin` VALUES (9, 'TEA2019050113', 'dbb4b81f432bb17c31519fdb992d341eb73e0a6c', '1556760688068', 3, 1);
INSERT INTO `admin` VALUES (10, '16101321', 'f0a842b58d9474b66cf69c333190a99d9c3b85d2', '1556761041260', 2, 1);
INSERT INTO `admin` VALUES (11, 'TEA2019050215', 'c4996618a1a80b97613097b88c7a94bd844cef41', '1557733186084', 3, 1);
INSERT INTO `admin` VALUES (12, 'TEA2019050316', '9d0751a02c6a6a21c54eb4f04bfa417b4922f8c2', '1557733738449', 3, 1);
INSERT INTO `admin` VALUES (13, 'TEA2019050417', '75857ce0f20e20fa2deac35892d1a98f05c9a469', '1557733787452', 3, 1);
INSERT INTO `admin` VALUES (14, 'TEA2019050918', 'c9f00c201ea1a07192addb27aeb9e2d761fc8ca3', '1557733818388', 3, 1);
INSERT INTO `admin` VALUES (15, 'TEA2019050819', '9c08b4aaaf0d4a062366881b5b0330ed4380df0c', '1557733827552', 3, 1);
INSERT INTO `admin` VALUES (16, '16101121', 'fb8d0d10be6ded541a761fdc24e7397552a44ac5', '1557740915634', 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `classId` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `className` varchar(20) DEFAULT NULL COMMENT '班级名称',
  `isDelete` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否可用',
  PRIMARY KEY (`classId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
BEGIN;
INSERT INTO `class` VALUES (1, '161013', 0);
INSERT INTO `class` VALUES (3, '161014', 0);
INSERT INTO `class` VALUES (4, '161012', 0);
INSERT INTO `class` VALUES (5, '161011', 0);
COMMIT;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseId` int(10) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(40) NOT NULL COMMENT '课程名称',
  `courseNum` varchar(30) NOT NULL COMMENT '课程编号',
  `tId` int(10) DEFAULT NULL COMMENT '任课教师',
  `createTime` varchar(20) NOT NULL COMMENT '创建时间',
  `courseProperty` varchar(30) NOT NULL COMMENT '课程属性，（限选/主修）',
  `courseSort` varchar(30) NOT NULL COMMENT '课程类别，专业课，基础理论课程等',
  `examType` varchar(30) NOT NULL COMMENT '考试类别',
  `studyType` varchar(30) NOT NULL COMMENT '修读方式',
  `weeklyTimes` varchar(30) NOT NULL COMMENT '周次比如（3-10周）',
  PRIMARY KEY (`courseId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
BEGIN;
INSERT INTO `course` VALUES (21, '高级数据库应用', 'COUR2019051313', 15, '1557734062748', '限选', '专业理论课', '考查', '正常', '3-16');
INSERT INTO `course` VALUES (22, 'JAVAEE', 'COUR2019051322', 18, '1557740285107', '限选', '专业理论课', '考试', '正常', '3-13');
INSERT INTO `course` VALUES (23, 'Android', 'COUR2019051323', 16, '1557740372071', '限选', '专业理论课', '考查', '正常', '3-14');
COMMIT;

-- ----------------------------
-- Table structure for lessons
-- ----------------------------
DROP TABLE IF EXISTS `lessons`;
CREATE TABLE `lessons` (
  `lessonId` int(10) NOT NULL AUTO_INCREMENT,
  `weekNum` tinyint(2) NOT NULL COMMENT '周次',
  `indexNum` tinyint(2) NOT NULL COMMENT '节次',
  `remark` varchar(40) DEFAULT NULL COMMENT '备注',
  `isSelect` tinyint(2) DEFAULT '0' COMMENT '是否被选',
  PRIMARY KEY (`lessonId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lessons
-- ----------------------------
BEGIN;
INSERT INTO `lessons` VALUES (2, 1, 1, '周一 第一大节', 1);
INSERT INTO `lessons` VALUES (3, 1, 2, '周一 第二大节', 0);
INSERT INTO `lessons` VALUES (4, 1, 3, '周一 第三大节', 0);
INSERT INTO `lessons` VALUES (5, 1, 4, '周一 第四大节', 1);
INSERT INTO `lessons` VALUES (6, 1, 5, '周一 第五大节', 0);
INSERT INTO `lessons` VALUES (7, 1, 6, '周一 第六大节', 0);
INSERT INTO `lessons` VALUES (8, 2, 1, '周二 第一大节', 0);
INSERT INTO `lessons` VALUES (9, 2, 2, '周二 第二大节', 1);
INSERT INTO `lessons` VALUES (10, 2, 3, '周二 第三大节', 0);
INSERT INTO `lessons` VALUES (11, 2, 4, '周二 第四大节', 0);
INSERT INTO `lessons` VALUES (12, 2, 5, '周二 第五大节', 0);
INSERT INTO `lessons` VALUES (13, 2, 6, '周二 第六大节', 0);
INSERT INTO `lessons` VALUES (14, 3, 1, '周三 第一大节', 0);
INSERT INTO `lessons` VALUES (15, 3, 2, '周三 第二大节', 1);
INSERT INTO `lessons` VALUES (16, 3, 3, '周三 第三大节', 0);
INSERT INTO `lessons` VALUES (17, 3, 4, '周三 第四大节', 0);
INSERT INTO `lessons` VALUES (18, 3, 5, '周三 第五大节', 0);
INSERT INTO `lessons` VALUES (19, 3, 6, '周三 第六大节', 0);
INSERT INTO `lessons` VALUES (20, 4, 1, '周四 第一大节', 1);
INSERT INTO `lessons` VALUES (21, 4, 2, '周四 第二大节', 0);
INSERT INTO `lessons` VALUES (22, 4, 3, '周四 第三大节', 0);
INSERT INTO `lessons` VALUES (23, 4, 4, '周四 第四大节', 0);
INSERT INTO `lessons` VALUES (24, 4, 5, '周四 第五大节', 0);
INSERT INTO `lessons` VALUES (25, 4, 6, '周四 第六大节', 0);
INSERT INTO `lessons` VALUES (26, 5, 1, '周五 第一大节', 0);
INSERT INTO `lessons` VALUES (27, 5, 2, '周五 第二大节', 0);
INSERT INTO `lessons` VALUES (28, 5, 3, '周五 第三大节', 1);
INSERT INTO `lessons` VALUES (29, 5, 4, '周五 第四大节', 0);
INSERT INTO `lessons` VALUES (30, 5, 5, '周五 第五大节', 0);
INSERT INTO `lessons` VALUES (31, 5, 6, '周五 第六大节', 0);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roleName` varchar(20) NOT NULL COMMENT '角色名称',
  `weight` int(4) NOT NULL COMMENT '权重等级 0 10 1000',
  `isDelete` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否有效(1为无效)',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '超级管理员', 0, 0);
INSERT INTO `role` VALUES (2, '学生', 100, 0);
INSERT INTO `role` VALUES (3, '教师', 10, 0);
COMMIT;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `roomId` int(10) NOT NULL AUTO_INCREMENT COMMENT '教室id',
  `roomNum` varchar(20) NOT NULL COMMENT '门牌号码',
  `peopleNum` int(4) NOT NULL COMMENT '容纳人数',
  `isMedia` tinyint(2) NOT NULL COMMENT '是否有多媒体',
  `isDelete` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否有效',
  PRIMARY KEY (`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
BEGIN;
INSERT INTO `room` VALUES (1, '8-310', 120, 1, 0);
INSERT INTO `room` VALUES (2, '8-210', 60, 1, 0);
INSERT INTO `room` VALUES (3, '8-211', 120, 1, 0);
INSERT INTO `room` VALUES (4, '8-311', 120, 1, 0);
INSERT INTO `room` VALUES (5, '8-4', 300, 1, 0);
INSERT INTO `room` VALUES (6, '8-5', 300, 1, 0);
INSERT INTO `room` VALUES (7, '8-6', 300, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `scId` int(10) NOT NULL AUTO_INCREMENT,
  `courseId` int(10) NOT NULL COMMENT '选择的课程',
  `sId` int(10) NOT NULL COMMENT '学生',
  `createTime` varchar(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`scId`),
  KEY `FK_STUDENT` (`sId`),
  KEY `FK_COURSE` (`courseId`),
  CONSTRAINT `FK_COURSE` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`),
  CONSTRAINT `FK_STUDENT` FOREIGN KEY (`sId`) REFERENCES `student` (`sId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc
-- ----------------------------
BEGIN;
INSERT INTO `sc` VALUES (7, 22, 14, '1557758392392');
INSERT INTO `sc` VALUES (8, 23, 14, '1557758392392');
INSERT INTO `sc` VALUES (9, 22, 1, '1557758392392');
INSERT INTO `sc` VALUES (10, 21, 14, '1557822072746');
COMMIT;

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `gId` int(10) NOT NULL AUTO_INCREMENT,
  `sId` int(10) NOT NULL COMMENT '学生',
  `courseId` int(10) NOT NULL COMMENT '课程',
  `score` int(10) DEFAULT NULL COMMENT '分数，保留两位',
  PRIMARY KEY (`gId`),
  KEY `FK_STU` (`sId`),
  KEY `FK_COU` (`courseId`),
  CONSTRAINT `FK_COU` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`),
  CONSTRAINT `FK_STU` FOREIGN KEY (`sId`) REFERENCES `student` (`sId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
BEGIN;
INSERT INTO `score` VALUES (1, 1, 23, 90);
INSERT INTO `score` VALUES (2, 14, 23, 20);
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sId` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sName` varchar(20) NOT NULL COMMENT '学生姓名',
  `classId` int(10) NOT NULL COMMENT '班级Id',
  `sNum` varchar(20) NOT NULL COMMENT '学号',
  `sAge` int(3) NOT NULL COMMENT '年龄',
  `startTime` varchar(20) NOT NULL COMMENT '入学时间',
  `createTime` varchar(20) NOT NULL COMMENT '创建时间',
  `isDelete` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否删除1为删除',
  PRIMARY KEY (`sId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (1, '张锦杰', 1, '16101321', 21, '2016-09-30', '21321313', 0);
INSERT INTO `student` VALUES (14, '张三', 5, '16101121', 22, '2016-09-30', '1557740915634', 0);
COMMIT;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `tId` int(10) NOT NULL AUTO_INCREMENT COMMENT '教师id',
  `tNum` varchar(30) NOT NULL COMMENT '职工编号',
  `tName` varchar(30) NOT NULL COMMENT '教师名称',
  `tPhone` varchar(20) NOT NULL COMMENT '联系方式',
  `tMajor` varchar(30) NOT NULL COMMENT '专业',
  `tPreCourse` varchar(40) DEFAULT NULL COMMENT '曾任课程',
  `createTime` varchar(20) NOT NULL COMMENT '创建时间',
  `startTime` varchar(20) NOT NULL COMMENT '入职时间',
  `isDelete` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否有效',
  PRIMARY KEY (`tId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
INSERT INTO `teacher` VALUES (14, 'TEA2019050214', '张三', '18622488052', '计算机科学与技术', '', '1557733186040', '2019-05-02', 0);
INSERT INTO `teacher` VALUES (15, 'TEA2019050215', '张美丽', '18622488052', '物联网', NULL, '1557733186084', '2019-05-02', 0);
INSERT INTO `teacher` VALUES (16, 'TEA2019050316', 'A', '18622488052', 'A', 'AAAS', '1557733738449', '2019-05-03', 0);
INSERT INTO `teacher` VALUES (17, 'TEA2019050417', 'B', '18622488052', 'B', NULL, '1557733787452', '2019-05-04', 0);
INSERT INTO `teacher` VALUES (18, 'TEA2019050918', 'C', '18622488052', 'C', NULL, '1557733818388', '2019-05-09', 0);
INSERT INTO `teacher` VALUES (19, 'TEA2019050819', 'D', '13752127826', '网络', '计算机网络', '1557733827552', '2019-05-08', 0);
COMMIT;

-- ----------------------------
-- Table structure for timetable
-- ----------------------------
DROP TABLE IF EXISTS `timetable`;
CREATE TABLE `timetable` (
  `cId` int(10) NOT NULL AUTO_INCREMENT,
  `roomId` int(10) NOT NULL COMMENT '教室id',
  `lessonId` int(10) NOT NULL COMMENT '节次id',
  `createTime` varchar(20) NOT NULL COMMENT '创建时间',
  `maxNum` int(4) NOT NULL COMMENT '限选人数',
  `courseId` int(10) NOT NULL COMMENT '课程id',
  PRIMARY KEY (`cId`),
  KEY `FK_room` (`roomId`),
  KEY `FK_lesson` (`lessonId`),
  CONSTRAINT `FK_lesson` FOREIGN KEY (`lessonId`) REFERENCES `lessons` (`lessonId`),
  CONSTRAINT `FK_room` FOREIGN KEY (`roomId`) REFERENCES `room` (`roomId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of timetable
-- ----------------------------
BEGIN;
INSERT INTO `timetable` VALUES (17, 4, 2, '1557734087117', 120, 21);
INSERT INTO `timetable` VALUES (18, 3, 9, '1557734087116', 213, 21);
INSERT INTO `timetable` VALUES (19, 4, 15, '1557740315512', 120, 22);
INSERT INTO `timetable` VALUES (20, 5, 5, '1557740315510', 200, 22);
INSERT INTO `timetable` VALUES (21, 3, 20, '1557740399954', 100, 23);
INSERT INTO `timetable` VALUES (22, 5, 28, '1557740399954', 120, 23);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
