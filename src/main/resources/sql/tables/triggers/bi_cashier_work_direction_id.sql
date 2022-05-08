CREATE trigger BI_CASHIER_WORK_DIRECT_ID
    before insert
    on CASHIER_WORK_DIRECTION
    for each row
begin
    select CASHIER_WORK_DIRECTION_ID_SEQ.nextval into :NEW.work_direction_id from dual;
end