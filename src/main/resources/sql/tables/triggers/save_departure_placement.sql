create or replace TRIGGER save_departure_placement
    AFTER INSERT ON DEPARTURE
    FOR EACH ROW
DECLARE
    current_time DATE;
BEGIN
    current_time := SYSDATE;
    INSERT INTO DEPARTURE_STATUS_HISTORY (departure_id, status_id, status_set_date) VALUES (:NEW.departure_id, :NEW.departure_status_id, current_time);
END;