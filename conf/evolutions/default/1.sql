# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table apartment (
  id                        number(19) not null,
  apartment_no              varchar2(255),
  user_account_id           number(19),
  apartment_building_id     number(19),
  constraint pk_apartment primary key (id))
;

create table apartment_building (
  id                        number(19) not null,
  name                      varchar2(255),
  address                   varchar2(255),
  real_estate_company_id    number(19),
  constraint pk_apartment_building primary key (id))
;

create table bill (
  id                        number(19) not null,
  apartment_id              number(19),
  description               varchar2(255),
  date_of_issuing           timestamp,
  deadline                  timestamp,
  status                    varchar2(255),
  amount                    number(19,4),
  constraint pk_bill primary key (id))
;

create table bill_notification (
  id                        number(19) not null,
  receiver_id               number(19),
  issue_date                timestamp,
  status                    varchar2(6),
  bill_id                   number(19),
  constraint ck_bill_notification_status check (status in ('Read','Unread')),
  constraint pk_bill_notification primary key (id))
;

create table message (
  internal_id               number(19) not null,
  THREAD_ID                 number(19) not null,
  time                      timestamp,
  body                      varchar2(255),
  is_read                   number(1),
  sender_id                 number(19),
  constraint pk_message primary key (internal_id))
;

create table notice (
  internal_id               number(19) not null,
  category                  varchar2(255),
  subject                   varchar2(255),
  publish_date              date,
  valid_until               timestamp,
  description               varchar2(255),
  published_by_id           number(19),
  viewcount                 number(10),
  constraint pk_notice primary key (internal_id))
;

create table real_estate_company (
  id                        number(19) not null,
  name                      varchar2(255),
  email                     varchar2(255),
  phone                     varchar2(255),
  address                   varchar2(255),
  constraint pk_real_estate_company primary key (id))
;

create table task (
  id                        number(19) not null,
  apartment_building_id     number(19),
  task_type                 varchar2(255),
  description               varchar2(255),
  status                    varchar2(255),
  date_of_issuing           timestamp,
  deadline                  timestamp,
  constraint pk_task primary key (id))
;

create table task_notification (
  id                        number(19) not null,
  receiver_id               number(19),
  issue_date                timestamp,
  status                    varchar2(6),
  maintenance_task_id       number(19),
  constraint ck_task_notification_status check (status in ('Read','Unread')),
  constraint pk_task_notification primary key (id))
;

create table thread (
  internal_id               number(19) not null,
  category                  varchar2(255),
  sent_time                 date,
  subject                   varchar2(255),
  sender_id                 number(19),
  receiver_id               number(19),
  status                    varchar2(255),
  occurrence                number(10),
  constraint pk_thread primary key (internal_id))
;

create table user_account (
  id                        number(19) not null,
  name                      varchar2(255),
  email                     varchar2(255),
  phone                     varchar2(255),
  password                  varchar2(255),
  account_type              varchar2(10),
  join_date                 timestamp,
  apartment_id              number(19),
  constraint ck_user_account_account_type check (account_type in ('Resident','Manager','Supervisor')),
  constraint uq_user_account_email unique (email),
  constraint pk_user_account primary key (id))
;

create sequence apartment_seq;

create sequence apartment_building_seq;

create sequence bill_seq;

create sequence bill_notification_seq;

create sequence message_seq;

create sequence notice_seq;

create sequence real_estate_company_seq;

create sequence task_seq;

create sequence task_notification_seq;

create sequence thread_seq;

create sequence user_account_seq;

alter table apartment add constraint fk_apartment_userAccount_1 foreign key (user_account_id) references user_account (id);
create index ix_apartment_userAccount_1 on apartment (user_account_id);
alter table apartment add constraint fk_apartment_apartmentBuildi_2 foreign key (apartment_building_id) references apartment_building (id);
create index ix_apartment_apartmentBuildi_2 on apartment (apartment_building_id);
alter table apartment_building add constraint fk_apartment_building_realEs_3 foreign key (real_estate_company_id) references real_estate_company (id);
create index ix_apartment_building_realEs_3 on apartment_building (real_estate_company_id);
alter table bill add constraint fk_bill_apartment_4 foreign key (apartment_id) references apartment (id);
create index ix_bill_apartment_4 on bill (apartment_id);
alter table bill_notification add constraint fk_bill_notification_receive_5 foreign key (receiver_id) references user_account (id);
create index ix_bill_notification_receive_5 on bill_notification (receiver_id);
alter table bill_notification add constraint fk_bill_notification_bill_6 foreign key (bill_id) references bill (id);
create index ix_bill_notification_bill_6 on bill_notification (bill_id);
alter table message add constraint fk_message_thread_7 foreign key (THREAD_ID) references thread (internal_id);
create index ix_message_thread_7 on message (THREAD_ID);
alter table message add constraint fk_message_sender_8 foreign key (sender_id) references user_account (id);
create index ix_message_sender_8 on message (sender_id);
alter table notice add constraint fk_notice_publishedBy_9 foreign key (published_by_id) references user_account (id);
create index ix_notice_publishedBy_9 on notice (published_by_id);
alter table task add constraint fk_task_apartmentBuilding_10 foreign key (apartment_building_id) references apartment_building (id);
create index ix_task_apartmentBuilding_10 on task (apartment_building_id);
alter table task_notification add constraint fk_task_notification_receiv_11 foreign key (receiver_id) references user_account (id);
create index ix_task_notification_receiv_11 on task_notification (receiver_id);
alter table task_notification add constraint fk_task_notification_mainte_12 foreign key (maintenance_task_id) references task (id);
create index ix_task_notification_mainte_12 on task_notification (maintenance_task_id);
alter table thread add constraint fk_thread_sender_13 foreign key (sender_id) references user_account (id);
create index ix_thread_sender_13 on thread (sender_id);
alter table thread add constraint fk_thread_receiver_14 foreign key (receiver_id) references user_account (id);
create index ix_thread_receiver_14 on thread (receiver_id);
alter table user_account add constraint fk_user_account_apartment_15 foreign key (apartment_id) references apartment (id);
create index ix_user_account_apartment_15 on user_account (apartment_id);




