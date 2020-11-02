/*
	author: Chirius
	description: none
	returns: nothing
*/

DROP TABLE IF EXISTS system_dict;


create table system_dict
(
    id          int auto_increment comment '自增主键，唯一标识'
        primary key,
    dict_name   varchar(64)                                not null comment '字典名称',
    dict_key    varchar(255)                               not null comment '字典KEY',
    dict_value  varchar(2000)                              not null comment '字典VALUE',
    dict_type   int          default 0                     not null comment '字典类型 0系统配置 1用户配置',
    dict_desc   varchar(255) default ''                    not null comment '字典描述',
    status      int(4)       default 1                     not null comment '字典状态：0-停用 1-正常',
    delete_flag tinyint(1)   default 0                     not null comment '是否删除：0-未删除 1-已删除',
    operator    int                                        not null comment '操作人ID，关联用户域用户表ID',
    create_time datetime     default CURRENT_TIMESTAMP     not null comment '创建时间',
    update_time datetime     default CURRENT_TIMESTAMP     not null comment '修改时间',
    delete_time datetime     default '1970-01-01 00:00:00' not null comment '删除时间'
)
	ENGINE=InnoDB
    comment '配置字典表' charset = utf8;

