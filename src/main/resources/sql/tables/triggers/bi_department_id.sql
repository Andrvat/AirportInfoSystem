CREATE trigger BI_DEPARTMENT_ID
    before insert
    on DEPARTMENT
    for each row
begin
    select DEPARTMENT_ID_SEQ.nextval into :NEW.department_id from dual;
end;