CREATE TABLE DEPARTURE
(
    departure_id        INT  NOT NULL,
    airplane_id         INT,
    flight_id           INT  NOT NULL,
    departure_date      DATE NOT NULL,
    arrival_date        DATE NOT NULL,
    departure_status_id INT  NOT NULL,
    status_set_date     DATE NOT NULL,
    constraint DEPARTURE_PK PRIMARY KEY (departure_id),
    constraint CHECK_DEPARTURE_DATES CHECK (departure_date < arrival_date)

);