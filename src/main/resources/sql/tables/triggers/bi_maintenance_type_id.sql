CREATE trigger BI_MAINTENANCE_TYPE_ID
    before insert
    on MAINTENANCE_TYPE
    for each row
begin
    select MAINTENANCE_TYPE_ID_SEQ.nextval into :NEW.maintenance_type_id from dual;
end