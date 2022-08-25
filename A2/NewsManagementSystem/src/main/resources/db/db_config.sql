/*
Source Host           : localhost:3306
Source Database       : javaee

Date: 2022-04-20
Author: Mengning Wu
*/

SET FOREIGN_KEY_CHECKS=0;

-- -- ----------------------------
-- -- Table structure for category
-- -- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name`(category_name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'Others');
INSERT INTO `category` VALUES ('2', 'Talk');
INSERT INTO `category` VALUES ('3', 'Movie');
INSERT INTO `category` VALUES ('4', 'Game');
INSERT INTO `category` VALUES ('5', 'Sports');
INSERT INTO `category` VALUES ('6', 'Environment');
INSERT INTO `category` VALUES ('7', 'Politics');


-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `provider_id` int(11) NOT NULL,
  `reviewer_id` int(11) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext,
  status varchar(10) NOT NULL,
  `post_time` datetime DEFAULT NULL,
  `art_like_count` int(5) DEFAULT '0',
  `category_id` int(5) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('41', '2','6', '  Incredible New Views of the Sun – As You’ve Never Seen It Before', 'Powerful eruptions, breathtaking views across the solar poles, and a strange solar ‘hedgehog’ are among the incredible haul of spectacular images, movies, and data returned by Solar Orbiter from its first close approach to the Sun. Although the analysis of the new dataset has only just begun, it is already clear that the ESA-led mission is providing the most extraordinary insights into the Sun’s magnetic behavior and the way this shapes space weather.\n  Solar Orbiter’s closest approach to the Sun, known as perihelion, took place on March 26. The spacecraft was inside the orbit of Mercury, at about one-third the distance from the Sun to the Earth, and its heatshield was reaching around 500°C. But it dissipated that heat with its innovative technology to keep the spacecraft safe and functioning.','Published', '2019-06-11 15:38:28', '0', 2);
INSERT INTO `news` VALUES ('42', '2','6', '测试2', 'TestEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEngEng','Reviewing', null, '0', 2);
INSERT INTO `news` VALUES ('43', '2','6', '测试3', 'TestEngWithCh正文正文正文正文正文正文正文EngEngEngEngEng正文正文正文正文正文正文正文正文EngEngEng正文正文正文正文正文EngEngEngEngEngEng','Modifying', null, '0', 1);
INSERT INTO `news` VALUES ('49', '2','6', '测试7', '喂喂喂','Published', '2019-06-11 15:52:37', '0', 3);
INSERT INTO `news` VALUES ('51', '2','6', '测试8', ' for spatial perception that are based on simultaneous stimulation of two separated sense organs—for example, auditory localization, which depends on differential stimulation at the two ears. Similarly, the use of the two eyes permits stereovision.

Several kinds of evidence support the hypothesis that forked tongues evolved as chemosensory edge detectors to enhance the ability to follow odor trails: (1) Snakes and lizards spread the tines of their tongue apart when they retrieve odor molecules, then draw the tines together when retracting the tongue. The greater the distance between sampling points, the better the animals sample differences within an odor trail. (2) Lizards that forage widely have forked tongues, whereas lizard species without forked tongues tend not to forage widely. (3) Forked tongues have evolved independently at least twice in different families of reptiles, indicating their value as an adaptation. (4) In the snake nervous system, each tine of the tongue is linked to a nucleus in the other side of the brain, and the two nuclei are linked across the two hemispheres. This arrangement is similar to the anatomy of auditory centers in mammals and birds that permits the computation of differences between what one ear hears and what the other ear hears and thus mediates auditory localization.

Species in other orders have also evolved paired chemical receptors to guide individuals to mates or prey. For example, male gypsy moths have large, elaborate, odor-detecting antennae with which they track potential mates over large distances, and the ant nest beetle has spoon-shaped antennae extending from each side of the head with which it detects and follows the pheromones of the ants that are its food.','Canceled', null, '0', 5);
INSERT INTO `news` VALUES ('1', '2','6', 'Test9', 'In 1920 Browman suggested what seemed to be a winning hypothesis: When the snake retracts its tongue, the tips (or tines) of the forked tongue are inserted into openings on both sides of the roof of the mouth; through these openings chemical stimuli reach special organs that help snakes detect smells—the vomeronasal organs (VNO). These organs are highly developed in snakes, lizards, and many mammals. They are a second system for detecting smells that appears to have evolved specifically to detect pheromones, the chemical signals that animals secrete as messages to other animals of their species. Browman suggested that the forked tongue flicks out, picking up chemical signals, and then delivers these to the VNO. This hypothesis was widely accepted into the 1980s. Then X-ray movie studies of tongue flicks in snakes and lizards with forked tongues disproved the hypothesis; they showed that when the tongue is withdrawn into the mouth, it enters a sheath and the tips do not go into the openings to the VNO. Instead, the chemical molecules are deposited on pads at the bottom of the mouth, and closing the mouth presses the pads and molecules against the VNO openings.

If the tongue is not forked to fit into the VNO, then what function could the forked shape serve? Schwenk proposes a solution that encompasses observations from several fields—animal behavior, ecology, sensory physiology, and neuroanatomy. He hypothesizes that the forked tongue allows the snake to sense chemical stimuli at two points simultaneously, thereby giving it the ability to detect differences in an odor trail.Obtaining two simultaneous readings enhances the ability of the snake to detect the edges of odor trails, and thus to follow pheromone trails accurately. This ability is important in seeking both prey and mates.

This spatial chemical perception is like other systems','Canceled', null, '0', 2);
-- ----------------------------
-- Table structure for news_review
-- ----------------------------
DROP TABLE IF EXISTS `news_review`;
CREATE TABLE `news_review` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `news_id` int(11) NOT NULL,
   `reviewer_id` int(11),
   `committer_description` longtext,
   `reviewer_description` longtext,
   `status` varchar(4) NOT NULL,
   `commit_time` datetime NOT NULL,
   `review_time` datetime DEFAULT NULL,
   PRIMARY KEY (`id`),
    UNIQUE KEY `news_id` (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userOld
-- ----------------------------
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  account varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(20) NOT NULL,
  permission varchar(30) NOT NULL,
  `token` varchar(100) DEFAULT NULL,
  register_time datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `banned` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (account)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userOld
-- ----------------------------
INSERT INTO user VALUES ('1', '1@qq.com', '1', '狗蛋','ROLE_MEMBER',  null, '2019-01-16 19:21:30', '2019-07-20 21:08:58', '2');
INSERT INTO user VALUES ('2', '2@qq.com', '2', '尼古拉斯-赵四','ROLE_MEMBER', null, '2019-01-16 19:21:49', '2019-07-20 21:08:39', '1');
INSERT INTO user VALUES ('3', '3@qq.com', '3', '大牛二虎','ROLE_MEMBER', null, '2019-01-18 14:50:32', '2019-06-18 11:56:26',  '1');
INSERT INTO user VALUES ('4', 'admin', '123', 'Admin','ROLE_ADMIN',  null, '2019-06-19 18:00:16', '2019-07-05 16:49:11', '1');
INSERT INTO user VALUES ('5', 'aa', '1', '战三','ROLE_EDITOR', null, '2019-06-27 16:44:52', null,  '1');
INSERT INTO user VALUES ('6', '1111', '1', 'good','ROLE_REVIEWER', null, '2019-07-05 16:49:33', null, '1');
INSERT INTO user VALUES ('8','ad@min','$2a$10$25/thUUVGHzAYw56LbbEaeBSkZU0yB3MJmBcE.rfRXW0ILlrCaCa6','admin','ROLE_ADMIN',null,'2022-06-20 11:28:26',null,'1');

-- ----------------------------
-- Table structure for news_comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `news_id` int(11) NOT NULL,
   `user_id` int(11) NOT NULL,
   `content` longtext NOT NULL,
   commit_time datetime NOT NULL,
   PRIMARY KEY (id,`news_id`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;






