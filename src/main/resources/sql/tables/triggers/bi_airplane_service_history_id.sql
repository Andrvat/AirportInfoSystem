CREATE trigger BI_AIRPLANE_SERVICE_HISTORY_RECORD_ID
    before insert
    on AIRPLANE_SERVICE_HISTORY
    for each row
begin
    select AIRPLANE_SERVICE_HISTORY_RECORD_ID_SEQ.nextval into :NEW.record_id from dual;
end