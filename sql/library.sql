-- MySQL 脚本
drop database if exists library;
create database library;
use library;


-- tbl_book
create table tbl_book
(
    id          char(32) primary key comment 'uuid',
    name        varchar(20) not null comment '图书名称',
    author      varchar(20) comment '作者',
    publisher   varchar(20) comment '出版社',
    isbn        char(13) unique key comment 'isbn',
    price       varchar(6) comment '价格',
    category    char(32) comment '类别',
    create_time char(19) comment '创建时间',
    create_by   char(32) comment '创建人id',
    edit_time   char(19) comment '最后一次修改时间',
    edit_by     char(32) comment '最后一次修改人id'
) comment ='图书信息表';
insert into tbl_book
values ('0a99de1b240045bd8139ec6470c965f3', '管理信息系统', '邱立新', '机械工业出版社', '9787111665656', '45',
        '9de0b424bbc14580b0a80c2ec15bdeff', '2023-02-27 16:16:59', 'f1083902518245d0bbb8392c1b7459a1', null, null),
       ('22d5e417309b47aaa9f6db4661da5131', '计算机组成原理', '白中英, 戴志涛', '科学出版社', '9787030619716', '68',
        'd38a74da2df24fc8863efc643eb8c0c8', '2023-02-24 14:58:40', 'f1083902518245d0bbb8392c1b7459a1',
        '2023-03-06 14:16:24', 'f1083902518245d0bbb8392c1b7459a1'),
       ('2f13224dc23c470aae0fe53168794568', '软件工程实用教程 (第二版)', '石冬凌', '大连理工大学出版社',
        '9787568528757', '45', 'd38a74da2df24fc8863efc643eb8c0c8', '2023-03-06 14:34:03',
        '86244c68142a4cb99caf3cc30a1a0c98', '2023-03-06 14:41:54', '86244c68142a4cb99caf3cc30a1a0c98'),
       ('369356655eb84186b60eb4e51c9ae8fe', '软件文档写作与管理', '陈长清', '清华大学出版社', '9787302391401', '29',
        'd38a74da2df24fc8863efc643eb8c0c8', '2023-03-06 14:39:11', '86244c68142a4cb99caf3cc30a1a0c98', null, null),
       ('617ac4e33e964ff18821f6ce4a6f49b8', '计算机网络技术基础', '阚宝朋', '高等教育出版社', '9787040518092', '49.5',
        'd38a74da2df24fc8863efc643eb8c0c8', '2023-03-06 14:40:36', '86244c68142a4cb99caf3cc30a1a0c98', null, null),
       ('69b91d51d93a477ca72da371e8e31819', 'Linux 系统与网络管理 (第三版)', '姜大庆', '中国铁道出版社',
        '9787113205201', '39.8', 'd38a74da2df24fc8863efc643eb8c0c8', '2023-03-06 14:35:04',
        '86244c68142a4cb99caf3cc30a1a0c98', null, null),
       ('6cbea7e3a8884dab8ddaa7297956b985', '流浪地球', '刘慈欣', '中国华侨出版社', '9787511360809', '38',
        '01accc83831c49d597997c89276b1470', '2023-03-06 14:22:25', 'f1083902518245d0bbb8392c1b7459a1', null, null),
       ('705838f858cf483496fc2203ca650a53', '软件测试', '郭雷', '高等教育出版社', '9787040564334', '55',
        'd38a74da2df24fc8863efc643eb8c0c8', '2023-03-06 14:35:58', '86244c68142a4cb99caf3cc30a1a0c98', null, null),
       ('77d9c7d4486944aea8d653ea6cfad08a', '数据结构 (Java 版)', '杨淑萍 聂哲', '高等教育出版社', '9787040376265',
        '26.8', 'd38a74da2df24fc8863efc643eb8c0c8', '2023-03-06 14:26:42', 'f1083902518245d0bbb8392c1b7459a1', null,
        null),
       ('808277392ea247219d9979ec89cbba3e', 'MySQL数据库技术', '周德伟 覃国蓉', '高等教育出版社', '9787040520835', '45',
        'd38a74da2df24fc8863efc643eb8c0c8', '2023-02-27 16:36:42', '86244c68142a4cb99caf3cc30a1a0c98',
        '2023-03-06 14:15:54', 'f1083902518245d0bbb8392c1b7459a1'),
       ('a9e804837580426393fcba7dc6aaa268', 'C++程序设计学习与实验指导', '刘维富', '清华大学出版社', '9787302292449',
        '39', 'd38a74da2df24fc8863efc643eb8c0c8', '2023-03-06 14:29:02', '86244c68142a4cb99caf3cc30a1a0c98', null,
        null),
       ('ad53443ad0c84e759333981ea8b5e93d', '西方建筑史', '[英] 大卫·沃特金', '北京美术摄影出版社', '9787559202253',
        '328', 'c6567dec01e7406cb600adcf5328138d', '2023-03-06 14:55:26', '86244c68142a4cb99caf3cc30a1a0c98', null,
        null),
       ('b8031e6d608d4331a07e2780dd908cce', 'Java从入门到精通', '明日科技', '清华大学出版社', '9787302517597', '69.8',
        'd38a74da2df24fc8863efc643eb8c0c8', '2023-02-27 16:38:20', '86244c68142a4cb99caf3cc30a1a0c98',
        '2023-03-06 14:15:42', 'f1083902518245d0bbb8392c1b7459a1'),
       ('c13d6718286f4f36a910d2042f216ed2', 'Python从入门到精通', '明日科技', '清华大学出版社', '9787302503880', '79.8',
        'd38a74da2df24fc8863efc643eb8c0c8', '2023-02-27 16:39:26', '86244c68142a4cb99caf3cc30a1a0c98',
        '2023-03-06 14:15:31', 'f1083902518245d0bbb8392c1b7459a1'),
       ('c504aaf03d3d4762aeec51ae0791462a', '中国近现代史纲要', '李捷, 王顺生', '高等教育出版社', '9787040506990', '36',
        'bd1fb9389bc041e59590658fdbf162d1', '2023-02-27 16:34:38', '86244c68142a4cb99caf3cc30a1a0c98',
        '2023-03-06 14:16:03', 'f1083902518245d0bbb8392c1b7459a1'),
       ('cd95a12803af45fcb5400cd6a8a0fa70', 'PHP 基础案例教程', '黑马程序员', '人民邮电出版社', '9787115460325', '49.8',
        'd38a74da2df24fc8863efc643eb8c0c8', '2023-03-06 14:31:49', '86244c68142a4cb99caf3cc30a1a0c98', null, null),
       ('d870da05a676419eb15ab740cf0e7c36', '网页设计与制作', '刘万辉 常村红', '高等教育出版社', '9787040487497', '46',
        'd38a74da2df24fc8863efc643eb8c0c8', '2023-03-06 14:24:34', 'f1083902518245d0bbb8392c1b7459a1', null, null),
       ('ec7880be328e44a08ff41e0ac483220b', 'Android 移动应用开发任务驱动教程', '陈承欢', '电子工业出版社',
        '9787121366253', '55', 'd38a74da2df24fc8863efc643eb8c0c8', '2023-03-06 14:37:15',
        '86244c68142a4cb99caf3cc30a1a0c98', null, null),
       ('f7b59df76709457da442bcb75e0c9754', '少年本色', '王晓方', '春风文艺出版社', '9787531330349', '16',
        '01accc83831c49d597997c89276b1470', '2023-03-06 14:53:52', '86244c68142a4cb99caf3cc30a1a0c98', null, null),
       ('f9725921fbdb410f8713143ed1ee7833', 'JSP 程序设计项目教程', '王平华', '电子工业出版社', '9787121364907', '49.8',
        'd38a74da2df24fc8863efc643eb8c0c8', '2023-03-06 14:38:24', '86244c68142a4cb99caf3cc30a1a0c98', null, null),
       ('fccdeb8c5e704faebdcf40a26a539237', 'C++程序设计', '刘维富', '清华大学出版社', '9787302146391', '32',
        'd38a74da2df24fc8863efc643eb8c0c8', '2023-03-06 14:27:48', 'f1083902518245d0bbb8392c1b7459a1', null, null);


