CREATE trigger BI_MAINTENANCE_HIST_RECORD_ID
    before insert
    on AIRPLANE_MAINTENANCE_HISTORY
    for each row
begin
    select MAINTENANCE_HIST_RECORD_ID_SEQ.nextval into :NEW.record_id from dual;
end