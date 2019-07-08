/*
 Navicat Premium Data Transfer

 Source Server         : mysql-pc
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : shirojinbang

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 08/07/2019 19:15:44
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
  `itemorder` int(2) NULL DEFAULT -1,
  `score` int(3) NULL DEFAULT -1,
  `timemin` int(3) NULL DEFAULT -1,
  PRIMARY KEY (`pid`, `iid`) USING BTREE,
  INDEX `fk_item_bpaper`(`iid`) USING BTREE,
  CONSTRAINT `fk_item_bpaper` FOREIGN KEY (`iid`) REFERENCES `item` (`iid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_paper_bpaper` FOREIGN KEY (`pid`) REFERENCES `paper` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of buildpaper
-- ----------------------------
BEGIN;
INSERT INTO `buildpaper` VALUES (1, 6, 4, 5, 5), (1, 7, 1, 2, 2), (1, 8, 3, 1, 1), (1, 9, 2, 4, 4);
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
  CONSTRAINT `fk_shirouser_item` FOREIGN KEY (`uid`) REFERENCES `shiro_user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of item
-- ----------------------------
BEGIN;
INSERT INTO `item` VALUES (1, '填空', '小学三年级', '上大附中2019年第一次月考', 5, 1, 1, '三（1）班小朋友做玩具，一共做了48个，送给幼儿园15个，其余的平均分给一年级3个班，每班可以分得____个.'), (2, '翻译', '高中三年级', '上海财经大学附属中学2016年9月月考', 5, 2, 2, '等这条铁路延伸至那座偏远的小城之后，当地的经济一定会得到迅猛的发展。（by the time）'), (3, '计算', '高中三年级', '宝山区2016届高三上学期期末', 5, 3, 3, '计算方程 4^x-2^x-6=0 的解.'), (4, '填空', '小学一年级', '自编', 5, 1, 4, '5+8=____.'), (5, '选择', '初中二年级', '21世纪教育在线题库', 5, 2, 5, '清华大学的虞昊教授致力于第三代照明技术——LED灯的普及工作。LED灯的能耗小，造价低廉，使用寿命长.虞昊自制的LED台灯，用电压为6伏的电源供电，正常工作电流为300毫安。那么，此LED台灯正常工作时的功率为(  ). \r\n<br/> A．1.8瓦	B．18瓦	C．180瓦	D．1800瓦'), (6, '翻译', '初中二年级', '21世纪教育在线题库', 5, 3, 6, '我想知道你的笔友是否已经回复你的电子邮件。<br/> I wonder ___________________________________ to your e-mail.'), (7, '翻译', '初中二年级', '21世纪教育在线题库', 5, 1, 7, '据报道，近来日本政府正积极采取措施减少核泄漏(nuclear leak)。<br/> It is reported that the Japanese government ______________reduce the nuclear leak recently.'), (8, '翻译', '初中二年级', '21世纪教育在线题库', 5, 2, 8, '“中国达人秀”被认为是目前最受欢迎的电视节目之一。<br/> China’s Got Talent is considered _____________________________ these days.'), (9, '翻译', '高中一年级', '21世纪教育在线题库', 5, 3, 9, 'Daniel气喘吁吁地赶到了，却发现他跑错了电影院。<br/> Daniel arrived _______________, but he found he had gone to the wrong cinema.'), (10, '翻译', '高中一年级', '21世纪教育在线题库', 5, 1, 10, '公共场合应该注意行为举止。<br/> Attention ________________________ one’s behavior in public.'), (11, '翻译', '初中一年级', '21世纪教育在线题库', 5, 2, 11, '现在许多青少年由于过重的作业负担而得不到充足的睡眠。<br/> Many teenagers can’t have ______________________________too much homework.');
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
INSERT INTO `knowledgepoint` VALUES (1, '数学', -1, 1), (2, '四则混合运算', 1, 2), (3, '英语', -1, 1), (4, '时态', 3, 2), (5, '将来进行时', 4, 3), (6, '对数运算', 1, 2), (7, '一元二次方程换元法', 1, 2), (8, '十以内加法运算', 1, 2), (9, '物理', -1, 1), (10, '电学', 9, 2), (11, '电功与电热', 10, 3), (12, '宾语从句', 3, 2), (13, '现在完成时', 3, 2), (14, '现在进行时', 3, 2), (15, '最高级', 3, 2), (16, '过去完成时', 3, 2), (17, '情态动词', 3, 2), (18, 'should', 17, 3), (19, '固定短语', 3, 2), (20, '统计学', 1, 2), (21, '正态分布', 20, 3), (22, '化学', -1, 1), (23, '无机化学', 22, 2), (24, '氧化还原反应', 23, 3);
COMMIT;

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
  `pid` int(11) NOT NULL,
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  `sumscore` int(3) NULL DEFAULT -1,
  `sumtimemin` int(3) NULL DEFAULT -1,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `fk_user_paper`(`uid`) USING BTREE,
  CONSTRAINT `fk_user_paper` FOREIGN KEY (`uid`) REFERENCES `shiro_user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of paper
-- ----------------------------
BEGIN;
INSERT INTO `paper` VALUES (1, '英语翻译测试', 3, 12, 12), (2, '试卷5', 1, -1, -1);
COMMIT;

-- ----------------------------
-- Table structure for shiro_resource
-- ----------------------------
DROP TABLE IF EXISTS `shiro_resource`;
CREATE TABLE `shiro_resource`  (
  `rscid` int(11) NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rscid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of shiro_resource
-- ----------------------------
BEGIN;
INSERT INTO `shiro_resource` VALUES (1, 'login'), (2, 'logout'), (3, 'getAllPaperDetail'), (4, 'itemradio'), (5, 'itemchoose'), (6, 'getRestBranch'), (7, 'itemDeleteByIids'), (8, 'addKpByPath'), (9, 'editItemFully'), (10, 'addItemFully'), (11, 'createEmptyPaper'), (12, 'buildPaper');
COMMIT;

-- ----------------------------
-- Table structure for shiro_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role`;
CREATE TABLE `shiro_role`  (
  `rid` int(11) NOT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of shiro_role
-- ----------------------------
BEGIN;
INSERT INTO `shiro_role` VALUES (1, '管理员'), (2, '录入员'), (3, '教职工');
COMMIT;

-- ----------------------------
-- Table structure for shiro_role_rsc
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role_rsc`;
CREATE TABLE `shiro_role_rsc`  (
  `rid` int(11) NOT NULL,
  `rscid` int(11) NOT NULL,
  PRIMARY KEY (`rid`, `rscid`) USING BTREE,
  INDEX `fk_rsc_rolersc`(`rscid`) USING BTREE,
  CONSTRAINT `fk_role_rolersc` FOREIGN KEY (`rid`) REFERENCES `shiro_role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_rsc_rolersc` FOREIGN KEY (`rscid`) REFERENCES `shiro_resource` (`rscid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of shiro_role_rsc
-- ----------------------------
BEGIN;
INSERT INTO `shiro_role_rsc` VALUES (1, 1), (2, 1), (3, 1), (1, 2), (2, 2), (3, 2), (1, 3), (2, 3), (3, 3), (1, 4), (2, 4), (3, 4), (1, 5), (2, 5), (3, 5), (1, 6), (2, 6), (3, 6), (1, 7), (1, 8), (2, 8), (1, 9), (2, 9), (1, 10), (2, 10), (1, 11), (2, 11), (1, 12), (2, 12);
COMMIT;

-- ----------------------------
-- Table structure for shiro_user
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user`;
CREATE TABLE `shiro_user`  (
  `uid` int(11) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '加密盐值',
  `shar256` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of shiro_user
-- ----------------------------
BEGIN;
INSERT INTO `shiro_user` VALUES (1, '白老板', '123456', 'wxKYXuTPST5SG0jMQzVPsg==', 'J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho='), (2, '白录入', '123456', 'wxKYXuTPST5SG0jMQzVPsg==', 'J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho='), (3, '白老师', '123456', 'wxKYXuTPST5SG0jMQzVPsg==', 'J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho='), (4, '校网', '123', NULL, NULL), (5, '小狗', '123456', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for shiro_user_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user_role`;
CREATE TABLE `shiro_user_role`  (
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`uid`, `rid`) USING BTREE,
  INDEX `fk_role_userrole`(`rid`) USING BTREE,
  CONSTRAINT `fk_role_userrole` FOREIGN KEY (`rid`) REFERENCES `shiro_role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_userrole` FOREIGN KEY (`uid`) REFERENCES `shiro_user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of shiro_user_role
-- ----------------------------
BEGIN;
INSERT INTO `shiro_user_role` VALUES (1, 1), (2, 2), (3, 3), (4, 3), (5, 3);
COMMIT;

-- ----------------------------
-- Triggers structure for table buildpaper
-- ----------------------------
DROP TRIGGER IF EXISTS `trig_delete_paper_sum`;
delimiter ;;
CREATE TRIGGER `trig_delete_paper_sum` AFTER DELETE ON `buildpaper` FOR EACH ROW begin
declare temp int;
select sumscore into temp from paper where pid = old.pid;
if (temp = old.score) then
		delete from paper where paper.pid = old.pid;
else
		update paper set sumscore = sumscore - old.score where paper.pid= old.pid;
    update paper set sumtimemin = sumtimemin - old.timemin where paper.pid= old.pid;
end if;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table buildpaper
-- ----------------------------
DROP TRIGGER IF EXISTS `trig_update_paper_sum`;
delimiter ;;
CREATE TRIGGER `trig_update_paper_sum` AFTER UPDATE ON `buildpaper` FOR EACH ROW begin
if (old.pid = new.pid and old.iid <> new.iid) then
    update paper set sumscore = sumscore - old.score where paper.pid= new.pid;
    update paper set sumscore = sumscore + new.score where paper.pid= new.pid;
    update paper set sumtimemin = sumtimemin - old.timemin where paper.pid= new.pid;
    update paper set sumtimemin = sumtimemin + new.timemin where paper.pid= new.pid;
end if;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
