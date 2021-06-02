DROP
DATABASE IF EXISTS sampledb;
CREATE
DATABASE sampledb DEFAULT CHARACTER SET utf8;
USE
sampledb;

-- 创建用户表
create table t_user
(
    user_id    int auto_increment
        primary key,
    user_name  varchar(30) null,
    credits    int null,
    password   varchar(32) null,
    last_visit datetime null,
    last_ip    varchar(23) null
);

-- 创建用户登录日志表
create table t_login_log
(
    login_log_id   int auto_increment
        primary key,
    user_id        int null,
    ip             varchar(23) null,
    login_datetime datetime null
);

-- 插入初始化数据
insert into t_user (user_name, password)
values ('admin', '123456');

commit;