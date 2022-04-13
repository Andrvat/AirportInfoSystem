CREATE trigger BI_SERVICE_WORK_DIRECTION_WORK_DIRECTION_ID
    before insert
    on SERVICE_WORK_DIRECTION
    for each row
begin
    select SERVICE_WORK_DIRECTION_WORK_DIRECTION_ID_SEQ.nextval into :NEW.work_direction_id from dual;
end