INSERT ALL
    -- From Novosibirsk
    INTO FLIGHT (airplane_type_id, departure_locality_id, arrival_locality_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (2, 1, 4, TO_DATE('07:35:00', 'hh24:mi:ss'), 8000, 50, 4, 1)
    INTO FLIGHT (airplane_type_id, departure_locality_id, arrival_locality_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (2, 1, 5, TO_DATE('04:20:00', 'hh24:mi:ss'), 7700, 60, 4, 1)
    INTO FLIGHT (airplane_type_id, departure_locality_id, arrival_locality_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (2, 1, 6, TO_DATE('05:05:00', 'hh24:mi:ss'), 13300, 80, 4, 1)
    INTO FLIGHT (airplane_type_id, departure_locality_id, arrival_locality_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (2, 1, 7, TO_DATE('05:35:00', 'hh24:mi:ss'), 21100, 100, 4, 1)

SELECT 1 FROM DUAL;