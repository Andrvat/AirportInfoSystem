CREATE trigger BI_SPECIALTY_SPECIALTY_ID
    before insert
    on SPECIALTY
    for each row
begin
    select SPECIALTY_SPECIALTY_ID_SEQ.nextval into :NEW.specialty_id from dual;
end