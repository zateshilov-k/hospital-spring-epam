create table diagnosis
(
  id          bigint       auto_increment
    primary key,
  description varchar(255) null,
  opened      bit          null,
  time        datetime     null,
  patient_id  bigint       not null,
  personal_id bigint       not null
) CHARACTER SET=utf8mb4;

create index FK8qmvjwaxcj6gjo9evce5uxs4f
  on diagnosis (personal_id);

create index FKp8tgyroh9ehqikufxe905q0xs
  on diagnosis (patient_id);

create table hibernate_sequence
(
  next_val bigint null
) CHARACTER SET=utf8mb4;

create table patient
(
  id         bigint auto_increment
    primary key,
  deleted    bit          null,
  discharged bit          null,
  first_name varchar(255) null,
  last_name  varchar(255) null
)CHARACTER SET=utf8mb4;

create table personal
(
  id         bigint auto_increment
    primary key,
  deleted    bit          null,
  first_name varchar(255) null,
  last_name  varchar(255) null,
  login      varchar(255) null,
  password   varchar(255) null,
  role       varchar(255) null
) CHARACTER SET=utf8mb4;

create table prescription
(
  id           bigint auto_increment
    primary key,
  description  varchar(255) null,
  done         bit          null,
  time         datetime     null,
  type         varchar(255) null,
  diagnosis_id bigint       not null
) CHARACTER SET=utf8mb4;

create index FKrj69paft3na7qqssd01vm9mtp
  on prescription (diagnosis_id);

