# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table apartment (
  id                        bigint not null,
  apartment_no              varchar(255),
  apartment_building_id     bigint,
  constraint pk_apartment primary key (id))
;

create table apartment_building (
  id                        bigint not null,
  name                      varchar(255),
  email                     varchar(255),
  phone                     varchar(255),
  address                   varchar(255),
  real_estate_company_id    bigint,
  constraint pk_apartment_building primary key (id))
;

create table bill (
  id                        bigint not null,
  apartment_building_id     bigint,
  apartment_no              bigint,
  description               varchar(255),
  date_of_issuing           time,
  deadline                  time,
  status                    varchar(255),
  amount                    double,
  notification_id           bigint,
  constraint pk_bill primary key (id))
;

create table message (
  internal_id               bigint not null,
  THREAD_ID                 bigint not null,
  time_stamp                time,
  body                      varchar(255),
  is_read                   boolean,
  constraint pk_message primary key (internal_id))
;

create table notice (
  internal_id               bigint not null,
  category                  varchar(255),
  subject                   varchar(255),
  publish_date              date,
  valid_until               date,
  description               varchar(255),
  published_by              bigint,
  constraint pk_notice primary key (internal_id))
;

create table real_estate_company (
  id                        bigint not null,
  name                      varchar(255),
  email                     varchar(255),
  phone                     varchar(255),
  address                   varchar(255),
  constraint pk_real_estate_company primary key (id))
;

create table thread (
  internal_id               bigint not null,
  category                  varchar(255),
  date                      date,
  subject                   varchar(255),
  sender_id                 bigint,
  receiver_id               bigint,
  constraint pk_thread primary key (internal_id))
;

create table user_account (
  id                        bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  phone                     varchar(255),
  account_type              varchar(10),
  verification_status       varchar(10),
  date                      date,
  apartment_id              bigint,
  constraint ck_user_account_account_type check (account_type in ('Resident','Manager','Supervisor')),
  constraint ck_user_account_verification_status check (verification_status in ('Verified','Unverified')),
  constraint pk_user_account primary key (id))
;

create sequence apartment_seq;

create sequence apartment_building_seq;

create sequence bill_seq;

create sequence message_seq;

create sequence notice_seq;

create sequence real_estate_company_seq;

create sequence thread_seq;

create sequence user_account_seq;

alter table apartment add constraint fk_apartment_apartmentBuilding_1 foreign key (apartment_building_id) references apartment_building (id) on delete restrict on update restrict;
create index ix_apartment_apartmentBuilding_1 on apartment (apartment_building_id);
alter table apartment_building add constraint fk_apartment_building_realEsta_2 foreign key (real_estate_company_id) references real_estate_company (id) on delete restrict on update restrict;
create index ix_apartment_building_realEsta_2 on apartment_building (real_estate_company_id);
alter table message add constraint fk_message_thread_3 foreign key (THREAD_ID) references thread (internal_id) on delete restrict on update restrict;
create index ix_message_thread_3 on message (THREAD_ID);
alter table user_account add constraint fk_user_account_apartment_4 foreign key (apartment_id) references apartment (id) on delete restrict on update restrict;
create index ix_user_account_apartment_4 on user_account (apartment_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists apartment;

drop table if exists apartment_building;

drop table if exists bill;

drop table if exists message;

drop table if exists notice;

drop table if exists real_estate_company;

drop table if exists thread;

drop table if exists user_account;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists apartment_seq;

drop sequence if exists apartment_building_seq;

drop sequence if exists bill_seq;

drop sequence if exists message_seq;

drop sequence if exists notice_seq;

drop sequence if exists real_estate_company_seq;

drop sequence if exists thread_seq;

drop sequence if exists user_account_seq;

