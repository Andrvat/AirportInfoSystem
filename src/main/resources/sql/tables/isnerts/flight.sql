INSERT ALL
    -- 2 2 4 5 airplanes
    -- 1 2 3 4 5 airlines

    -- From Novosibirsk
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (2, 1, 4, TO_DATE('04:35:00', 'hh24:mi:ss'), 8000, 50, 4, 1)
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (2, 1, 5, TO_DATE('04:20:00', 'hh24:mi:ss'), 7700, 60, 4, 1)
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (2, 1, 6, TO_DATE('05:05:00', 'hh24:mi:ss'), 13300, 80, 4, 1)
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (2, 1, 7, TO_DATE('05:35:00', 'hh24:mi:ss'), 21100, 100, 4, 1)
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (2, 1, 3, TO_DATE('02:05:00', 'hh24:mi:ss'), 7500, 80, 4, 1)
    
    -- From Moscow
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (5, 5, 1, TO_DATE('03:55:00', 'hh24:mi:ss'), 6300, 150, 3, 1)
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (5, 5, 2, TO_DATE('03:10:00', 'hh24:mi:ss'), 4900, 200, 3, 1)
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (5, 5, 3, TO_DATE('02:45:00', 'hh24:mi:ss'), 3800, 110, 3, 1)
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (5, 5, 4, TO_DATE('01:20:00', 'hh24:mi:ss'), 2200, 200, 3, 1)
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (5, 5, 6, TO_DATE('02:20:00', 'hh24:mi:ss'), 7800, 300, 3, 1)
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (5, 5, 9, TO_DATE('02:40:00', 'hh24:mi:ss'), 16000, 200, 3, 2)
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (5, 5, 8, TO_DATE('03:50:00', 'hh24:mi:ss'), 25000, 160, 3, 2)

    -- From Tyumen
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (4, 3, 1, TO_DATE('02:10:00', 'hh24:mi:ss'), 9000, 70, 5, 1)
    INTO FLIGHT (airplane_type_id, departure_location_id, arrival_location_id, travel_time, ticket_price, min_passengers_number, airline_id, flight_category_id)
        VALUES (4, 3, 5, TO_DATE('02:55:00', 'hh24:mi:ss'), 6000, 180, 5, 1)

SELECT 1 FROM DUAL;