use harvard;

drop table if exists degree_program;
drop table if exists harvard_school;
drop table if exists certification;

create table certification
(
    id           int auto_increment primary key,
    name         varchar(16) unique not null,
    fullname     varchar(64) unique not null,
    degree_level varchar(16)        not null comment 'undergraduate & graduate'
) comment 'such as A.B., Ph.D.';

create table harvard_school
(
    id      int auto_increment primary key,
    name    varchar(128) unique not null,
    abbr    varchar(16) unique  not null,
    phone   varchar(16),
    email   varchar(64),
    address varchar(128)
);

create table degree_program
(
    id             int auto_increment primary key,
    name           varchar(64) not null,
    certification  int         not null,
    harvard_school int         not null,
    description    text,
    image          varchar(1024),
    create_at      timestamp default current_timestamp,
    update_at      timestamp default current_timestamp,
    unique (name, certification),
    foreign key (certification) references certification (id),
    foreign key (harvard_school) references harvard_school (id)
);

drop table if exists user;
create table user
(
    id        int auto_increment primary key,
    username  varchar(32) unique not null,
    password  varchar(32)        not null,
    email     varchar(64)        not null,
    create_at timestamp default now(),
    update_at timestamp default now()
);

drop table if exists operation_log;
create table operation_log
(
    id            int auto_increment primary key,
    operate_user  int comment 'user id',
    class_name    varchar(64),
    method_name   varchar(64),
    method_params varchar(1024),
    return_value  varchar(2048),
    create_at     timestamp default now()
);
