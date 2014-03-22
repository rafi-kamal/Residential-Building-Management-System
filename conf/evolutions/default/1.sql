# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table message (
  internal_id               bigint not null,
  time_stamp                time,
  body                      varchar(255),
  is_read                   boolean,
  constraint pk_message primary key (internal_id))
;

create sequence message_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists message;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists message_seq;

