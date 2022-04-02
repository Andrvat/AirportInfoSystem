CREATE trigger BI_AIRLINE_AIRLINE_ID
    before insert
    on AIRLINE
    for each row
begin
    select AIRLINE_AIRLINE_ID_SEQ.nextval into :NEW.airline_id from dual;
end