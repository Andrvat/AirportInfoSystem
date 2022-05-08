CREATE trigger BI_AIRPLANE_TYPE_ID
    before insert
    on AIRPLANE_TYPE
    for each row
begin
    select AIRPLANE_TYPE_ID_SEQ.nextval into :NEW.airplane_type_id from dual;
end