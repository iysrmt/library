DROP TABLE IF EXISTS user;

CREATE TABLE tbl_user
(
    id          char(32) primary key comment 'uuid',
    account     varchar(20) not null unique key comment '用户名',
    password    char(32)    not null comment 'MD5加密密码',
    permissions char(1) default '3' comment '1-超级管理员(可操作字段), 2-管理员(可操作用户, 图书), 3-用户(可借还图书), 4-禁用用户(预注册, 冻结, 违规...)',
    name        varchar(20) comment '姓名',
    phone       char(11) comment '手机号',
    create_time char(19) comment '创建时间',
    create_by   char(32) comment '创建人id',
    edit_time   char(19) comment '最后一次修改时间',
    edit_by     char(32) comment '最后一次修改人'
) COMMENT ='用户表';

insert into tbl_user
values ('f1083902518245d0bbb8392c1b7459a1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '1', 'admin', null, null, null,
        null, null),
       ('86244c68142a4cb99caf3cc30a1a0c98', 'iys', '6ba746cea48cc6ccc4927a974296e45f', '2', 'iys', '11111111111',
        '2023-02-23 22:55:05', 'f1083902518245d0bbb8392c1b7459a1', null, null),
       ('dc9b5ded89774e8f8063868d76eec726', 'user', 'ee11cbb19052e40b07aac0ca060c23ee', '3', 'user', '11111111111',
        '2023-02-23 22:55:05', 'f1083902518245d0bbb8392c1b7459a1', null, null);