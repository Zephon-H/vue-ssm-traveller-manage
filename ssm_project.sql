create table tb_log
(
    id             varchar(32)  not null comment '主键'
        primary key,
    visit_time     timestamp    null comment '访问时间',
    username       varchar(50)  null comment '操作者用户名',
    ip             varchar(20)  null comment '访问ip',
    url            text         null comment '访问资源url',
    execution_time int          null comment '执行时长',
    method         varchar(250) null comment '访问方法'
)
    comment '日志表';

create table tb_member
(
    id        varchar(32) not null comment '主键'
        primary key,
    name      varchar(20) null comment '姓名',
    nickname  varchar(20) null comment '昵称',
    phone_num varchar(20) null comment '电话号码',
    email     varchar(50) null comment '邮箱'
)
    comment '会员表';

create table tb_order
(
    id           varchar(32)  not null comment '主键'
        primary key,
    order_num    varchar(50)  not null comment '订单编号',
    order_time   timestamp    null comment '下单时间',
    people_count int          null comment '出行人数',
    order_desc   varchar(500) null comment '订单描述',
    pay_type     int          null comment '支付方式（0：支付宝，1：微信，2：其它',
    order_status int          null comment '订单状态（0：未支付，1：已支付）',
    member_id    int          null comment '会员(联系人)id',
    constraint tb_order_order_num_uindex
        unique (order_num)
)
    comment '订单表';

create table tb_order_product
(
    order_id   varchar(32) not null,
    product_id varchar(32) not null,
    primary key (order_id, product_id)
);

create table tb_order_traveller
(
    order_id     varchar(32) not null,
    traveller_id varchar(32) not null,
    primary key (order_id, traveller_id)
);

create table tb_permission
(
    id              varchar(32)  not null comment '主键'
        primary key,
    permission_name varchar(50)  null comment '角色名',
    url             varchar(255) null
)
    comment '资源权限表';

create table tb_product
(
    id             varchar(32)  not null comment '主键'
        primary key,
    product_num    varchar(50)  not null comment '产品编号',
    product_name   varchar(250) null comment '产品名称',
    city_name      varchar(250) null comment '出发城市',
    departure_time timestamp    null comment '出发时间',
    product_price  decimal      null,
    product_desc   varchar(500) null comment '产品描述',
    product_status int          null comment '状态（0：关闭，1：开启）',
    constraint tb_product_product_num_uindex
        unique (product_num)
)
    comment '产品表';

create table tb_role
(
    id        varchar(32) not null comment '主键'
        primary key,
    role_name varchar(50) null comment '角色名',
    role_desc varchar(50) null comment '角色描述'
)
    comment '角色表';

create table tb_role_permission
(
    role_id       varchar(32) not null,
    permission_id varchar(32) not null,
    primary key (role_id, permission_id)
);

create table tb_traveller
(
    id               varchar(32) not null comment '主键'
        primary key,
    name             varchar(20) null comment '姓名',
    gender           varchar(20) null comment '性别',
    phone_num        varchar(20) null comment '电话号码',
    credentials_type int         null comment '证件类型：0：身份证，1：护照，2：军官证',
    credentials_num  varchar(50) null comment '证件号码',
    traveller_type   int         null comment '旅客类型：0：成人，1：儿童'
)
    comment '旅客表';

create table tb_user
(
    id        varchar(32) not null comment '主键'
        primary key,
    email     varchar(50) not null comment '邮箱',
    username  varchar(50) null comment '用户名',
    password  varchar(50) null comment '密码(加密)',
    phone_num varchar(20) null comment '电话',
    status    int         null comment '状态:0:未开启，1：开启',
    avatar    text        null,
    constraint tb_user_email_uindex
        unique (email)
)
    comment '用户表';

create table tb_user_role
(
    user_id varchar(32) not null,
    role_id varchar(32) not null,
    primary key (user_id, role_id)
);

