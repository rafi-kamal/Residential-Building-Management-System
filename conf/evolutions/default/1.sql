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
  constraint pk_bill primary key (id))
;

create table message (
  internal_id               bigint not null,
  time_stamp                time,
  body                      varchar(255),
  is_read                   boolean,
  constraint pk_message primary key (internal_id))
;

create sequence bill_seq;

create sequence message_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists bill;

drop table if exists message;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists bill_seq;

drop sequence if exists message_seq;

