CREATE trigger BI_DEPARTURE_DEPARTURE_ID
    before insert
    on DEPARTURE
    for each row
begin
    select DEPARTURE_DEPARTURE_ID_SEQ.nextval into :NEW.departure_id from dual;
end;