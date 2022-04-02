CREATE trigger BI_SECURITY_WORK_DIRECTION_WORK_DIRECTION_ID
    before insert
    on SECURITY_WORK_DIRECTION
    for each row
begin
    select SECURITY_WORK_DIRECTION_WORK_DIRECTION_ID_SEQ.nextval into :NEW.work_direction_id from dual;
end