-- tbl_borrow
create table tbl_borrow
(
    id          char(32) primary key comment 'uuid',
    book_id     char(32) comment '图书id',
    user_id     char(32) comment '用户id',
    create_time char(19) comment '创建时间(借阅时间)',
    deadline    char(19) comment '归还时间'
) comment ='借阅记录表';


-- tbl_dict_value
create table tbl_dict_value
(
    id        char(32) primary key comment 'uuid',
    value     varchar(8),
    order_no  varchar(3),
    type_code varchar(8)
) comment ='字典表, 保存相应字段';
insert into tbl_dict_value
values ('5dc7881f8ead48b49cc784a2d49ae4d9', '哲学', '1', 'category'),
       ('021b4855ebe044e4a4c6ee25202a5c16', '宗教', '2', 'category'),
       ('c8184975dfad45e2bc907d07a99705e7', '伦理', '3', 'category'),
       ('1d61c4470e3f42bfad67309a61a22908', '美学', '4', 'category'),
       ('e19533f046454a209ce433d621c7d3f7', '心理', '5', 'category'),
       ('44bcd459325f4169adb8c03f4ed30616', '语言', '6', 'category'),
       ('01accc83831c49d597997c89276b1470', '文学', '7', 'category'),
       ('35de0bf0a0904a16af8212b3ffd96ff3', '艺术', '8', 'category'),
       ('6b8c360bab474fcabd66699b223f6100', '政治', '9', 'category'),
       ('b245bf9d21c046de92cca96cba23ac09', '经济', '10', 'category'),
       ('5473bfe7cda143d8b78ee4f21d557757', '军事', '11', 'category'),
       ('990b963018c647bcbe58ea660fbadbc6', '法律', '12', 'category'),
       ('962b663a3f8f4f789b3a809840a621e7', '体育', '13', 'category'),
       ('ae2e3cad7c874992af1542142ee2971e', '传媒', '14', 'category'),
       ('221e6924ab804a2a88fd695a4897b1af', '资讯', '15', 'category'),
       ('f79bb8046c4249f18463f8478e5aee5f', '管理', '16', 'category'),
       ('910124d5fce242c99cd4ecd26f1c2813', '商贸', '17', 'category'),
       ('bd1fb9389bc041e59590658fdbf162d1', '历史', '18', 'category'),
       ('da63810af67d4f22874ca658671aa1cf', '考古', '19', 'category'),
       ('b38563e45ab54a6bae554271a73a05f0', '民族', '20', 'category'),
       ('c43977b24a4747feb9ec485e58e72189', '生活', '21', 'category'),
       ('4632f60769d644e8ab70f763cda29e93', '财金', '22', 'category'),
       ('2bad20ec4b2a40c380a7407e98e4a7ea', '统计', '23', 'category'),
       ('2841995318fa48af9964f601d8e76e2f', '社会', '24', 'category'),
       ('e89786566cd1448a94fc0efeaa8828e2', '天文', '25', 'category'),
       ('25ca9300f5824871853f0fbcd352c0af', '地理', '26', 'category'),
       ('cfb56e55709847668f65c3ae4a5fff7f', '数学', '27', 'category'),
       ('c251d01f5f6b4dc3ac385b5b21026a26', '物理', '28', 'category'),
       ('8d5110ed02174d1fadaf068b9b328bd8', '化学', '29', 'category'),
       ('bac7cc7e65514f05a3973af4f0a5a2ef', '生物', '30', 'category'),
       ('e8d4b6001ee842d0be299345c53b02f4', '机械', '31', 'category'),
       ('ac04d26f8be247a7931ed98ec3f2b109', '电信', '32', 'category'),
       ('b6f6f98ff5584303a17bef10db24f062', '水力', '33', 'category'),
       ('da4991e05de144ecbeea61928b4f3001', '电力', '34', 'category'),
       ('23cc8e644c4940c792f770a5106969d8', '纺织', '35', 'category'),
       ('a6974333c3fb4b30ae6be41269e3a040', '食品', '36', 'category'),
       ('c6567dec01e7406cb600adcf5328138d', '建筑', '37', 'category'),
       ('ded94cd49bb64c42a20e071371e18687', '矿山', '38', 'category'),
       ('f408ff0e387543458ed79f89cc65e06f', '冶金', '39', 'category'),
       ('7cde662c7d9542f3a777f5172cd5be5b', '能源', '40', 'category'),
       ('58c6b3813ad3440d8f71bfc24f2f2c69', '交通', '41', 'category'),
       ('d3f1a4457a744cc6bb62d1f5f94ecc7f', '航天', '42', 'category'),
       ('46d5622430544c08952b046989c887ff', '医学', '43', 'category'),
       ('bccbefc196fd4addb2d231496179f08f', '工学', '44', 'category'),
       ('b1a0c393bd054490901f3abd34b817b4', '农学', '45', 'category'),
       ('1f0ff5107da34544975f813ea6f24445', '林学', '46', 'category'),
       ('228b5e90487e47bba2ddb2dde6b4f0c6', '养殖', '47', 'category'),
       ('d38a74da2df24fc8863efc643eb8c0c8', '电脑', '48', 'category'),
       ('64bc8bc2ceea4bb8bc692d32fce27d92', '环保', '49', 'category'),
       ('9de0b424bbc14580b0a80c2ec15bdeff', '信息', '50', 'category');


