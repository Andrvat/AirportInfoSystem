create or replace TRIGGER save_ticket_status_changes
    AFTER INSERT OR UPDATE ON TICKET
    FOR EACH ROW
DECLARE
    current_time DATE;
BEGIN
    current_time := SYSDATE;
    INSERT INTO TICKET_STATUS_HISTORY (departure_id, seat, ticket_status_id, status_set_date, passenger_id) VALUES (:NEW.departure_id, :NEW.seat, :NEW.ticket_status_id, current_time, NULL);
END;