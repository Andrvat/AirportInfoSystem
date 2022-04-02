CREATE trigger BI_TICKET_STATUS_HISTORY_RECORD_ID
    before insert
    on TICKET_STATUS_HISTORY
    for each row
begin
    select TICKET_STATUS_HISTORY_RECORD_ID_SEQ.nextval into :NEW.record_id from dual;
end