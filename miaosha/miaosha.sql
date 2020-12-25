/*
 Navicat Premium Data Transfer

 Source Server         : 111
 Source Server Type    : MySQL
 Source Server Version : 50709
 Source Host           : localhost:3306
 Source Schema         : miaosha

 Target Server Type    : MySQL
 Target Server Version : 50709
 File Encoding         : 65001

 Date: 16/07/2019 10:07:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                         `price` decimal(10, 2) NULL DEFAULT 0.00,
                         `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                         `sales` int(11) NOT NULL DEFAULT 0,
                         `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (1, 'iPhone Max', 9999.00, '苹果手机', 28, 'https://img14.360buyimg.com/n0/jfs/t1/4460/2/3458/153299/5b997bf0Ed101778b/2361563781a99acb.jpg');
INSERT INTO `item` VALUES (2, '荣耀8X', 1300.00, '千元屏霸 91%屏占比 2000万AI双摄 4GB+64GB 幻夜黑 移动联通电信4G全面屏手机 双卡双待', 26, 'https://img14.360buyimg.com/n0/jfs/t1/21333/14/5246/180334/5c3ad7b6Ef7d727c0/c16e93d0bf77a31f.jpg');
INSERT INTO `item` VALUES (3, '苹果手机', 4999.00, '手机', 4, 'http://img4.imgtn.bdimg.com/it/u=558459014,2890088372&fm=26&gp=0.jpg');
INSERT INTO `item` VALUES (5, '苹果手机', 4999.00, '手机', 0, 'asdfdasfdsaf');
INSERT INTO `item` VALUES (6, '苹果手机', 4999.00, '手机', 0, 'asdfdasfdsaf');
INSERT INTO `item` VALUES (7, '苹果手机', 4999.00, '手机', 0, 'asdfdasfdsaf');

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `stock` int(11) NOT NULL DEFAULT 0,
                               `item_id` int(11) NOT NULL DEFAULT 0,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_stock
-- ----------------------------
INSERT INTO `item_stock` VALUES (1, 89, 1);
INSERT INTO `item_stock` VALUES (2, 95, 2);
INSERT INTO `item_stock` VALUES (3, 8, 3);
INSERT INTO `item_stock` VALUES (4, 12, 4);
INSERT INTO `item_stock` VALUES (5, 12, 5);
INSERT INTO `item_stock` VALUES (6, 12, 6);
INSERT INTO `item_stock` VALUES (7, 12, 7);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
                               `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                               `user_id` int(11) NOT NULL DEFAULT 0 COMMENT '用户id',
                               `item_id` int(11) NOT NULL DEFAULT 0 COMMENT '商品id',
                               `item_price` decimal(10, 2) NOT NULL DEFAULT 0.00,
                               `amount` int(11) NOT NULL,
                               `order_account` decimal(10, 2) NOT NULL,
                               `promo_id` int(11) NOT NULL DEFAULT 0,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('2019031100000000', 6, 2, 1300.00, 1, 1300.00, 0);
INSERT INTO `order_info` VALUES ('2019031100000100', 6, 2, 1300.00, 1, 1300.00, 0);
INSERT INTO `order_info` VALUES ('2019031100000200', 6, 2, 1300.00, 1, 1300.00, 0);
INSERT INTO `order_info` VALUES ('2019031100000300', 6, 1, 100.00, 1, 100.00, 1);
INSERT INTO `order_info` VALUES ('2019071500000000', 1, 1, 9999.00, 1, 9999.00, 0);
INSERT INTO `order_info` VALUES ('2019071500000500', 1, 1, 9999.00, 1, 9999.00, 0);
INSERT INTO `order_info` VALUES ('2019071500000600', 1, 1, 9999.00, 1, 9999.00, 0);
INSERT INTO `order_info` VALUES ('2019071500000700', 1, 1, 9999.00, 1, 9999.00, 0);
INSERT INTO `order_info` VALUES ('2019071500000800', 8, 1, 9999.00, 1, 9999.00, 0);
INSERT INTO `order_info` VALUES ('2019071500000900', 8, 1, 9999.00, 1, 9999.00, 0);
INSERT INTO `order_info` VALUES ('2019071500001000', 8, 1, 9999.00, 1, 9999.00, 0);
INSERT INTO `order_info` VALUES ('2019071500001100', 8, 3, 4999.00, 1, 4999.00, 0);
INSERT INTO `order_info` VALUES ('2019071500001200', 8, 3, 4999.00, 1, 4999.00, 0);
INSERT INTO `order_info` VALUES ('2019071500001300', 8, 2, 1300.00, 1, 1300.00, 0);
INSERT INTO `order_info` VALUES ('2019071500001400', 8, 1, 9999.00, 1, 9999.00, 0);
INSERT INTO `order_info` VALUES ('2019071500001500', 8, 2, 100.00, 1, 100.00, 2);
INSERT INTO `order_info` VALUES ('2019071500001600', 8, 3, 4999.00, 1, 4999.00, 0);
INSERT INTO `order_info` VALUES ('2019071600001700', 8, 3, 4999.00, 1, 4999.00, 0);
INSERT INTO `order_info` VALUES ('2019071600001800', 8, 1, 100.00, 1, 100.00, 3);
INSERT INTO `order_info` VALUES ('2019071600001900', 8, 1, 100.00, 1, 100.00, 3);

-- ----------------------------
-- Table structure for promo
-- ----------------------------
DROP TABLE IF EXISTS `promo`;
CREATE TABLE `promo`  (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `promo_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                          `start_date` datetime(0) NOT NULL,
                          `end_date` datetime(0) NOT NULL,
                          `item_id` int(11) NOT NULL DEFAULT 0,
                          `promo_item_price` decimal(10, 2) NOT NULL DEFAULT 0.00,
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promo
-- ----------------------------
INSERT INTO `promo` VALUES (2, '抢购', '2019-07-15 19:00:00', '2019-07-15 23:00:00', 2, 100.00);
INSERT INTO `promo` VALUES (3, 'iphone抢购', '2019-07-15 19:41:00', '2020-12-11 00:00:00', 1, 100.00);

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info`  (
                                  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                  `current_value` int(11) NOT NULL DEFAULT 0 COMMENT '当前值',
                                  `step` int(11) NOT NULL DEFAULT 0 COMMENT '步长',
                                  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sequence_info
-- ----------------------------
INSERT INTO `sequence_info` VALUES ('order_info', 20, 1);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                              `gender` tinyint(4) NOT NULL DEFAULT 0 COMMENT '1-男性；2女性',
                              `age` int(11) NOT NULL,
                              `telphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
                              `register_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '//byphone,bywechat,byalipay',
                              `third_party_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '第三方登陆账号id',
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE INDEX `telphone`(`telphone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (6, 'zhongchao', 1, 11, '17682310538', 'byphone', '');
INSERT INTO `user_info` VALUES (8, '1', 1, 12, '15726021813', 'byphone', '');
INSERT INTO `user_info` VALUES (9, '1', 1, 151, '123456789', 'byphone', '');
INSERT INTO `user_info` VALUES (11, '1', 1, 150, '987654321', 'byphone', '');

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password`  (
                                  `id` int(11) NOT NULL AUTO_INCREMENT,
                                  `encrpt_password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                  `user_id` int(11) NOT NULL,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES (2, '4QrcOUm6Wau+VuBX8g+IPg==', 6);
INSERT INTO `user_password` VALUES (3, '4QrcOUm6Wau+VuBX8g+IPg==', 8);
INSERT INTO `user_password` VALUES (4, '4QrcOUm6Wau+VuBX8g+IPg==', 9);
INSERT INTO `user_password` VALUES (5, '4QrcOUm6Wau+VuBX8g+IPg==', 11);

SET FOREIGN_KEY_CHECKS = 1;