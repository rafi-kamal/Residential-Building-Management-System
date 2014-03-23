# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table apartment (
  id                        number(19) not null,
  apartment_no              varchar2(255),
  apartment_building_id     number(19),
  constraint pk_apartment primary key (id))
;

create table apartment_building (
  id                        number(19) not null,
  name                      varchar2(255),
  email                     varchar2(255),
  phone                     varchar2(255),
  address                   varchar2(255),
  real_estate_company_id    number(19),
  constraint pk_apartment_building primary key (id))
;

create table bill (
  id                        number(19) not null,
  apartment_building_id     number(19),
  apartment_no              number(19),
  description               varchar2(255),
  date_of_issuing           timestamp,
  deadline                  timestamp,
  status                    varchar2(255),
  amount                    number(19,4),
  notification_id           number(19),
  constraint pk_bill primary key (id))
;

create table message (
  internal_id               number(19) not null,
  THREAD_ID                 number(19) not null,
  time_stamp                timestamp,
  body                      varchar2(255),
  is_read                   number(1),
  constraint pk_message primary key (internal_id))
;

create table notice (
  internal_id               number(19) not null,
  category                  varchar2(255),
  subject                   varchar2(255),
  publish_date              date,
  valid_until               date,
  description               varchar2(255),
  published_by              number(19),
  viewcount                 number(10),
  constraint pk_notice primary key (internal_id))
;

create table notification (
  id                        number(19) not null,
  receiver_id               number(19),
  issue_date                date,
  status                    varchar2(6),
  constraint ck_notification_status check (status in ('Read','Unread')),
  constraint pk_notification primary key (id))
;

create table real_estate_company (
  id                        number(19) not null,
  name                      varchar2(255),
  email                     varchar2(255),
  phone                     varchar2(255),
  address                   varchar2(255),
  constraint pk_real_estate_company primary key (id))
;

create table thread (
  internal_id               number(19) not null,
  category                  varchar2(255),
  start_date                date,
  subject                   varchar2(255),
  sender_id                 number(19),
  receiver_id               number(19),
  constraint pk_thread primary key (internal_id))
;

create table user_account (
  id                        number(19) not null,
  first_name                varchar2(255),
  last_name                 varchar2(255),
  email                     varchar2(255),
  phone                     varchar2(255),
  account_type              varchar2(10),
  join_date                 date,
  apartment_id              number(19),
  constraint ck_user_account_account_type check (account_type in ('Resident','Manager','Supervisor')),
  constraint pk_user_account primary key (id))
;

create sequence apartment_seq;

create sequence apartment_building_seq;

create sequence bill_seq;

create sequence message_seq;

create sequence notice_seq;

create sequence notification_seq;

create sequence real_estate_company_seq;

create sequence thread_seq;

create sequence user_account_seq;

alter table apartment add constraint fk_apartment_apartmentBuildi_1 foreign key (apartment_building_id) references apartment_building (id);
create index ix_apartment_apartmentBuildi_1 on apartment (apartment_building_id);
alter table apartment_building add constraint fk_apartment_building_realEs_2 foreign key (real_estate_company_id) references real_estate_company (id);
create index ix_apartment_building_realEs_2 on apartment_building (real_estate_company_id);
alter table message add constraint fk_message_thread_3 foreign key (THREAD_ID) references thread (internal_id);
create index ix_message_thread_3 on message (THREAD_ID);
alter table notification add constraint fk_notification_receiver_4 foreign key (receiver_id) references user_account (id);
create index ix_notification_receiver_4 on notification (receiver_id);
alter table user_account add constraint fk_user_account_apartment_5 foreign key (apartment_id) references apartment (id);
create index ix_user_account_apartment_5 on user_account (apartment_id);



# --- !Downs

drop table apartment cascade constraints purge;

drop table apartment_building cascade constraints purge;

drop table bill cascade constraints purge;

drop table message cascade constraints purge;

drop table notice cascade constraints purge;

drop table notification cascade constraints purge;

drop table real_estate_company cascade constraints purge;

drop table thread cascade constraints purge;

drop table user_account cascade constraints purge;

drop sequence apartment_seq;

drop sequence apartment_building_seq;

drop sequence bill_seq;

drop sequence message_seq;

drop sequence notice_seq;

drop sequence notification_seq;

drop sequence real_estate_company_seq;

drop sequence thread_seq;

drop sequence user_account_seq;

