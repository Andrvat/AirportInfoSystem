CREATE trigger BI_LOCALITY_LOCALITY_ID
    before insert
    on LOCALITY
    for each row
begin
    select LOCALITY_LOCALITY_ID_SEQ.nextval into :NEW.locality_id from dual;
end