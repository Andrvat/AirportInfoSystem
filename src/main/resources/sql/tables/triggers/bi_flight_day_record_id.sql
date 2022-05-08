CREATE trigger BI_FLIGHT_DAY_RECORD_ID
    before insert
    on FLIGHT_DAY
    for each row
begin
    select FLIGHT_DAY_RECORD_ID_SEQ.nextval into :NEW.record_id from dual;
end;