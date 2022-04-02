CREATE TABLE FLIGHT
(
    flight_id             INT       NOT NULL,
    airplane_type_id      INT       NOT NULL,
    departure_locality_id INT       NOT NULL,
    arrival_locality_id   INT       NOT NULL,
    travel_time           TIMESTAMP NOT NULL,
    ticket_price          FLOAT     NOT NULL,
    min_passengers_number INT       NOT NULL,
    airline_id            INT       NOT NULL,
    flight_category_id    INT       NOT NULL,
    constraint FLIGHT_PK PRIMARY KEY (flight_id),
    constraint check_flight_ticket_price CHECK ( ticket_price >= 0),
    constraint check_flight_min_passengers_number CHECK ( min_passengers_number >= 0)
)