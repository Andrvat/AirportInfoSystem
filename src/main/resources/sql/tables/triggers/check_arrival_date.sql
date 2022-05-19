create or replace TRIGGER CHECK_ARRIVAL_DATE
    BEFORE INSERT OR UPDATE OF ARRIVAL_DATE ON DEPARTURE
    FOR EACH ROW
    WHEN (NEW.ARRIVAL_DATE < TO_DATE('1970/01/01', 'yyyy/mm/dd'))
BEGIN
    raise_application_error(-20000, 'Arrival date cannot be less than of airport date founding');
END;