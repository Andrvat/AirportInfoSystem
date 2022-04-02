CREATE trigger BI_DISPATCHER_WORK_DIRECTION_WORK_DIRECTION_ID
    before insert
    on DISPATCHER_WORK_DIRECTION
    for each row
begin
    select DISPATCHER_WORK_DIRECTION_WORK_DIRECTION_ID_SEQ.nextval into :NEW.work_direction_id from dual;
end