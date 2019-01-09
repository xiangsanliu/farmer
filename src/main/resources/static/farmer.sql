# 建库
create database farmer default charset utf8 collate utf8_general_ci;
use farmer;

# 用户和权限
create table t_user
(
  username varchar(20) not null
    primary key,
  password varchar(20) null,
  userType tinyint     not null
);
create table t_permission
(
  id  int auto_increment
    primary key,
  url varchar(100) null
);
create table user_permission
(
  userType     int not null,
  permissionId int not null,
  primary key (userType, permissionId)
);


#建表:生产材料管理
create table t_material
(
  materialName varchar(20) not null
    primary key,
  price        int         null comment '单价:种子以g计,原料以kg计,表中数据为现实数据*100,以加快计算速度.',
  materialType tinyint     null comment '种子为 0, 原料为 1.'
)
  comment '农业原料数据表';
create table purchase_record
(
  rId          int auto_increment
    primary key,
  materialName varchar(10) null,
  amount       int         null,
  purchaseDate date        null,
  remarks      varchar(20) null
)
  comment '生产材料购买记录';


# 建表: 配肥配药管理
create table t_ingredient
(
  id             int auto_increment
    primary key,
  ingredientName varchar(20) null
);
create table fertilizer
(
  id       int auto_increment
    primary key,
  fDate    date        null,
  fName    varchar(20) null,
  fee      int         null comment '劳务费',
  lossRate int         null comment '总重量损耗比'
);
create table fertilizer_ingredient
(
  fertilizerId int not null,
  ingredientId int not null,
  amount       int null,
  primary key (fertilizerId, ingredientId),
  constraint fertilizer_ingredient_fertilizer_id_fk
    foreign key (fertilizerId) references fertilizer (id)
      on update cascade on delete cascade,
  constraint fertilizer_ingredient_t_ingredient_id_fk
    foreign key (ingredientId) references t_ingredient (id)
      on update cascade on delete cascade
);


# 插入初始数据
insert into t_user(username, password, userType)
VALUES ('admin', '20181225', 1),
       ('tech', '20181225', 2),
       ('tech2', '20181225', 2),
       ('market', '20181225', 3),
       ('guest', '', 4);

insert into t_permission(id, url)
values (1, '/getAllMaterialsAndRecords'),
       (2, '/createPurchaseRecord'),
       (3, '/removeRecord'),
       (4, '/createFI'),
       (5, '/getAllFertilizersAndIngredients'),
       (6, '/getIngredientsByFertilizer'),
       (7, '/removeFI'),
       (8, '/initUser');

insert into user_permission
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (2, 7),
       (2, 8),
       (3, 8),
       (4, 8);

insert into t_material(materialName, price)
VALUES ('番茄', 5),
       ('青椒', 4),
       ('牛心包菜', 3),
       ('黄瓜', 4),
       ('辣椒', 3),
       ('红薯尖', 5),
       ('白苋菜', 3),
       ('油麦菜', 4),
       ('四季豆', 5),
       ('甜玉米', 4),
       ('毛豆', 3),
       ('红苋菜', 4),
       ('黄秋葵', 3),
       ('西红柿', 4),
       ('茄子', 3),
       ('豆角', 4),
       ('丝瓜', 5),
       ('瓠子', 3),
       ('葫芦', 4),
       ('小南瓜', 5),
       ('西葫芦', 3),
       ('老南瓜', 3),
       ('平包', 5),
       ('小白菜', 4),
       ('生菜', 4),
       ('黄豆', 5),
       ('藕带', 4),
       ('莲子', 3),
       ('藕', 4),
       ('鸡粪', 5),
       ('马粪', 6),
       ('饼肥', 7),
       ('稻壳', 9),
       ('莲蓬壳', 10),
       ('稻草', 1),
       ('锯末', 2),
       ('树叶', 1);

insert into t_ingredient(ingredientName)
values ('鸡粪'),
       ('马粪'),
       ('饼肥'),
       ('稻壳'),
       ('莲蓬壳'),
       ('稻草'),
       ('锯末'),
       ('树叶');
