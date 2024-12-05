CREATE DATABASE  `mm_common` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

use `mm_common`;


-- ----------------------------
-- Table structure for mm_cms_menu
-- 如果涉及多级栏目、导航 
-- ----------------------------
DROP TABLE IF EXISTS `mm_cms_menu`;
CREATE TABLE `mm_cms_menu`  (
  id                   bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  tenant_id            varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'S00000000' COMMENT '租户ID',
  menu_title           VARCHAR(255) NOT NULL COMMENT '菜单名称',
  parent_id            bigint UNSIGNED DEFAULT 0 COMMENT '上级菜单ID',
  type varchar(64)     DEFAULT NULL COMMENT '菜单类型',
  sort_order           int NOT NULL COMMENT '排序',
  state                VARCHAR(80) DEFAULT NULL COMMENT '状态',
  remark               text COMMENT '备注',
  c_time               timestamp not null default CURRENT_TIMESTAMP COMMENT '创建时间',
  m_time               timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'CMS菜单配置' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for mm_cms_articles
-- 文章表 与栏目导航挂钩
-- ----------------------------
DROP TABLE IF EXISTS `mm_cms_articles`;
CREATE TABLE `mm_cms_articles`  (
  id                   bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  tenant_id            varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'S00000000' COMMENT '租户ID',
  title                varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  sort_order           int NOT NULL DEFAULT 0 COMMENT '排序',
  state                VARCHAR(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  type                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型',
  attr_data            json NULL COMMENT '属性信息',
  remark               text COMMENT '备注',
  c_time               timestamp not null default CURRENT_TIMESTAMP COMMENT '创建时间',
  m_time               timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'CMS文章' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for mm_cms_articles_content
-- 文章内容表 与文章挂钩
-- ----------------------------
DROP TABLE IF EXISTS `mm_cms_articles_content`;
CREATE TABLE `mm_cms_articles_content`  (
  id                   bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  tenant_id            varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'S00000000' COMMENT '租户ID',
  articles_id          bigint UNSIGNED DEFAULT 0 COMMENT '文章ID',
  content              longtext DEFAULT NULL COMMENT '内容',
  remark               text COMMENT '备注',
  c_time               timestamp not null default CURRENT_TIMESTAMP COMMENT '创建时间',
  m_time               timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'CMS文章内容' ROW_FORMAT = Dynamic;




