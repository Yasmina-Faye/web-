/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80038
 Source Host           : localhost:3306
 Source Schema         : goodsshop

 Target Server Type    : MySQL
 Target Server Version : 80038
 File Encoding         : 65001

 Date: 01/01/2025 21:39:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_admin
-- ----------------------------
DROP TABLE IF EXISTS `s_admin`;
CREATE TABLE `s_admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `passWord` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `lastLoginTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_admin
-- ----------------------------
INSERT INTO `s_admin` VALUES (2, 'admin', '123456', '管理员', '2025-01-01 21:33:17');

-- ----------------------------
-- Table structure for s_catalog
-- ----------------------------
DROP TABLE IF EXISTS `s_catalog`;
CREATE TABLE `s_catalog`  (
  `catalogId` int NOT NULL AUTO_INCREMENT,
  `catalogName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`catalogId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_catalog
-- ----------------------------
INSERT INTO `s_catalog` VALUES (1, '图书');
INSERT INTO `s_catalog` VALUES (2, '手机');
INSERT INTO `s_catalog` VALUES (3, '服装');

-- ----------------------------
-- Table structure for s_goods
-- ----------------------------
DROP TABLE IF EXISTS `s_goods`;
CREATE TABLE `s_goods`  (
  `goodsId` int NOT NULL AUTO_INCREMENT,
  `catalogId` int NOT NULL,
  `goodsName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
  `origin` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
  `supplier` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
  `price` double(10, 2) NOT NULL,
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `imgId` int NOT NULL,
  `addTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`goodsId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_goods
-- ----------------------------
INSERT INTO `s_goods` VALUES (47, 1, '万物起源', '英国《新科学家》杂志', '湖南科学技术出版社', 102.60, '我们来自哪里？所有事物是如何发端的？\r\n\r\n这是宇宙间ZUI大的问题，New Scientist告诉你答案……\r\n\r\n本书精心选择了具有代表性的六大类53个题目，从大爆炸、暗物质、生命起源、人类进化、金钱的历史、酒的历史、财产的前世今生、人类情感背后的基因驱动到卫生纸的发明和鼻屎的秘密。简洁诗意的叙述，随时随地颠覆你的常识，时常脑洞大开，偶尔恶趣味。', 61, '2025-01-02 21:27:36');
INSERT INTO `s_goods` VALUES (48, 1, '所罗门王的指环', '[奥] 康拉德·洛伦茨 ', '中信出版社 ', 33.30, '《所罗门王的指环》是著名科普作家、诺贝尔卡生理学获得者洛伦茨的经典科普著作。本书将科学知识与文学趣味巧妙结合，将读者大众引入有趣的动物行为学世界，作者通过介绍斗鱼、水鼩、寒鸦等动物的生动故事，使人们认识到大自然的美好，认识到动物行为学的意义所在。\r\n为什么书名叫《所罗门王的指环》呢？洛伦茨说：“根据史料记载，所罗门王能够和鸟兽虫鱼交谈，而他却需要借助一枚指环。这事我也会，虽然我只能和几种我特别熟悉的动物交谈，但我可不需要魔戒的帮助，这点他就不如我啦！活泼的生命完全无须借助魔法，便能对我们诉说至美至真的故事。大自然的真实面貌，比起诗人所能描摹的境界，更要美上千百倍。”', 62, '2025-01-02 21:27:36');
INSERT INTO `s_goods` VALUES (49, 1, '汽车杂志（2022年10月号）', '陈政义 ', ' 四川汽车杂志出版有限公司', 17.30, '汽车杂志（2022年10月号）', 63, '2025-01-02 21:27:36');
INSERT INTO `s_goods` VALUES (50, 1, '意林合订本2022年01期-06期', '大意林图书 ', '意林杂志社', 28.30, '意林合订本，小故事大智慧，小幽默大道理，小视角大意境', 64, '2025-01-02 21:27:36');
INSERT INTO `s_goods` VALUES (51, 1, '三联生活周刊（2022年第38期）', '三联生活周刊编辑部', '生活·读书·新知三联书店', 12.40, '明治维新到明年正好150周年，正是美学引入日本的时间。经过东西方文化冲突的十字路口，日本先全面向西，然而zui终没有入欧，也无法脱亚，并存优越感与劣等感，近年又走向了回归。福泽谕吉的现代化与夏目漱石的现代化不同。强调内心本位的夏目漱石越来越被知识界提到了前面。这两种现代化形成作用力的成果是：工业化，全面教育', 65, '2025-01-02 21:27:36');
INSERT INTO `s_goods` VALUES (52, 1, '瑞丽家居设计（2022年第10期）', '周小捷 ', '北京《瑞丽》杂志社', 17.30, '瑞丽家居设计（2017年第10期）\r\n　　细节蜕变\r\n　　细微之处的完美情结\r\n　　在细节中闪光\r\n　　细节控的完美生活\r\n　　点缀不羁色\r\n　　方寸间的乾坤\r\n　　……', 66, '2025-01-02 21:27:36');
INSERT INTO `s_goods` VALUES (55, 2, '小米手机', '北京', '小米公司', 3999.00, NULL, 69, '2025-01-02 21:27:36');
INSERT INTO `s_goods` VALUES (56, 2, 'iPhone16手机', '中国', '苹果公司', 5999.00, '新款苹果手机，数量不多', 70, '2025-01-02 21:27:36');
INSERT INTO `s_goods` VALUES (57, 2, 'vivo x100', '中国', 'vivo公司', 3999.00, '一流处理器\r\n流程的系统\r\n拍照超级厉害', 71, '2025-01-02 21:27:36');
INSERT INTO `s_goods` VALUES (58, 2, '华为手机', '中国', '华为手机公司', 5999.00, '华为新款手机\r\n国产芯片', 72, '2025-01-02 21:27:36');
INSERT INTO `s_goods` VALUES (59, 2, '红米手机', '中国', '红米手机公司', 1999.00, '红米手机', 73, '2025-01-02 21:27:36');
INSERT INTO `s_goods` VALUES (60, 3, '冬季女上衣外套', '中国', '服装公司', 199.00, '纯棉冬季', 74, '2025-01-02 21:27:36');
INSERT INTO `s_goods` VALUES (61, 3, '冬季女裤', '中国', '服装公司', 159.00, '冬季女裤', 75, '2025-01-02 21:27:36');

-- ----------------------------
-- Table structure for s_order
-- ----------------------------
DROP TABLE IF EXISTS `s_order`;
CREATE TABLE `s_order`  (
  `orderId` int NOT NULL AUTO_INCREMENT,
  `orderNum` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
  `userId` int NOT NULL,
  `orderDate` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `money` double(10, 2) NOT NULL DEFAULT 0.00,
  `orderStatus` int NOT NULL,
  PRIMARY KEY (`orderId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_order
-- ----------------------------
INSERT INTO `s_order` VALUES (1, '202409261544415841968822923', 8, '2025-01-02 21:27:36', 3999.00, 2);
INSERT INTO `s_order` VALUES (2, '20240926200757859438420559', 1, '2025-01-02 21:27:36', 33.30, 1);
INSERT INTO `s_order` VALUES (3, '202501012136260382030534625', 1, '2025-01-02 21:27:36', 5999.00, 1);

-- ----------------------------
-- Table structure for s_orderitem
-- ----------------------------
DROP TABLE IF EXISTS `s_orderitem`;
CREATE TABLE `s_orderitem`  (
  `itemId` int NOT NULL AUTO_INCREMENT,
  `goodsId` int NOT NULL,
  `orderId` int NOT NULL DEFAULT 0,
  `quantity` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`itemId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_orderitem
-- ----------------------------
INSERT INTO `s_orderitem` VALUES (1, 57, 1, 1);
INSERT INTO `s_orderitem` VALUES (2, 48, 2, 1);
INSERT INTO `s_orderitem` VALUES (3, 58, 3, 1);

-- ----------------------------
-- Table structure for s_uploadimg
-- ----------------------------
DROP TABLE IF EXISTS `s_uploadimg`;
CREATE TABLE `s_uploadimg`  (
  `imgId` int NOT NULL AUTO_INCREMENT,
  `imgName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `imgSrc` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `imgType` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`imgId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_uploadimg
-- ----------------------------
INSERT INTO `s_uploadimg` VALUES (61, 'da18420707624f8ab30cd2d4e7c6de2b.jpg', 'images/goods/goodsimg/da18420707624f8ab30cd2d4e7c6de2b.jpg', 'image/jpeg');
INSERT INTO `s_uploadimg` VALUES (62, '818f012c5b7a4109b76aed5eab3c86d7.jpg', 'images/goods/goodsimg/818f012c5b7a4109b76aed5eab3c86d7.jpg', 'image/jpeg');
INSERT INTO `s_uploadimg` VALUES (63, '18613c972e0f4b88a369b062f13cff3d.jpg', 'images/goods/goodsimg/18613c972e0f4b88a369b062f13cff3d.jpg', 'image/jpeg');
INSERT INTO `s_uploadimg` VALUES (64, '6767e8a6bcb344269be32ed3f98f432c.jpg', 'images/goods/goodsimg/6767e8a6bcb344269be32ed3f98f432c.jpg', 'image/jpeg');
INSERT INTO `s_uploadimg` VALUES (65, 'e6c925a657e049729f9b57ad688f2708.jpg', 'images/goods/goodsimg/e6c925a657e049729f9b57ad688f2708.jpg', 'image/jpeg');
INSERT INTO `s_uploadimg` VALUES (66, '1f3d4937bac1467c9ef69d0141d8800f.jpg', 'images/goods/goodsimg/1f3d4937bac1467c9ef69d0141d8800f.jpg', 'image/jpeg');
INSERT INTO `s_uploadimg` VALUES (67, 'a348d4a5f9d94923bf20ea8604837178.png', 'images/goods/goodsimg/a348d4a5f9d94923bf20ea8604837178.png', 'image/png');
INSERT INTO `s_uploadimg` VALUES (68, 'b5b88bce978b4ed997bdc8f93040bb4f.jpg', 'images/goods/goodsimg/b5b88bce978b4ed997bdc8f93040bb4f.jpg', 'image/jpeg');
INSERT INTO `s_uploadimg` VALUES (69, 'f932a6862b57410cb60d2cc3d2bdb542.png', 'images/front/goodsimg/f932a6862b57410cb60d2cc3d2bdb542.png', 'image/png');
INSERT INTO `s_uploadimg` VALUES (70, '9da685e201854184aee4d1538173ee59.png', 'images/front/goodsimg/9da685e201854184aee4d1538173ee59.png', 'image/png');
INSERT INTO `s_uploadimg` VALUES (71, 'ad2f7e47d9a74b74b35d0d17e0503045.png', 'images/front/goodsimg/ad2f7e47d9a74b74b35d0d17e0503045.png', 'image/png');
INSERT INTO `s_uploadimg` VALUES (72, 'e0f11a333e964e13bd495b174a5eb31b.png', 'images/front/goodsimg/e0f11a333e964e13bd495b174a5eb31b.png', 'image/png');
INSERT INTO `s_uploadimg` VALUES (73, '997e2fd6ab4c4b9984870054ece45381.png', 'images/front/goodsimg/997e2fd6ab4c4b9984870054ece45381.png', 'image/png');
INSERT INTO `s_uploadimg` VALUES (74, 'da1303f7f8064a2590486a933dd9fda4.png', 'images/front/goodsimg/da1303f7f8064a2590486a933dd9fda4.png', 'image/png');
INSERT INTO `s_uploadimg` VALUES (75, 'b4b34a1309464feca6b96f2027323251.png', 'images/front/goodsimg/b4b34a1309464feca6b96f2027323251.png', 'image/png');

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user`  (
  `userId` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `userPassWord` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `age` int NOT NULL,
  `tell` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `address` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `enabled` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES (1, 'zhangsan', '123456', '张三', '女', 22, '11800000000', '番茄炒鸡蛋学院', 'y');
INSERT INTO `s_user` VALUES (8, 'lisi', '123456', '李四', '男', 23, '13302747116', '北京市朝阳区', 'y');

SET FOREIGN_KEY_CHECKS = 1;
