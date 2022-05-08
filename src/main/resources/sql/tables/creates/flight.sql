CREATE TABLE FLIGHT
(
    flight_id             INT   NOT NULL,
    airplane_type_id      INT   NOT NULL,
    departure_location_id INT   NOT NULL,
    arrival_location_id   INT   NOT NULL,
    travel_time           DATE  NOT NULL,
    ticket_price          FLOAT NOT NULL,
    min_passengers_number INT   NOT NULL,
    airline_id            INT   NOT NULL,
    flight_category_id    INT   NOT NULL,
    constraint FLIGHT_PK PRIMARY KEY (flight_id),
    constraint CHECK_FLIGHT_TICKET_PRICE CHECK ( ticket_price >= 0),
    constraint CHECK_FLIGHT_MIN_PASSENGERS CHECK ( min_passengers_number >= 0)
);