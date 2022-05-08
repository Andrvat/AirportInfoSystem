CREATE trigger BI_FLIGHT_ID
    before insert
    on FLIGHT
    for each row
begin
    select FLIGHT_ID_SEQ.nextval into :NEW.flight_id from dual;
end;