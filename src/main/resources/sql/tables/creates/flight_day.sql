CREATE TABLE FLIGHT_DAY
(
    record_id      INT  NOT NULL,
    flight_id      INT  NOT NULL,
    week_day       INT  NOT NULL,
    departure_time DATE NOT NULL,
    constraint FLIGHT_DAY_PK PRIMARY KEY (record_id),
    constraint CHECK_FLIGHT_DAY CHECK ( week_day IN (1, 2, 3, 4, 5, 6, 7))
);