drop table if exists ALGORITHMS;
create table ALGORITHMS(
    id int primary key auto_increment,
    name varchar(100) not null,
    purpose varchar(255) not null
);

drop table if exists SECTIONS;
create table SECTIONS(
    id int primary key auto_increment,
    name varchar(100) not null
);