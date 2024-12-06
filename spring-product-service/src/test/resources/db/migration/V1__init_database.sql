CREATE SCHEMA IF NOT EXISTS spring;
create sequence if not exists spring.seq_product;
create table if not exists spring.product
(
    id         bigint PRIMARY KEY not null DEFAULT nextval('spring.seq_product'),
    name       varchar(100)       not null,
    brand      varchar(100)       not null,
    unit_price real,
    status     varchar(10)        not null,
    created_on timestamptz        not null,
    updated_on timestamptz
);
