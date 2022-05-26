create or replace TRIGGER CHECK_EMPLOYMENT_DATE
    BEFORE INSERT OR UPDATE OF EMPLOYMENT_DATE
    ON EMPLOYEE
    FOR EACH ROW
    WHEN (NEW.EMPLOYMENT_DATE >= CURRENT_DATE OR NEW.EMPLOYMENT_DATE < TO_DATE('1970/01/01', 'yyyy/mm/dd'))
BEGIN
    raise_application_error(-20000,
                            'Employment date cannot be greater than current date and less than of airport date founding');
END;