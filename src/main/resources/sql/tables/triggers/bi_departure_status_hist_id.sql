CREATE trigger BI_DEPARTURE_STATUS_HIST_ID
    before insert
    on DEPARTURE_STATUS_HISTORY
    for each row
begin
    select DEPARTURE_STATUS_HIST_ID_SEQ.nextval into :NEW.record_id from dual;
end;