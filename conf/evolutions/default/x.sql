
create table bill_notification (
  id                        number(19) not null,
  receiver_id               number(19),
  issue_date                timestamp,
  status                    varchar2(6) default 'Unread',
  bill_id                   number(19),
  constraint ck_bill_notification_status check (status in ('Read','Unread')),
  constraint pk_bill_notification primary key (id))
;
