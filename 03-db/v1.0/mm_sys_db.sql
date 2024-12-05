
/**
    通用SaaS的系统表设计创建
        包括：用户、角色、权限、租户、字典、客户端、数据源（代码生成）、部门
*/

-- CREATE DATABASE  `mm_common` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

use `mm_common`;

-- ----------------------------
-- Table structure for mm_sys_user
-- 系统用户表
-- ----------------------------
DROP TABLE IF EXISTS `mm_sys_user`;
CREATE TABLE `mm_sys_user` (
  id                   bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  tenant_id            varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'S00000000' COMMENT '租户ID',
  state                varchar(24) DEFAULT NULL COMMENT '用户状态',
  type                 varchar(24) DEFAULT NULL COMMENT '用户类型',
  user_name            varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户登录名称',
  name                 varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名称',
  user_password        varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户密码',
  salt                 varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '盐',
  email                varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱地址',
  phone                varchar(24) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  nick_name            varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  avatar               text COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  remarks              varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  c_time               timestamp not null default CURRENT_TIMESTAMP COMMENT '创建时间',
  m_time               timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '用户表';


-- ----------------------------
-- Table structure for mm_sys_permission
-- 系统用户权限表
-- ----------------------------
DROP TABLE IF EXISTS `mm_sys_permission`;
CREATE TABLE mm_sys_permission (
  id                   bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  tenant_id            varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'S00000000' COMMENT '租户ID',
  name                 varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限名称',
  type                 varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限类型',
  od                   int(11) DEFAULT NULL COMMENT '排序',
  parent_id            bigint DEFAULT NULL COMMENT '上级权限ID 顶级为空',
  url                  varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求地址URL',
  status               int(11) DEFAULT NULL COMMENT '权限状态',
  icon                 varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限icon图片',
  parent_ids           varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上级权限id地址串',
  precode              varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限代码',
  c_time               timestamp not null default CURRENT_TIMESTAMP COMMENT '创建时间',
  m_time               timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '权限表';

-- ----------------------------
-- Table structure for mm_sys_role
-- 系统用户角色表
-- ----------------------------
DROP TABLE IF EXISTS `mm_sys_role`;
CREATE TABLE mm_sys_role (
  id                   bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  tenant_id            varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'S00000000' COMMENT '租户ID',
  name                 varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
  remarks              varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色描述',
  status               int(11) DEFAULT NULL COMMENT '状态：0关闭角色 1开启角色',
  c_time               timestamp not null default CURRENT_TIMESTAMP COMMENT '创建时间',
  m_time               timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '角色表';

-- ----------------------------
-- Table structure for mm_sys_role_permission_relation
-- 系统角色权限关联表
-- ----------------------------
DROP TABLE IF EXISTS `mm_sys_role_permission_relation`;
CREATE TABLE mm_sys_role_permission_relation (
  role_id              bigint DEFAULT NULL,
  permission_id        bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '角色权限关联表';

-- ----------------------------
-- Table structure for mm_sys_user_role_relation
-- 角色用户关联表
-- ----------------------------
DROP TABLE IF EXISTS `mm_sys_user_role_relation`;
CREATE TABLE mm_sys_user_role_relation (
  user_id              bigint DEFAULT NULL,
  role_id              bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '角色用户关联表';


-- ----------------------------
-- Table structure for mm_sys_user
-- 系统租户表
--  后续：租户可以通过购买资源包方式，进行有效期续命，并且可以通过配置购买不同的产品，获取不同的角色和权限。
--    有效期 : 有效期 截止日期
-- ----------------------------
DROP TABLE IF EXISTS `mm_sys_tenant`;
CREATE TABLE `mm_sys_tenant` (
  id                   bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  tenant_name          varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名称',
  tenant_state         varchar(24) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户状态',
  tenant_type          varchar(24) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '租户类型',
  contact_email        varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系人邮箱',
  contact_phone        varchar(24) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系人电话',
  contact_person       varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系人姓名',
  domain_bind          varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '绑定域名',
  lifespan             timestamp COMMENT '有效期',
  remarks              varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  c_time               timestamp not null default CURRENT_TIMESTAMP COMMENT '创建时间',
  m_time               timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '租户表';



-- ----------------------------
-- Table structure for mm_sys_client
-- 系统客户端表 : 
--    客户端id : sword
--    客户端密钥 : sword_secret
--    授权类型 : refresh_token password authorization_code captcha social 可多选
--    授权范围 : all 全部
--    令牌过期秒数 : 3600
--    刷新令牌过期秒数 : 604800
--    回调地址 : http://localhost:8080 
--    
-- ----------------------------
DROP TABLE IF EXISTS `mm_sys_client`;
CREATE TABLE `mm_sys_client`  (
  id                        bigint NOT NULL COMMENT '主键',
  client_id                 varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户端id',
  client_secret             varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户端密钥',
  resource_ids              varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源集合',
  scope                     varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '授权范围',
  authorized_grant_types    varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '授权类型',
  web_server_redirect_uri   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  authorities               varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限',
  access_token_validity     int(11) NOT NULL COMMENT '令牌过期秒数',
  refresh_token_validity    int(11) NOT NULL COMMENT '刷新令牌过期秒数',
  additional_information    varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附加说明',
  autoapprove               varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自动授权',
  create_dept               bigint NULL DEFAULT NULL COMMENT '创建部门',
  c_time                    timestamp not null default CURRENT_TIMESTAMP COMMENT '创建时间',
  m_time                    timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户端表';


-- ----------------------------
-- Table structure for mm_sys_basic_dict
-- 系统字典 : 
-- ----------------------------
DROP TABLE IF EXISTS `mm_sys_basic_dict`;
CREATE TABLE `mm_sys_basic_dict`  (
  id                        bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  parent_id                 int(11) NULL DEFAULT NULL COMMENT '父主键',
  state                     int(2) DEFAULT 1 COMMENT '状态：1启用，0禁止',
  code                      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典码',
  dict_key                  varchar(255) NULL DEFAULT NULL COMMENT '字典值',
  dict_value                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  sort                      int(11) NULL DEFAULT NULL COMMENT '排序',
  remark                    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典备注',
  c_time                    timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  m_time                    timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表';

-- ----------------------------
-- Table structure for mm_sys_datasource
-- 数据源配置 : 
--      仅支持：Mysql 后续可追加其它数据库
-- ----------------------------
DROP TABLE IF EXISTS `mm_sys_datasource`;
CREATE TABLE `mm_sys_datasource`  (
  id                        bigint NOT NULL COMMENT '主键',
  state                     int(2) DEFAULT 1 COMMENT '状态：1启用，0禁止',
  name                      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  driver_class              varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '驱动类',
  url                       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '连接地址',
  username                  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  password                  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  remark                    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  c_time                    timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  m_time                    timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据源配置表';


-- ----------------------------
-- Table structure for mm_sys_dept
-- 部门 : 
-- ----------------------------
DROP TABLE IF EXISTS `mm_sys_dept`;
CREATE TABLE mm_sys_dept (
  id                     bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  tenant_id              varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '000000' COMMENT '租户ID',
  dept_no                varchar(50) NOT NULL COMMENT '组织机构编号',
  parent_id              bigint DEFAULT NULL COMMENT '上级组织机构ID',
  parent_ancestors       varchar(255) DEFAULT NULL COMMENT '公司ID序列',
  state                  int DEFAULT NULL COMMENT '组织机构状态',
  dept_name              varchar(200) DEFAULT NULL COMMENT '组织机构名称',
  full_name              varchar(200) DEFAULT NULL COMMENT '组织机构全称',
  dept_type              varchar(12) DEFAULT NULL COMMENT '组织机构类型',
  remark                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  c_time                 timestamp not null default CURRENT_TIMESTAMP COMMENT '创建时间',
  m_time                 timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (id)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表';

