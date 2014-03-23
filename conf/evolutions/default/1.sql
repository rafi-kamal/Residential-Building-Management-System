# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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

create table thread (
  internal_id               bigint not null,
  category                  varchar(255),
  date                      date,
  subject                   varchar(255),
  sender_id                 bigint,
  receiver_id               bigint,
  constraint pk_thread primary key (internal_id))
;

create sequence bill_seq;

create sequence message_seq;

create sequence thread_seq;

alter table message add constraint fk_message_thread_1 foreign key (THREAD_ID) references thread (internal_id) on delete restrict on update restrict;
create index ix_message_thread_1 on message (THREAD_ID);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists bill;

drop table if exists message;

drop table if exists thread;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists bill_seq;

drop sequence if exists message_seq;

drop sequence if exists thread_seq;

