CREATE SCHEMA IF NOT EXISTS quarkus;
create sequence if not exists quarkus.seq_product;
create table if not exists quarkus.product
(
    id         bigint PRIMARY KEY not null DEFAULT nextval('quarkus.seq_product'),
    name       varchar(100)       not null,
    brand      varchar(100)       not null,
    unit_price real,
    created_on timestamptz        not null,
    updated_on timestamptz
);

CREATE SCHEMA IF NOT EXISTS spring;
create sequence if not exists spring.seq_product;
create table if not exists spring.product
(
    id         bigint PRIMARY KEY not null DEFAULT nextval('spring.seq_product'),
    name       varchar(100)       not null,
    brand      varchar(100)       not null,
    unit_price real,
    created_on timestamptz        not null,
    updated_on timestamptz
);