CREATE trigger BI_SPECIALTY_ID
    before insert
    on SPECIALIZATION
    for each row
begin
    select SPECIALTY_ID_SEQ.nextval into :NEW.specialty_id from dual;
end