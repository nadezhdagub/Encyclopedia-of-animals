----------------------------------------------------------------------------- Пользователи
CREATE TABLE "account" (
    "id"              uuid NOT NULL,
    "parent"          uuid NULL,
    "email"           varchar(256) NULL,
    "passwd"          varchar(256) NULL,
    "access_level"    bigint NOT NULL DEFAULT 0,
    CONSTRAINT account_pk PRIMARY KEY ("id"),
    CONSTRAINT account_fk_parent FOREIGN KEY ("parent") REFERENCES "account"("id"),
    constraint "account_email_uq" unique ("email")
);


create table "animal_inf" (
    "id"                   uuid not null,
    "inf_of_type"          varchar(2048) not null,
    "inf_of_area"          varchar(2048) not null,
    "inf_of_number"        varchar(2048) not null,
    "inf_of_downsizing"    varchar(2048) not null,
    "inf_of_security"      varchar(2048) not null,

    constraint "animal_inf_pk" primary key ("id")
);


create table "name_of_type" (
    "id"                uuid        not null,
    "animal_id"         uuid        not null,
    "name"              varchar(15) not null,

    constraint "name_of_type_pk" primary key ("id")
);


create table "photo_of_type" (
    "id"                uuid        not null,
    "animal_id"         uuid        not null,
    "name"              varchar(15) not null,
    "photo"             bytea,

    constraint "photo_of_type_pk" primary key ("id")
);

create table "photo_of_area" (
    "id"                uuid        not null,
    "animal_id"         uuid        not null,
    "name"              varchar(15) not null,
    "photo"             bytea,

    constraint "photo_of_area_pk" primary key ("id")
);


