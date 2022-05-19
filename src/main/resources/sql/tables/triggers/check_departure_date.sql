create or replace TRIGGER CHECK_DEPARTURE_DATE
    BEFORE INSERT OR UPDATE OF DEPARTURE_DATE ON DEPARTURE
    FOR EACH ROW
    WHEN (NEW.DEPARTURE_DATE < TO_DATE('1970/01/01', 'yyyy/mm/dd'))
BEGIN
    raise_application_error(-20000, 'Departure date cannot be less than of airport date founding');
END;