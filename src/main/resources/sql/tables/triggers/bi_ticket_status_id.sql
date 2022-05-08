CREATE trigger BI_TICKET_STATUS_ID
    before insert
    on TICKET_STATUS
    for each row
begin
    select TICKET_STATUS_ID_SEQ.nextval into :NEW.ticket_status_id from dual;
end