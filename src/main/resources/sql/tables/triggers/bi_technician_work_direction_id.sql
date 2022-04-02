CREATE trigger BI_TECHNICIAN_WORK_DIRECTION_WORK_DIRECTION_ID
    before insert
    on TECHNICIAN_WORK_DIRECTION
    for each row
begin
    select TECHNICIAN_WORK_DIRECTION_WORK_DIRECTION_ID_SEQ.nextval into :NEW.work_direction_id from dual;
end