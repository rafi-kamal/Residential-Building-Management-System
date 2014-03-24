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
  apartment_id              bigint,
  description               varchar(255),
  date_of_issuing           date,
  deadline                  date,
  status                    varchar(255),
  amount                    double,
  constraint pk_bill primary key (id))
;

create table bill_notification (
  id                        bigint not null,
  receiver_id               bigint,
  issue_date                timestamp,
  status                    varchar(6),
  bill_id                   bigint,
  constraint ck_bill_notification_status check (status in ('Read','Unread')),
  constraint pk_bill_notification primary key (id))
;

create table maintenance_task (
  id                        bigint not null,
  task_type                 varchar(255),
  description               varchar(255),
  status                    varchar(255),
  deadline                  time,
  constraint pk_maintenance_task primary key (id))
;

create table maintenance_task_notification (
  id                        bigint not null,
  receiver_id               bigint,
  issue_date                timestamp,
  status                    varchar(6),
  maintenance_task_id       bigint,
  constraint ck_maintenance_task_notification_status check (status in ('Read','Unread')),
  constraint pk_maintenance_task_notification primary key (id))
;

create table message (
  internal_id               bigint not null,
  THREAD_ID                 bigint not null,
  time                      time,
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
  published_by_id           bigint,
  viewcount                 integer,
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

create table signin_info (
  email                     varchar(255) not null,
  password                  varchar(255),
  constraint pk_signin_info primary key (email))
;

create table thread (
  internal_id               bigint not null,
  category                  varchar(255),
  date                      date,
  subject                   varchar(255),
  sender_id                 bigint,
  receiver_id               bigint,
  occurrence                integer,
  constraint pk_thread primary key (internal_id))
;

create table user_account (
  id                        bigint not null,
  name                      varchar(255),
  email                     varchar(255),
  phone                     varchar(255),
  account_type              varchar(10),
  join_date                 timestamp,
  apartment_id              bigint,
  constraint ck_user_account_account_type check (account_type in ('Resident','Manager','Supervisor')),
  constraint uq_user_account_email unique (email),
  constraint pk_user_account primary key (id))
;

create sequence apartment_seq;

create sequence apartment_building_seq;

create sequence bill_seq;

create sequence bill_notification_seq;

create sequence maintenance_task_seq;

create sequence maintenance_task_notification_seq;

create sequence message_seq;

create sequence notice_seq;

create sequence real_estate_company_seq;

create sequence signin_info_seq;

create sequence thread_seq;

create sequence user_account_seq;

alter table apartment add constraint fk_apartment_apartmentBuilding_1 foreign key (apartment_building_id) references apartment_building (id) on delete restrict on update restrict;
create index ix_apartment_apartmentBuilding_1 on apartment (apartment_building_id);
alter table apartment_building add constraint fk_apartment_building_realEsta_2 foreign key (real_estate_company_id) references real_estate_company (id) on delete restrict on update restrict;
create index ix_apartment_building_realEsta_2 on apartment_building (real_estate_company_id);
alter table bill add constraint fk_bill_apartment_3 foreign key (apartment_id) references apartment (id) on delete restrict on update restrict;
create index ix_bill_apartment_3 on bill (apartment_id);
alter table bill_notification add constraint fk_bill_notification_receiver_4 foreign key (receiver_id) references user_account (id) on delete restrict on update restrict;
create index ix_bill_notification_receiver_4 on bill_notification (receiver_id);
alter table bill_notification add constraint fk_bill_notification_bill_5 foreign key (bill_id) references bill (id) on delete restrict on update restrict;
create index ix_bill_notification_bill_5 on bill_notification (bill_id);
alter table maintenance_task_notification add constraint fk_maintenance_task_notificati_6 foreign key (receiver_id) references user_account (id) on delete restrict on update restrict;
create index ix_maintenance_task_notificati_6 on maintenance_task_notification (receiver_id);
alter table maintenance_task_notification add constraint fk_maintenance_task_notificati_7 foreign key (maintenance_task_id) references maintenance_task (id) on delete restrict on update restrict;
create index ix_maintenance_task_notificati_7 on maintenance_task_notification (maintenance_task_id);
alter table message add constraint fk_message_thread_8 foreign key (THREAD_ID) references thread (internal_id) on delete restrict on update restrict;
create index ix_message_thread_8 on message (THREAD_ID);
alter table notice add constraint fk_notice_publishedBy_9 foreign key (published_by_id) references user_account (id) on delete restrict on update restrict;
create index ix_notice_publishedBy_9 on notice (published_by_id);
alter table thread add constraint fk_thread_sender_10 foreign key (sender_id) references user_account (id) on delete restrict on update restrict;
create index ix_thread_sender_10 on thread (sender_id);
alter table thread add constraint fk_thread_receiver_11 foreign key (receiver_id) references user_account (id) on delete restrict on update restrict;
create index ix_thread_receiver_11 on thread (receiver_id);
alter table user_account add constraint fk_user_account_apartment_12 foreign key (apartment_id) references apartment (id) on delete restrict on update restrict;
create index ix_user_account_apartment_12 on user_account (apartment_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists apartment;

drop table if exists apartment_building;

drop table if exists bill;

drop table if exists bill_notification;

drop table if exists maintenance_task;

drop table if exists maintenance_task_notification;

drop table if exists message;

drop table if exists notice;

drop table if exists real_estate_company;

drop table if exists signin_info;

drop table if exists thread;

drop table if exists user_account;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists apartment_seq;

drop sequence if exists apartment_building_seq;

drop sequence if exists bill_seq;

drop sequence if exists bill_notification_seq;

drop sequence if exists maintenance_task_seq;

drop sequence if exists maintenance_task_notification_seq;

drop sequence if exists message_seq;

drop sequence if exists notice_seq;

drop sequence if exists real_estate_company_seq;

drop sequence if exists signin_info_seq;

drop sequence if exists thread_seq;

drop sequence if exists user_account_seq;

