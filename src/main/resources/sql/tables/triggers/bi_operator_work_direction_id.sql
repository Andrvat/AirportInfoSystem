CREATE trigger BI_OPERATOR_WORK_DIRECT_ID
    before insert
    on OPERATOR_WORK_DIRECTION
    for each row
begin
    select OPERATOR_WORK_DIRECTION_ID_SEQ.nextval into :NEW.work_direction_id from dual;
end