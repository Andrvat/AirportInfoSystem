CREATE trigger BI_FLIGHT_CATEGORY_FLIGHT_CATEGORY_ID
    before insert
    on FLIGHT_CATEGORY
    for each row
begin
    select FLIGHT_CATEGORY_FLIGHT_CATEGORY_ID_SEQ.nextval into :NEW.flight_category_id from dual;
end