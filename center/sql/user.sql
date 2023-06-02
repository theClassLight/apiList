/*
SQLyog Enterprise v13.1.1 (64 bit)
MySQL - 5.7.19 : Database - usercenter
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;


USE `usercenter`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

-- 用户
create table if not exists `user`
(
    `id` bigint(20) not null auto_increment comment 'id' primary key,
    `userAccount` varchar(256) default 'null' not null comment '账号',
    `userPassword` varchar(512) default 'null' not null comment '密码',
    `unionId` varchar(256) default 'NULL' not null comment '微信开放平台id',
    `mpOpenId` varchar(256) default 'NULL' not null comment '公众号openId',
    `userName` varchar(256) default 'NULL' not null comment '用户昵称',
    `userAvatar` varchar(1024) default 'NULL' not null comment '用户头像',
    `gender` tinyint(4) default '1' not null comment '性别 :0->女; 1->男',
    `userProfile` varchar(512) default 'NULL' not null comment '用户简介',
    `tags` varchar(1024) default 'NULL' not null comment '标签 json 列表',
    `userStatus` int(11) default 0 not null comment '状态 0 - 正常',
    `accessKey` varchar(512) default 'NULL' not null comment 'accessKey',
    `secretKey` varchar(512) default 'NULL' not null comment 'secretKey',
    `userRole` varchar(256) default 'user' not null comment '用户角色：user/admin/ban',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint(4) default 0 not null comment '是否删除:0存活;1删除'
) comment '用户' ENGINE INNODB  DEFAULT CHARSET=utf8 COLLATE utf8_general_ci;

