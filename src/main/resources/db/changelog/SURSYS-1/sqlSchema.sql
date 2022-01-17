--liquibase formatted sql

--changeset akorzh:initialSequence
create sequence if not exists hibernate_sequence
    increment 1
    minvalue 1
    maxvalue 9223372036854775807
    start 1
    cache 1;
-- rollback drop sequence hibernate_sequence;
--comment: Добавлен hibernate_sequence

--changeset akorzh:user1
create table if not exists users
(
    id         int8 default nextval('hibernate_sequence'::regclass) not null primary key unique,
    login      varchar(64)                                          not null unique,
    email      varchar(64)                                          not null unique,
    password   varchar(256)                                         not null,
    first_name varchar(64)                                          not null,
    last_name  varchar(64)                                          not null,
    patronymic varchar(64)
);
--rollback drop table users;
--comment: Создана таблица users

--changeset akorzh:roles1
create table if not exists roles
(
    id   int8 default nextval('hibernate_sequence'::regclass) not null primary key unique,
    name varchar(64)                                          not null unique
);
--rollback drop table roles;
--comment: Создана таблица roles

--changeset akorzh:userRoles1
create table if not exists user_roles
(
    user_id int8 not null,
    role_id int8 not null,
    primary key (user_id, role_id),
    constraint user_roles_ibfk_1 foreign key (user_id) references users (id),
    constraint user_roles_ibfk_2 foreign key (role_id) references roles (id)
);
--rollback drop table user_roles;
--comment: Создана таблица user_roles

--changeset akorzh:university
create table if not exists university
(
    id   int8 default nextval('hibernate_sequence'::regclass) not null primary key unique,
    name varchar(256)                                         not null unique,
    guid varchar(64)                                          not null unique
);
--rollback drop table university;
--comment: Создана таблица university

--changeset akorzh:groups
create table if not exists groups
(
    id            int8 default nextval('hibernate_sequence'::regclass) not null primary key unique,
    name          varchar(256)                                         not null,
    guid          varchar(64)                                          not null,
    year          varchar(4)                                           not null,
    university_id int8                                                 not null,
    constraint group_university_ibfk_1 foreign key (university_id) references university (id),
    unique (name, guid, university_id)
);
--rollback drop table groups;
--comment: Создана таблица groups

--changeset akorzh:subject
create table if not exists subject
(
    id            int8 default nextval('hibernate_sequence'::regclass) not null primary key unique,
    name          varchar(256)                                         not null,
    rate_type     varchar(32)                                          not null,
    university_id int8                                                 not null,
    constraint group_university_ibfk_1 foreign key (university_id) references university (id),
    unique (name, rate_type, university_id)
);
--rollback drop table subject;
--comment: Создана таблица subject

--changeset akorzh:pupil
create table if not exists pupil
(
    id          int8        not null default nextval('hibernate_sequence'::regclass) primary key unique,
    record_book varchar(64) not null,
    user_id     int8        not null,
    group_id    int8        not null,
    submitted   boolean     not null default false,
    constraint pupil_user_ibfk_1 foreign key (user_id) references users (id),
    constraint pupil_group_ibfk_1 foreign key (group_id) references groups (id),
    unique (user_id, group_id)
);
--rollback drop table pupil;
--comment: Создана таблица pupil

--changeset akorzh:teacher
create table if not exists teacher
(
    id            int8        not null default nextval('hibernate_sequence'::regclass) primary key unique,
    university_id int8        not null,
    user_id       int8        not null,
    grade         varchar(64) not null,
    teaching_date timestamp   not null,
    submitted     boolean     not null default false,
    constraint teacher_user_ibfk_1 foreign key (user_id) references users (id),
    constraint teacher_university_ibfk_1 foreign key (university_id) references university (id)
);
--rollback drop table teacher;
--comment: Создана таблица teacher

--changeset akorzh:course
create table if not exists course
(
    id         int8 default nextval('hibernate_sequence'::regclass) not null primary key unique,
    group_id   int8                                                 not null,
    subject_id int8                                                 not null,
    teacher_id int8                                                 not null,
    since      timestamp                                            not null,
    till       timestamp                                            not null,
    hours      numeric(4)                                           not null,
    constraint group_subject_teacher_ibfk_1 foreign key (group_id) references groups (id),
    constraint group_subject_teacher_ibfk_2 foreign key (subject_id) references subject (id),
    constraint group_subject_teacher_ibfk_3 foreign key (teacher_id) references teacher (id),
    unique (subject_id, group_id, teacher_id, since, till)
);
--rollback drop table course;
--comment: Создана таблица course

--changeset akorzh:survey
create table if not exists survey
(
    id           int8         not null default nextval('hibernate_sequence'::regclass) primary key unique,
    user_id      int8         not null,
    name         varchar(256) not null,
    created_date timestamp    not null,
    type         varchar(64)  not null,
    common       bool         not null default false,
    constraint survey_user_ibfk_1 foreign key (user_id) references users (id),
    unique (user_id, name)
);
--rollback drop table survey;
--comment: Создана таблица survey

--changeset akorzh:survey_permission
create table if not exists survey_permission
(
    survey_id                int8 not null,
    course_id int8 not null,
    constraint survey_permission_ibfk_1 foreign key (survey_id) references survey (id),
    constraint survey_permission_ibfk_2 foreign key (course_id) references course (id)
);
--rollback drop table survey_permission;
--comment: Создана таблица survey_permission

--changeset akorzh:category
create table if not exists category
(
    id        int8         not null default nextval('hibernate_sequence'::regclass) primary key unique,
    name      varchar(256) not null,
    common    boolean      not null default false,
    survey_id int8         not null,
    constraint question_survey_ibfk_1 foreign key (survey_id) references survey (id),
    unique (name, survey_id)
);
--rollback drop table category;
--comment: Создана таблица category

--changeset akorzh:question
create table if not exists question
(
    id          int8 default nextval('hibernate_sequence'::regclass) not null primary key unique,
    name        varchar(256)                                         not null,
    answer_type varchar(64)                                          not null,
    survey_id   int8                                                 not null,
    category_id int8                                                 not null,
    constraint question_survey_ibfk_1 foreign key (survey_id) references survey (id),
    constraint question_category_ibfk_1 foreign key (category_id) references category (id),
    unique (name, survey_id, category_id)
);
--rollback drop table question;
--comment: Создана таблица question

--changeset akorzh:answer
create table if not exists answer
(
    id          int8 default nextval('hibernate_sequence'::regclass) not null primary key unique,
    value       numeric(3),
    text        varchar(264),
    question_id int8                                                 not null,
    user_id     int8                                                 not null,
    constraint answer_question_ibfk_1 foreign key (question_id) references question (id),
    constraint answer_person_ibfk_1 foreign key (user_id) references users (id)
);
--rollback drop table answer;
--comment: Создана таблица answer

--changeset akorzh:insert default roles
INSERT INTO roles (name)
VALUES ('ROLE_UNIVERSITY_ADMINISTRATOR'),
       ('ROLE_ADMINISTRATOR'),
       ('ROLE_USER');

--changeset akorzh:insert default roles1
INSERT INTO roles (name)
VALUES ('ROLE_TEACHER'),
       ('ROLE_PUPIL');

ALTER TABLE users ADD COLUMN university_id int8;
ALTER TABLE users ADD CONSTRAINT user_university foreign key (university_id) references university (id);
ALTER TABLE teacher DROP COLUMN university_id;

