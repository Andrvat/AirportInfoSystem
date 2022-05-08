CREATE trigger BI_LOCATION_ID
    before insert
    on LOCATION
    for each row
begin
    select LOCATION_ID_SEQ.nextval into :NEW.location_id from dual;
end;