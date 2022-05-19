create or replace TRIGGER generate_tickets
    AFTER INSERT ON DEPARTURE
    FOR EACH ROW
DECLARE
    trig_capacity NUMBER;
    trig_cargo FLOAT;
    loop_flight_id NUMBER;
    loop_airplane_type_id NUMBER;
    loop_capacity NUMBER;
    loop_cargo FLOAT;
    loop_flight_cat_id NUMBER;
    need_generate NUMBER;
    CURSOR cur_capacity is
        SELECT flight_id, airplane_type_id, people_capacity, cargo_capacity, flight_category_id FROM (SELECT * FROM FLIGHT LEFT JOIN AIRPLANE_TYPE USING (airplane_type_id));
BEGIN
    need_generate := 0;
    OPEN cur_capacity;
    LOOP
        FETCH cur_capacity INTO loop_flight_id, loop_airplane_type_id, loop_capacity, loop_cargo, loop_flight_cat_id;
        EXIT WHEN cur_capacity%notfound;
        IF :NEW.FLIGHT_ID = loop_flight_id THEN
            IF loop_flight_cat_id = 1 OR loop_flight_cat_id = 2 THEN
                need_generate := 1;
            END IF;
            trig_capacity := loop_capacity;
            trig_cargo := loop_cargo;
        END IF;
    END LOOP;
    CLOSE cur_capacity;

    IF need_generate = 1 THEN
        FOR I IN 1..trig_capacity
            LOOP
                INSERT INTO TICKET (DEPARTURE_ID, SEAT, TICKET_STATUS_ID, BAG_MAX_CAPACITY) VALUES (:NEW.DEPARTURE_ID, I, 1, trig_cargo / trig_capacity);
         END LOOP;
    END IF;
END;