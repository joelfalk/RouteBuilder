--liquibase formatted sql
--changeset joel:3 dbms:oracle contexts:db-structure
create table PERMISSIONS
(
    NAME    varchar(12) not null,
    ID INTEGER not null,
    BANK varchar(20),
    PERMISSION varchar(20),
    PRIMARY KEY (ID)
);
--rollback drop table PERMISSIONS

