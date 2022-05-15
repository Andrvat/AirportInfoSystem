create or replace TRIGGER generate_tickets
    AFTER UPDATE ON DEPARTURE
    FOR EACH ROW
DECLARE
    trig_airplane_id NUMBER;
    trig_capacity NUMBER;
    trig_cargo FLOAT;
    loop_airplane_id NUMBER;
    loop_capacity NUMBER;
    loop_cargo FLOAT;
    CURSOR cur_capacity is
        SELECT airplane_id, people_capacity, cargo_capacity FROM (SELECT * FROM AIRPLANE JOIN AIRPLANE_TYPE USING (airplane_type_id));
BEGIN
    IF :NEW.AIRPLANE_ID <> :OLD.AIRPLANE_ID OR :OLD.AIRPLANE_ID IS NULL THEN
        trig_airplane_id := :NEW.AIRPLANE_ID;
        OPEN cur_capacity;
        LOOP
            FETCH cur_capacity INTO loop_airplane_id, loop_capacity, loop_cargo;
            EXIT WHEN cur_capacity%notfound;
            IF trig_airplane_id = loop_airplane_id THEN
                trig_capacity := loop_capacity;
                trig_cargo := loop_cargo;
            END IF;
        END LOOP;
        CLOSE cur_capacity;
        FOR I IN 1..trig_capacity
            LOOP
                INSERT INTO TICKET (DEPARTURE_ID, SEAT, TICKET_STATUS_ID, BAG_MAX_CAPACITY) VALUES (:NEW.DEPARTURE_ID, I, 1, trig_cargo / trig_capacity);
            END LOOP;
    END IF;
END;