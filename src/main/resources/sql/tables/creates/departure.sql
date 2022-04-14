CREATE TABLE DEPARTURE
(
    departure_id        INT  NOT NULL,
    airplane_id         INT,
    flight_id           INT  NOT NULL,
    departure_date      DATE NOT NULL,
    arrival_date        DATE NOT NULL,
    departure_status_id INT  NOT NULL,
    constraint DEPARTURE_PK PRIMARY KEY (departure_id)
);