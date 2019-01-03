create table user (
	id bigint(16) unsigned not null auto_increment comment '主键',
	code varchar(32) not null default '' comment '用户唯一码',
	open_id varchar(32) not null default '' comment '小程序opendId',
	nick_name varchar(32) character set utf8mb4 not null default '' comment '昵称',
	head_photo varchar(128) not null default '' comment '头像',
	status tinyint(1) unsigned not null comment '状态(1:正常)',
	create_time datetime not null comment '创建时间',
	update_time datetime not null comment '修改时间',
	primary key (id),
	key idx_code (code),
	key idx_open_id (open_id)
) engine = InnoDB auto_increment=10001 default charset=utf8 comment '用户表';

create table company (
	id bigint(16) unsigned not null auto_increment comment '主键',
	code varchar(32) not null default '' comment '公司唯一码',
	name varchar(64) not null default '' comment '名称',
	link_man varchar(32) not null default '' comment '联系人',
	link_phone varchar(16) not null default '' comment '联系电话',
	status tinyint(1) unsigned not null comment '状态(1:正常)',
	create_time datetime not null comment '创建时间',
	update_time datetime not null comment '修改时间',
	primary key (id),
	key idx_code(code)
) engine = InnoDB auto_increment=10001 default charset=utf8 comment '公司表';

create table company_account(
	id bigint(16) unsigned not null auto_increment comment '主键',
	account varchar(32) not null default '' comment '账号',
	password varchar(32) not null default '' comment '密码',
	head_photo varchar(128) not null default '' comment '头像',
	nick_name varchar(64) not null default '' comment '昵称',
	status tinyint(1) unsigned not null comment '状态(1:正常)',
	create_time datetime not null comment '创建时间',
	company_id bigint(16) unsigned not null comment '公司id(关联company.id)',
	primary key (id),
	key idx_account (account),
	key idx_company_id (company_id)
) engine = InnoDB auto_increment=10001 default charset=utf8 comment '公司账号表';

create table company_banner (
	id bigint(16) unsigned not null auto_increment comment '主键',
	picture varchar(128) not null default '' comment '图片',
	link varchar(128) not null default '链接',
	type tinyint(1) unsigned not null comment '类型(1:无链接,2:产品)',
	sort tinyint(1) unsigned not null comment '排序',
	company_id bigint(16) unsigned not null comment '公司id(关联company.id)',
	primary key (id),
	key idx_company_id (company_id)
) engine=InnoDB auto_increment=10001 default charset=utf8 comment '公司banner表';

create table company_product_type (
	id bigint(16) unsigned not null auto_increment comment '主键',
	name varchar(32) not null default '' comment '名称',
	sort tinyint(1) unsigned not null comment '排序',
	company_id bigint(16) unsigned not null comment '公司id(关联company.id)',
	primary key (id),
	key idx_company_id (company_id)
)engine=InnoDB auto_increment=10001 default charset=utf8 comment '公司产品分类表';

create table product_type_associate (
	product_id bigint(16) unsigned not null comment '产品id(关联product.id)',
	company_product_type_id bigint(16) unsigned not null comment '公司产品分类id(关联company_product_type.id)',
	key idx_product_id(product_id),
	key idx_company_product_type_id (company_product_type_id)
) engine=InnoDB auto_increment=10001 default charset=utf8 comment '产品分类关系表';


create table product (
	id bigint(16) unsigned not null auto_increment comment '主键',
	code varchar(32) not null default '' comment '产品唯一码',
	photo varchar(128) not null default '' comment '主图',
	title varchar(256) not null default '' comment '标题',
	price int(12) unsigned not null comment '产品价格(分)',
	status tinyint(1) unsigned not null comment "状态(1:正常)",
	create_time datetime not null comment '创建时间',
	update_time datetime not null comment '修改时间',
	company_id bigint(16) unsigned not null comment '公司id(关联company.id)',
	primary key (id),
	key idx_code (code),
	key idx_company_id(company_id)
) engine=InnoDB auto_increment=10001 default charset=utf8 comment '产品表';

create table proudct_resource (
	id bigint(16) unsigned not null auto_increment comment '主键',
	resource text character set utf8mb4 not null comment '资源',
	type tinyint(1) unsigned not null comment '资源类型(1:轮播图,2:产品详情)',
	sort tinyint(1) unsigned not null comment '排序',
	product_id bigint(16) unsigned not null comment '产品id(关联product.id)',
	primary key (id),
	key idx_product_id (product_id)
) engine=InnoDB auto_increment=10001 default charset=utf8 comment '产品资源表';



