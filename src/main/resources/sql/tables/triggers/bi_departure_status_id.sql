CREATE trigger BI_DEPARTURE_STATUS_ID
    before insert
    on DEPARTURE_STATUS
    for each row
begin
    select DEPARTURE_STATUS_ID_SEQ.nextval into :NEW.departure_status_id from dual;
end;