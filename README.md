<h1 align="center"><a href="https://github.com/lovinstudio" target="_blank">lovinstudio</a></h1>

> This is a quickly starter project for Spring Boot。

<p align="center">
<a href="#"><img alt="JDK" src="https://img.shields.io/badge/JDK-1.8-yellow.svg?style=flat-square"/></a>
<a href="https://github.com/lovinstudio/lovinstarter/releases"><img alt="GitHub release" src="https://img.shields.io/github/release/lovinstudio/lovinstarter.svg?style=flat-square"/></a>
<a href="https://github.com/lovinstudio/lovinstarter/releases"><img alt="GitHub All Releases" src="https://img.shields.io/github/downloads/lovinstudio/lovinstarter/total.svg?style=flat-square"></a>
<a href="https://github.com/lovinstudio/lovinstarter/commits"><img alt="GitHub commit activity" src="https://img.shields.io/github/commit-activity/w/lovinstudio/lovinstarter.svg?style=flat-square"></a>
<a href="https://github.com/lovinstudio/lovinstarter/commits"><img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/lovinstudio/lovinstarter.svg?style=flat-square"></a>
<a href="https://travis-ci.org/lovinstudio/lovinstarter"><img alt="Travis CI" src="https://img.shields.io/travis/lovinstudio/lovinstarter.svg?style=flat-square"/></a>
</p>

------------------------------

## 简介

一个简单的常用的配置的工具类的封装，Spring Boot脚手架。

欢迎访问[首页](http://localhost:8080/lovin/)
![首页](./images/index.png)

集成Swagger2，访问地址：[http://localhost:8080/lovin/swagger-ui.html](http://localhost:8080/lovin/swagger-ui.html)
![Swagger2](./images/swagger.png)

集成druid，访问地址：[http://localhost:8080/lovin/druid/login.html](http://localhost:8080/lovin/druid/login.html)
![druid](./images/druid.png)

## 动态刷新配置

访问[http://localhost:8080/lovin/config/config](http://localhost:8080/lovin/config/config) 查看当前生效的配置，当需要更改某些配置的时候，在数据库修改配置，然后访问[http://localhost:8080/lovin/config/index](http://localhost:8080/lovin/config/index) 打开配置页面，刷新配置即可加载最新的配置。
![config](./images/config.png)

下面给出表结构

```sql
/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : training

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 22/05/2020 22:59:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_dict
-- ----------------------------
DROP TABLE IF EXISTS `system_dict`;
CREATE TABLE `system_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键，唯一标识',
  `dict_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `dict_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典KEY',
  `dict_value` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典VALUE',
  `dict_type` int(11) NOT NULL DEFAULT 0 COMMENT '字典类型 0系统配置 1用户配置',
  `dict_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '字典描述',
  `status` int(4) NOT NULL DEFAULT 1 COMMENT '字典状态：0-停用 1-正常',
  `delete_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0-未删除 1-已删除',
  `operator` int(11) NOT NULL COMMENT '操作人ID，关联用户域用户表ID',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_time` datetime(0) NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_dict
-- ----------------------------
INSERT INTO `system_dict` VALUES (1, '应用ID', 'appid', 'eelve', 0, '应用ID', 1, 0, 17, '2020-05-09 11:14:00', '2020-05-09 11:14:00', '1970-01-01 00:00:00');
INSERT INTO `system_dict` VALUES (2, '密钥', 'key', 'eelve', 0, '密钥', 1, 0, 17, '2020-05-09 11:14:39', '2020-05-09 11:14:39', '1970-01-01 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;

```

## 添加全局错误页面配置


## Flyway集成

~~~xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
~~~

> 与`druid`集成，注意`filters: stat,wall`过滤器的配置，不然会导致报错

~~~yaml
spring:
  datasource:
      url: jdbc:mysql://127.0.0.1:3306/lovin?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8
      username: ENC(FIBMFUGC/3Ni8vE2PJ9VAA==)
      password: ENC(FIBMFUGC/3Ni8vE2PJ9VAA==)
      type: com.alibaba.druid.pool.DruidDataSource
      druid:
        # 下面为连接池的补充设置，应用到上面所有数据源中
        # 初始化大小，最小，最大
        initial-size: 5
        min-idle: 5
        max-active: 20
        # 配置获取连接等待超时的时间
        max-wait: 60000
        # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        time-between-eviction-runs-millis: 60000
        # 配置一个连接在池中最小生存的时间，单位是毫秒
        min-evictable-idle-time-millis: 300000
        validation-query: SELECT 1 FROM DUAL
        test-while-idle: true
        test-on-borrow: false
        test-on-return: false
        # 打开PSCache，并且指定每个连接上PSCache的大小
        pool-prepared-statements: true
        #   配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        max-pool-prepared-statement-per-connection-size: 20
        filter:
          stat:
            enabled: true
          slf4j:
            enabled: true
          wall:
            enabled: true
            config:
              comment-allow: true
        #filters: stat,wall
        use-global-data-source-stat: true
        # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        connect-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
        # 配置监控服务器
        stat-view-servlet:
          login-username: admin
          login-password: 123456
          reset-enable: false
          url-pattern: /druid/*
          # 添加IP白名单
          #allow:
          # 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
          #deny:
        web-stat-filter:
          # 添加过滤规则
          url-pattern: /*
          # 忽略过滤格式
          exclusions: "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"
    flyway:
      enabled: true
      # 禁止清理数据库表
      clean-disabled: true
      # 如果数据库不是空表，需要设置成 true，否则启动报错
      baseline-on-migrate: true
      # 与 baseline-on-migrate: true 搭配使用
      baseline-version: 0
      locations:
        - classpath:db/migration  #（根据个人情况设置）
~~~
# License
Released under the [MIT](LICENSE) License.
