CREATE trigger BI_PASSENGER_ID
    before insert
    on PASSENGER
    for each row
begin
    select PASSENGER_ID_SEQ.nextval into :NEW.passenger_id from dual;
end;