-- tbl_inventory
create table tbl_inventory
(
    id      char(32) primary key,
    book_id char(32) unique key,
    count   varchar(8)
) comment ='库存表';
insert into tbl_inventory
values ('0e4100ae1cc04bbf9ad57982603ccdd8', '69b91d51d93a477ca72da371e8e31819', '100'),
       ('124f8431e5fc40a7ab5cb7d74f6068fb', 'c13d6718286f4f36a910d2042f216ed2', '1000'),
       ('1920f23e799940b99004a9741d95a5e3', 'f9725921fbdb410f8713143ed1ee7833', '100'),
       ('1948b7f123aa42c68c7c6543b921c2ee', '0a99de1b240045bd8139ec6470c965f3', '1000'),
       ('244a56ee39664457b2e45c19b7b57a53', '2f13224dc23c470aae0fe53168794568', '100'),
       ('271f69823adc43e2a8b923657aa2ebca', 'ec7880be328e44a08ff41e0ac483220b', '100'),
       ('380226fe8e4a49998e2f3fd9d09479cc', '369356655eb84186b60eb4e51c9ae8fe', '100'),
       ('3bd9cc50edc94c0d900197e11ef3a8da', 'ad53443ad0c84e759333981ea8b5e93d', '100'),
       ('40bb0d82b68a42d3845b7f1892079acc', '808277392ea247219d9979ec89cbba3e', '1000'),
       ('539dae79792e480299081314704a6dad', 'd870da05a676419eb15ab740cf0e7c36', '100'),
       ('757c45d765b9451db2d620e0ab839d35', 'c504aaf03d3d4762aeec51ae0791462a', '500'),
       ('7d74d5cc85654616992425dc0ded1970', '6cbea7e3a8884dab8ddaa7297956b985', '100'),
       ('8c013fd299824e358139d738200d9a23', 'cd95a12803af45fcb5400cd6a8a0fa70', '100'),
       ('90f535988c97450abcdf0e26c58a1cff', 'f7b59df76709457da442bcb75e0c9754', '1000'),
       ('99b356cc43924f25a51845c671b7d348', '77d9c7d4486944aea8d653ea6cfad08a', '100'),
       ('bb799ffde9d74d228370a1fb9a2c5872', '705838f858cf483496fc2203ca650a53', '100'),
       ('be991d690d1840afa0c580180d073028', 'a9e804837580426393fcba7dc6aaa268', '100'),
       ('c4ae24880a264b8893390e32a5484e33', '22d5e417309b47aaa9f6db4661da5131', '500'),
       ('c51a0ad7ff814190a87b0a824ff69ed3', 'fccdeb8c5e704faebdcf40a26a539237', '100'),
       ('c925190505b144b2a2a40fbe73946708', 'b8031e6d608d4331a07e2780dd908cce', '1000'),
       ('cf75b9d4b007489cbf7f783145df10c0', '617ac4e33e964ff18821f6ce4a6f49b8', '100');


-- tbl_user
create table tbl_user
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
) comment ='用户表';
insert into tbl_user
values ('f1083902518245d0bbb8392c1b7459a1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '1', 'admin', null, null, null,
        null, null);