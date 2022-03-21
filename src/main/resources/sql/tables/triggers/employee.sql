CREATE trigger BI_Employee_Id
    before insert on Employee
    for each row
begin
    select Employee_Id_SEQ.nextval into :NEW.Id from dual;
end