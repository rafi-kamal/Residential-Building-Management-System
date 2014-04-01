create table message_notification (
  id                        number(19) not null,
  receiver_id               number(19),
  issue_date                timestamp,
  status                    varchar2(6) default 'Unread',
  message_internal_id       number(19),
  constraint ck_message_notification_status check (status in ('Read','Unread')),
  constraint pk_message_notification primary key (id))
;
