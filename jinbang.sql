/*
 Navicat Premium Data Transfer

 Source Server         : mysql-pc
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : jinbang

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 14/05/2019 09:17:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `asrid` int(11) NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`asrid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of answer
-- ----------------------------
BEGIN;
INSERT INTO `answer` VALUES (1, '11'), (2, 'By the time this railroad has been extended to the remote small city, the local economy is sure to develop/be developed very fast.'), (3, 'x=ln(4)/ln(2)'), (4, '13'), (5, 'A'), (6, 'if your pen friend has replied'), (7, 'is actively taking action(s) to'), (8, '(to be) one of the most popular programs'), (9, 'out of breath'), (10, 'should be paid to'), (11, 'enough sleep because of');
COMMIT;

-- ----------------------------
-- Table structure for buildpaper
-- ----------------------------
DROP TABLE IF EXISTS `buildpaper`;
CREATE TABLE `buildpaper`  (
  `pid` int(11) NOT NULL,
  `iid` int(11) NOT NULL,
  `itemorder` int(2) NULL DEFAULT 0,
  `score` int(3) NULL DEFAULT 0,
  `timemin` int(3) NULL DEFAULT 0,
  PRIMARY KEY (`pid`, `iid`) USING BTREE,
  INDEX `fk_item_bpaper`(`iid`) USING BTREE,
  CONSTRAINT `fk_item_bpaper` FOREIGN KEY (`iid`) REFERENCES `item` (`iid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_paper_bpaper` FOREIGN KEY (`pid`) REFERENCES `paper` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of buildpaper
-- ----------------------------
BEGIN;
INSERT INTO `buildpaper` VALUES (1, 1, 1, 5, 5);
COMMIT;

-- ----------------------------
-- Table structure for imgofasr
-- ----------------------------
DROP TABLE IF EXISTS `imgofasr`;
CREATE TABLE `imgofasr`  (
  `asrimgid` int(11) NOT NULL,
  `asrid` int(11) NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`asrimgid`) USING BTREE,
  INDEX `fk_asr_imgofitem`(`asrid`) USING BTREE,
  CONSTRAINT `fk_asr_imgofitem` FOREIGN KEY (`asrid`) REFERENCES `answer` (`asrid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for imgofitem
-- ----------------------------
DROP TABLE IF EXISTS `imgofitem`;
CREATE TABLE `imgofitem`  (
  `iimgid` int(11) NOT NULL,
  `iid` int(11) NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`iimgid`) USING BTREE,
  INDEX `fk_item_imgofitem`(`iid`) USING BTREE,
  CONSTRAINT `fk_item_imgofitem` FOREIGN KEY (`iid`) REFERENCES `item` (`iid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `iid` int(11) NOT NULL,
  `type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `source` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `difficulty` int(1) NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `asrid` int(11) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`iid`) USING BTREE,
  INDEX `fk_user_item`(`uid`) USING BTREE,
  INDEX `fk_asr_item`(`asrid`) USING BTREE,
  CONSTRAINT `fk_asr_item` FOREIGN KEY (`asrid`) REFERENCES `answer` (`asrid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_item` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of item
-- ----------------------------
BEGIN;
INSERT INTO `item` VALUES (1, '填空', '小学三年级', '上大附中2019年第一次月考', 5, 1, 1, '三（1）班小朋友做玩具，一共做了48个，送给幼儿园15个，其余的平均分给一年级3个班，每班可以分得____个.'), (2, '翻译', '高中三年级', '上海财经大学附属中学2016年9月月考', 5, 1, 2, '等这条铁路延伸至那座偏远的小城之后，当地的经济一定会得到迅猛的发展。（by the time）'), (3, '计算', '高中三年级', '宝山区2016届高三上学期期末', 5, 1, 3, '计算方程 4^x-2^x-6=0 的解.'), (4, '填空', '小学一年级', '自编', 5, 2, 4, '5+8=____.'), (5, '选择', '初中二年级', '21世纪教育在线题库', 5, 3, 5, '清华大学的虞昊教授致力于第三代照明技术——LED灯的普及工作。LED灯的能耗小，造价低廉，使用寿命长.虞昊自制的LED台灯，用电压为6伏的电源供电，正常工作电流为300毫安。那么，此LED台灯正常工作时的功率为(  ). <br/> A．1.8瓦	B．18瓦	C．180瓦	D．1800瓦'), (6, '翻译', '初中二年级', '21世纪教育在线题库', 5, 3, 6, '我想知道你的笔友是否已经回复你的电子邮件。<br/> I wonder ___________________________________ to your e-mail.'), (7, '翻译', '初中二年级', '21世纪教育在线题库', 5, 2, 7, '据报道，近来日本政府正积极采取措施减少核泄漏(nuclear leak)。<br/> It is reported that the Japanese government ______________reduce the nuclear leak recently.'), (8, '翻译', '初中二年级', '21世纪教育在线题库', 5, 2, 8, '“中国达人秀”被认为是目前最受欢迎的电视节目之一。<br/> China’s Got Talent is considered _____________________________ these days.'), (9, '翻译', '高中一年级', '21世纪教育在线题库', 5, 2, 9, 'Daniel气喘吁吁地赶到了，却发现他跑错了电影院。<br/> Daniel arrived _______________, but he found he had gone to the wrong cinema.'), (10, '翻译', '高中一年级', '21世纪教育在线题库', 5, 2, 10, '公共场合应该注意行为举止。<br/> Attention ________________________ one’s behavior in public.'), (11, '翻译', '初中一年级', '21世纪教育在线题库', 5, 2, 11, '现在许多青少年由于过重的作业负担而得不到充足的睡眠。<br/> Many teenagers can’t have ______________________________too much homework.');
COMMIT;

-- ----------------------------
-- Table structure for item_kp
-- ----------------------------
DROP TABLE IF EXISTS `item_kp`;
CREATE TABLE `item_kp`  (
  `iid` int(11) NOT NULL,
  `kpid` int(11) NOT NULL,
  `degree` int(1) NULL DEFAULT 5,
  PRIMARY KEY (`iid`, `kpid`) USING BTREE,
  INDEX `fk_kp_itemkp`(`kpid`) USING BTREE,
  CONSTRAINT `fk_item_itemkp` FOREIGN KEY (`iid`) REFERENCES `item` (`iid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_kp_itemkp` FOREIGN KEY (`kpid`) REFERENCES `knowledgepoint` (`kpid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of item_kp
-- ----------------------------
BEGIN;
INSERT INTO `item_kp` VALUES (1, 2, 5), (2, 5, 5), (3, 6, 5), (3, 7, 5), (4, 8, 5), (5, 11, 5), (6, 12, 5), (6, 13, 5), (7, 14, 5), (8, 15, 5), (9, 16, 5), (10, 18, 5), (11, 19, 5);
COMMIT;

-- ----------------------------
-- Table structure for knowledgepoint
-- ----------------------------
DROP TABLE IF EXISTS `knowledgepoint`;
CREATE TABLE `knowledgepoint`  (
  `kpid` int(11) NOT NULL,
  `knowledgepoint` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `prepoint` int(11) NULL DEFAULT -1,
  `depth` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`kpid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of knowledgepoint
-- ----------------------------
BEGIN;
INSERT INTO `knowledgepoint` VALUES (1, '数学', -1, 1), (2, '四则混合运算', 1, 2), (3, '英语', -1, 1), (4, '时态', 3, 2), (5, '将来进行时', 4, 3), (6, '对数运算', 1, 2), (7, '一元二次方程换元法', 1, 2), (8, '十以内加法运算', 1, 2), (9, '物理', -1, 1), (10, '电学', 9, 2), (11, '电功与电热', 10, 3), (12, '宾语从句', 3, 2), (13, '现在完成时', 3, 2), (14, '现在进行时', 3, 2), (15, '最高级', 3, 2), (16, '过去完成时', 3, 2), (17, '情态动词', 3, 2), (18, 'should', 17, 3), (19, '固定短语', 3, 2);
COMMIT;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
  `pid` int(11) NOT NULL,
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `sumscore` int(3) NULL DEFAULT 0,
  `sumtimemin` int(3) NULL DEFAULT 0,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `fk_user_paper`(`uid`) USING BTREE,
  CONSTRAINT `fk_user_paper` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of paper
-- ----------------------------
BEGIN;
INSERT INTO `paper` VALUES (1, '三年级数学测试卷', 1, 5, 5);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL,
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authority` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '建国', 'e10adc3949ba59abbe56e057f20f883e', '录入组卷员'), (2, '铁柱', 'e10adc3949ba59abbe56e057f20f883e', '录入组卷员'), (3, '翠花', 'e10adc3949ba59abbe56e057f20f883e', '录入组卷员');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
