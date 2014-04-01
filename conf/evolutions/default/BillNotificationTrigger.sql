CREATE OR REPLACE TRIGGER bill_notification_trg
	AFTER INSERT ON BILL
	FOR EACH ROW

DECLARE
	user_id USER_ACCOUNT.id%type;

BEGIN
	select id into user_id
	from USER_ACCOUNT
	where USER_ACCOUNT.apartment_id = :new.apartment_id;
	
	insert into BILL_NOTIFICATION (receiver_id, bill_id)
	values(user_id, :new.id);
	
END;
/