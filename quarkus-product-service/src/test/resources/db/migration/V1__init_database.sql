CREATE SCHEMA IF NOT EXISTS quarkus;
create sequence if not exists quarkus.seq_product;
create table if not exists quarkus.product
(
    id         bigint PRIMARY KEY not null DEFAULT nextval('quarkus.seq_product'),
    name       varchar(100)       not null,
    brand      varchar(100)       not null,
    unit_price real,
    status     varchar(10)        not null,
    created_on timestamptz        not null,
    updated_on timestamptz
);
