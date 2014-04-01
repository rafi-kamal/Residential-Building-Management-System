create or replace trigger msg_notif_trgr
    after insert or update on message
    for each row 
declare 
	mid message.internal_id%type;
	tid message.THREAD_ID%type;
	user_id1 USER_ACCOUNT.id%type;
	user_id2 USER_ACCOUNT.id%type;

begin
	mid:=:new.internal_id;
	tid:=:new.THREAD_id;

	select thread.sender_id into user_id1 from thread where thread.internal_id = :new.THREAD_ID;
	select thread.receiver_id into user_id2 from thread where thread.internal_id = :new.THREAD_ID;

	if user_id1 = :new.sender_id then
		insert into message_notification (id, receiver_id, message_internal_id) values (:new.internal_id, user_id2, :new.internal_id);
	
	else insert into message_notification (id, receiver_id, message_internal_id) values (:new.internal_id, user_id1, :new.internal_id);

	end if;
end;
/




