drop table if exists tbl_borrow;

create table tbl_borrow
(
    id          char(32) primary key comment 'uuid',
    book_id     char(32) comment '图书id',
    user_id     char(32) comment '用户id',
    create_time char(19) comment '创建时间(借阅时间)',
    deadline    char(19) comment '归还时间'
) comment='借阅记录表';