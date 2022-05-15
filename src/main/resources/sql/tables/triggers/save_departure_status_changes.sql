create or replace TRIGGER save_departure_status_changes
    AFTER UPDATE ON DEPARTURE
    FOR EACH ROW
DECLARE
    current_time DATE;
BEGIN
    current_time := SYSDATE;
    IF :NEW.DEPARTURE_STATUS_ID <> :OLD.DEPARTURE_STATUS_ID THEN
        INSERT INTO DEPARTURE_STATUS_HISTORY (departure_id, status_id, status_set_date) VALUES (:NEW.departure_id, :NEW.departure_status_id, current_time);
        IF :OLD.DEPARTURE_STATUS_ID = 1 AND :NEW.DEPARTURE_STATUS_ID <> 8 AND :NEW.DEPARTURE_STATUS_ID <> 9 THEN
            DELETE FROM TICKET WHERE departure_id = :NEW.DEPARTURE_ID;
        END IF;
    END IF;
END;