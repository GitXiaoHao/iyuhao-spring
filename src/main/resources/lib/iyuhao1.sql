use `iyuhao1`;
create table user
(
    `user_id`          varchar(50) not null comment '用户id',
    `user_name`        varchar(35) comment '用户名',
    `user_password`    varchar(50) comment '用户密码',
    `user_status`      tinyint(3)           DEFAULT '0' COMMENT '状态',
    `user_create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `user_update_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `user_is_deleted`  tinyint(3)  NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
    primary key (`user_id`),
    unique (`user_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='用户信息表';
alter table `user`
    add column `user_email` varchar(50) default null comment '用户邮箱' after `user_password`;
alter table `user`
    add column `user_cover` varchar(50) comment '用户头像' after `user_password`;
alter table `user`
    add column `user_fans` int default 0 comment '用户粉丝数量' after `user_cover`;
alter table `user`
    add column `user_phone` varchar(20) comment '用户手机号码' after `user_status`;
alter table `user`
    add column `user_autograph` text comment '用户签名' after `user_phone`;
alter table `user`
    add column `user_role` varchar(10) comment '用户角色' after `user_status`;
alter table `user`
    add column `user_last_login_time` timestamp comment '最后登录时间' after `user_update_time`;
alter table `user`
    modify `user_is_deleted` tinyint(3) NOT NULL DEFAULT '1' COMMENT '删除标记（0:不可用 1:可用）';
create table article
(
    `article_id`              varchar(50) not null comment '文章id',
    `user_id`                 varchar(50) not null comment '用户id',
    `article_title`           varchar(500) comment '文章标题',
    `article_context`         text comment '文章内容',
    `article_add_time`        timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文章添加时间',
    `article_update_time`     timestamp   not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `article_good_number`     int comment '点赞次数',
    `article_views_number`    int comment '观看次数',
    `article_favorite_number` int comment '收藏次数',
    primary key (`article_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='文章表';


drop table if exists `article_tag`;
create table article_tag
(
    `article_tag_id`                    varchar(50) not null comment '文章标签id',
    `article_tag_name`                  varchar(35) not null comment '标签名称',
    `article_tag_create_user_id`        varchar(50) comment '创建人id',
    `article_tag_create_user_name`      varchar(35) comment '创建人名称',
    `article_tag_last_update_user_id`   varchar(50) comment '最后一次修改人id',
    `article_tag_last_update_user_name` varchar(35) comment '最后一次修改人名称',
    `article_tag_parent_id`             varchar(50) comment '标签上一级的id',
    `article_tag_add_time`              timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文章标签添加时间',
    `article_tag_update_time`           timestamp   not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '文章标签更新时间',
    `article_tag_is_deleted`            tinyint(3)  NOT NULL DEFAULT '1' COMMENT '删除标记（0:不可用 1:可用）',
    primary key (`article_tag_id`),
    unique (`article_tag_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='文章标签表';
alter table `article_tag` modify column `article_tag_parent_name`  varchar(35) comment '标签父级名称' after `article_tag_parent_id`;
alter table `article_tag` add column `article_tag_disable`  varchar(35) comment '标签父级名称' after `article_tag_parent_name`;
alter table `article_tag` modify column `article_tag_disable` int default 0 comment '是否被禁用 1:禁用 0:不禁用' after `article_tag_parent_name`;



create table article_tag_list
(
    `article_tag_list_id` varchar(50) not null comment '文章对应标签id',
    `article_id`          varchar(50) not null comment '文章id',
    `article_tag_id`      varchar(50) not null comment '文章标签id',
    primary key (`article_tag_list_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='文章与文章标签关系表';

create table comment
(
    `comment_id`          varchar(50) not null comment '文章评论id',
    `article_id`          varchar(50) not null comment '文章id',
    `user_id`             varchar(50) not null comment '文章评论用户id',
    `comment_time`        timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文章评论时间',
    `comment_good_number` int                  default 0 comment '点赞次数',
    `comment_is_deleted`  tinyint(3)  NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
    primary key (`comment_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='文章评论表';
alter table `comment`
    modify `comment_is_deleted` tinyint(3) NOT NULL DEFAULT '1' COMMENT '删除标记（0:不可用 1:可用）';
create table comment_reply
(
    `comment_reply_id`          varchar(50) not null comment '文章评论回复id',
    `comment_id`                varchar(50) not null comment '文章评论id',
    `reply_user_id`             varchar(50) comment '回复人的id',
    `secondly_user_id`          varchar(50) comment '回复人的下一个人的id',
    `comment_reply_create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文章回复时间',
    `comment_reply_is_deleted`  tinyint(3)  NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
    primary key (`comment_reply_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='文章评论回复表';
alter table `comment_reply`
    modify `comment_reply_is_deleted` tinyint(3) NOT NULL DEFAULT '1' COMMENT '删除标记（0:不可用 1:可用）';
create table link
(
    `link_id`          varchar(50) not null comment '链接id',
    `link_name`        varchar(150) comment '链接标题',
    `link_url`         varchar(500) comment '链接地址',
    `link_logo_url`    varchar(500) comment '链接logo',
    `link_create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    primary key (`link_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='链接表';

create table ad
(
    `ad_id`          varchar(50) not null comment '广告id',
    `ad_type_id`     varchar(50) not null comment '广告类型id',
    `ad_title`       varchar(255) comment '广告标题',
    `ad_url`         varchar(500) comment '广告url',
    `ad_sort`        int comment '广告排序',
    `ad_begin_time`  datetime comment '广告开始时间',
    `ad_end_time`    datetime comment '广告结束时间',
    `ad_create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    `ad_update_time` timestamp   not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `ad_is_deleted`  tinyint(3)  NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
    primary key (`ad_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='广告表';
alter table `ad`
    modify `ad_is_deleted` tinyint(3) NOT NULL DEFAULT '1' COMMENT '删除标记（0:不可用 1:可用）';

create table ad_type
(
    `ad_type_id`          varchar(50) not null comment '广告类型id',
    `ad_type_name`        varchar(50) not null comment '广告类型名称',
    `ad_type_tag`         varchar(50) comment '广告标示',
    `ad_type_sort`        int         not null default 1 comment '广告类型排序',
    `ad_type_create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `ad_type_update_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `ad_type_is_deleted`  tinyint(3)  NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
    primary key (`ad_type_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='广告类型表';
alter table `ad_type`
    modify `ad_type_is_deleted` tinyint(3) NOT NULL DEFAULT '1' COMMENT '删除标记（0:不可用 1:可用）';


create table blog_category
(
    `blog_category_id`          varchar(50) not null comment '分类id',
    `blog_category_cover`       varchar(50) comment '分类图片',
    `blog_category_name`        varchar(20) comment '分类名称',
    `blog_category_type`        varchar(20) comment '分类类型',
    `user_id`                   varchar(50) comment '创建分类用户id',
    `blog_category_essay_count` int                  default 0 comment '分类文章数量',
    `blog_category_create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `blog_category_is_deleted`  tinyint(3)  NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
    primary key (`blog_category_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='博客分类表';

alter table `blog_category`
    add column `blog_category_desc` varchar(500) comment '分类简介' after `blog_category_type`;
alter table `blog_category`
    add column `blog_category_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' after `blog_category_create_time`;
alter table `blog_category`
    modify column `blog_category_name` varchar(20) unique comment '分类名称';
alter table `blog_category`
    modify `blog_category_is_deleted` tinyint(3) NOT NULL DEFAULT '1' COMMENT '删除标记（0:不可用 1:可用）';



create table `blog_article`
(
    `blog_article_id`               varchar(50)  not null comment '博客文章id',
    `blog_article_title`            varchar(100) not null comment '博客文章标题',
    `blog_category_name`            varchar(50) comment '博客文章分类id',
    `blog_article_cover`            varchar(50) comment '博客文章封面',
    `blog_article_summary`          varchar(500) comment '博客文章摘要',
    `blog_article_content`          text comment '博客文章内容',
    `blog_article_markdown_content` text comment '博客文章markdown内容',
    `blog_article_editor_type`      varchar(50) comment '博客文章编辑器类型',
    `user_id`                       varchar(50) comment '博客文章编辑人id',
    `user_name`                     varchar(50) comment '博客文章作者',
    `blog_article_allow_comment`    int                   default 1 comment '文章评论权限1:允许0:不允许',
    `blog_status_name`              varchar(10) comment '博客文章状态',
    `blog_article_good_number`      int comment '点赞次数',
    `blog_article_views_number`     int comment '观看次数',
    `blog_article_favorite_number`  int comment '收藏次数',
    `blog_article_add_time`         timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文章添加时间',
    `blog_article_update_time`      timestamp    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `blog_category_is_deleted`      tinyint(3)   NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
    primary key (`blog_article_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='博客文章表';
alter table `blog_article`
    add column `blog_article_type` varchar(10) comment '文章类型' after `user_name`;
alter table `blog_article`
    add column `blog_article_reprint_url` varchar(50) comment '文章转载' after `blog_article_type`;
alter table `blog_article`
    modify `blog_category_is_deleted` tinyint(3) NOT NULL DEFAULT '1' COMMENT '删除标记（0:不可用 1:可用）';

create table `blog_status`
(
    `blog_status_id`          varchar(50) not null comment '博客状态id',
    `blog_status_name`        varchar(10) not null unique comment '博客状态名称',
    `blog_status_color`       varchar(10) not null comment '博客状态颜色',
    `blog_status_add_time`    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文章添加时间',
    `blog_status_update_time` timestamp   not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `blog_status_is_deleted`  tinyint(3)  NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
    primary key (`blog_status_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='博客状态表';
alter table `blog_status`
    add column `blog_status_disable` int default 0 comment '是否被禁用 1:禁用 0:不禁用';

alter table `blog_status`
    modify `blog_status_is_deleted` tinyint(3) NOT NULL DEFAULT '1' COMMENT '删除标记（0:不可用 1:可用）';
alter table `blog_status`
    modify `blog_status_color` varchar(50) not null comment '博客状态颜色';
drop table if exists `blog_special`;
create table `blog_special`
(
    `blog_special_id`                  varchar(50)  not null comment '博客专题id',
    `blog_special_name`                varchar(10)  not null comment '博客专题名称',
    `blog_special_cover`               varchar(50)  not null comment '博客专题封面',
    `blog_special_brief_introduction`  varchar(500) not null comment '博客专题简介',
    `user_id`                          varchar(50) comment '博客专题编辑人id',
    `user_name`                        varchar(50) comment '博客专题作者',
    `blog_special_last_update_user_id` varchar(50) comment '博客专题最后一次修改人id',
    `blog_special_good_number`         int                   default 0 comment '点赞次数',
    `blog_special_views_number`        int                   default 0 comment '观看次数',
    `blog_special_favorite_number`     int                   default 0 comment '收藏次数',
    `blog_special_essay_count`         int                   default 0 comment '博客专题文章数量',
    `blog_special_add_time`            timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    `blog_special_update_time`         timestamp    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `blog_special_is_deleted`          tinyint(3)   NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
    primary key (`blog_special_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='博客专题表';
alter table `blog_special`
    modify `blog_special_is_deleted` tinyint(3) NOT NULL DEFAULT '1' COMMENT '删除标记（0:不可用 1:可用）';


create table `blog_special_article_relationship`
(
    `relationship_id` varchar(50) not null comment '主键id',
    ``
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='博客专题与文章关系表';



# create table `blog_admin`
# (
#     `blog_admin_id`          varchar(50) not null comment '管理员id',
#     `blog_admin_name`        varchar(35) comment '管理员昵称',
#     `blog_admin_password`    varchar(50) comment '博客管理员密码',
#     `blog_admin_phone` varchar(11) comment '博客管理员手机号码',
#     `blog_admin_email` varchar(20) comment '博客管理员邮箱',
#     `blog_admin_status`      tinyint(3)           DEFAULT '0' COMMENT '状态',
#     `blog_admin_create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
#     `blog_admin_update_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
#     `blog_admin_is_deleted`  tinyint(3)  NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
#     primary key (`blog_admin_id`),
#     unique (`blog_admin_name`)
# )ENGINE = InnoDB
#  DEFAULT CHARSET = utf8
#  COLLATE = utf8_bin COMMENT ='博客管理员表';

drop table if exists `blog_category_type`;
create table `blog_category_type`
(
    `blog_category_type_id`               varchar(50) not null comment '博客状态id',
    `blog_category_type_name`             varchar(10) not null unique comment '博客状态名称',
    `blog_category_type_disable`          int         not null default 0 comment '是否被禁用 1:禁用 0:不禁用',
    `blog_category_create_user_id`        varchar(50) comment '创建人id',
    `blog_category_create_user_name`      varchar(35) comment '创建人名称',
    `blog_category_last_update_user_id`   varchar(50) comment '最后一次修改人id',
    `blog_category_last_update_user_name` varchar(35) comment '最后一次修改人名称',
    `blog_category_type_add_time`         timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文章添加时间',
    `blog_category_type_update_time`      timestamp   not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `blog_category_type_is_deleted`       tinyint(3)  NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
    primary key (`blog_category_type_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin COMMENT ='博客分类类型表';