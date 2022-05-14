create or replace PROCEDURE generate_departures(next_days IN NUMBER)
    IS
    pr_flight_id FLIGHT_DAY.flight_id%type;
    pr_travel_time FLIGHT.travel_time%type;
    pr_week_day FLIGHT_DAY.week_day%type;
    pr_departure_time FLIGHT_DAY.departure_time%type;
    current_time DATE;
    pr_last_date DATE;
    day_number NUMBER;
    pr_departure_date DATE;
    pr_arrival_date DATE;
    CURSOR cur is
        SELECT flight_id, travel_time, week_day, departure_time FROM (SELECT * FROM FLIGHT_DAY JOIN FLIGHT USING (flight_id));
    CURSOR date_cur is
        SELECT last_date FROM LAST_GENERATION_DATE;
BEGIN
    OPEN date_cur;
    LOOP
        FETCH date_cur INTO pr_last_date;
        EXIT WHEN date_cur%notfound;
    END LOOP;
    CLOSE date_cur;
    IF pr_last_date IS NULL THEN
        current_time := SYSDATE;
    ELSE
        current_time := pr_last_date + next_days;
    END IF;
    OPEN cur;
    LOOP
        FETCH cur into pr_flight_id, pr_travel_time, pr_week_day, pr_departure_time;
        EXIT WHEN cur%notfound;
        FOR I IN 1..next_days
            LOOP
                day_number := TO_CHAR(current_time + I, 'D');
                IF day_number = pr_week_day THEN
                    pr_departure_date := TRUNC(current_time + I) + (pr_departure_time - TRUNC(pr_departure_time));
                    pr_arrival_date := TRUNC(current_time + I) + (pr_departure_time - TRUNC(pr_departure_time)) + (pr_travel_time - TRUNC(pr_travel_time));
                    INSERT INTO DEPARTURE (AIRPLANE_ID, FLIGHT_ID, DEPARTURE_DATE, ARRIVAL_DATE, DEPARTURE_STATUS_ID, STATUS_SET_DATE) VALUES (NULL, pr_flight_id, pr_departure_date, pr_arrival_date, 1, current_time);
                    UPDATE LAST_GENERATION_DATE SET LAST_DATE = current_time WHERE ROWNUM = 1;
                END IF;
            END LOOP;
    END LOOP;
    CLOSE cur;
END generate_departures;