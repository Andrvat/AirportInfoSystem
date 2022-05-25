create or replace TRIGGER CHECK_TICKET_CHANGES
    BEFORE UPDATE ON TICKET
    FOR EACH ROW
BEGIN
    IF (:NEW.TICKET_STATUS_ID = 2 OR :NEW.TICKET_STATUS_ID = 3) AND (:OLD.TICKET_STATUS_ID = 2 OR :OLD.TICKET_STATUS_ID = 3) THEN
        raise_application_error(-20000, 'Passenger should only change ticket status of his tickets');
    END IF;
END;