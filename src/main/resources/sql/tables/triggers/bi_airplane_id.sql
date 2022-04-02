CREATE trigger BI_AIRPLANE_AIRPLANE_ID
    before insert
    on AIRPLANE
    for each row
begin
    select AIRPLANE_AIRPLANE_ID_SEQ.nextval into :NEW.airplane_id from dual;
end;