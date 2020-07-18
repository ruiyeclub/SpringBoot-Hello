/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : boot_hello

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 18/07/2020 10:23:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `deptno` bigint(20) NOT NULL AUTO_INCREMENT,
  `dname` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `db_source` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`deptno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '开发部', 't_learning');
INSERT INTO `dept` VALUES (2, '人事部', 't_learning');
INSERT INTO `dept` VALUES (3, '财务部', 't_learning');
INSERT INTO `dept` VALUES (4, '市场部', 't_learning');
INSERT INTO `dept` VALUES (5, '运维部', 't_learning');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `SNO` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SNAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SSEX` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DATASOURCE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('001', 'KangKang', 'M', 'mysql');
INSERT INTO `student` VALUES ('002', 'Mike', 'M', 'mysql');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'ccc', '123', NULL);
INSERT INTO `user` VALUES (2, 'ray', '321', 'user:add');
INSERT INTO `user` VALUES (3, '我爱java', '545', NULL);
INSERT INTO `user` VALUES (4, 'admin', '123456', 'user:update');

-- ----------------------------
-- Table structure for user_msg
-- ----------------------------
DROP TABLE IF EXISTS `user_msg`;
CREATE TABLE `user_msg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_msg
-- ----------------------------
INSERT INTO `user_msg` VALUES (1, '亪', '今天不开心', '2018-08-14 14:26:02', '2018-08-14 14:26:02');
INSERT INTO `user_msg` VALUES (2, '祐', '不错呀', '2018-08-14 15:09:40', '2018-08-14 15:09:40');
INSERT INTO `user_msg` VALUES (3, '搈', '开心 开心', '2018-08-14 15:09:40', '2018-08-14 15:09:40');
INSERT INTO `user_msg` VALUES (4, '兇', '可以的，后面再做个深入一点的', '2018-08-14 15:18:35', '2018-08-14 15:18:35');
INSERT INTO `user_msg` VALUES (5, '倎', '开源这个项目', '2018-08-14 15:18:35', '2018-08-14 15:18:35');
INSERT INTO `user_msg` VALUES (6, '蝡', '1-someting', '2018-08-14 15:24:28', '2018-08-14 15:24:28');
INSERT INTO `user_msg` VALUES (7, '弔', '不行呀', '2018-08-14 15:24:29', '2018-08-14 15:24:29');
INSERT INTO `user_msg` VALUES (8, '習', '可以的', '2018-08-14 15:26:03', '2018-08-14 15:26:03');
INSERT INTO `user_msg` VALUES (9, '蔫', '开源这个项目', '2018-08-14 15:26:03', '2018-08-14 15:26:03');

-- ----------------------------
-- Table structure for user_record
-- ----------------------------
DROP TABLE IF EXISTS `user_record`;
CREATE TABLE `user_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int(4) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_record
-- ----------------------------
INSERT INTO `user_record` VALUES (1, 'cr', 21, 'ruiyeclub@foxmail.com');
INSERT INTO `user_record` VALUES (2, 'test', 0, 'test');

SET FOREIGN_KEY_CHECKS = 1;
