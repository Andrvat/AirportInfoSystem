CREATE TABLE TICKET
(
    departure_id     INT NOT NULL,
    seat             INT NOT NULL,
    ticket_status_id INT NOT NULL,
    passenger_id     CHAR(1) CHECK (passenger_id IN ('N', 'Y')),
    bag_max_capacity INT,
    constraint TICKET_PK PRIMARY KEY (departure_id, seat)
)