CREATE trigger BI_EMPLOYEE_ID
    before insert
    on EMPLOYEE
    for each row
begin
    select EMPLOYEE_ID_SEQ.nextval into :NEW.employee_id from dual;
end;