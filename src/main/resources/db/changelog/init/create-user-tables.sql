--liquibase formatted sql
--changeset i.chudakov:1

create table users
(
    id          uuid not null,
    first_name  varchar(255),
    second_name varchar(255),
    birthdate   varchar(10),
    biography   varchar(255),
    city        varchar(255)
);

create table users_metadata
(
    id uuid not null,
    role  varchar(255),
    password  varchar(255)
);