CREATE trigger BI_SERVICE_HISTORY_RECORD_ID
    before insert
    on AIRPLANE_SERVICE_HISTORY
    for each row
begin
    select SERVICE_HIST_RECORD_ID_SEQ.nextval into :NEW.record_id from dual;
end;