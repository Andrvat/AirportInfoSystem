CREATE TABLE AIRPLANE
(
    airplane_id         INT   NOT NULL,
    airplane_type_id    INT   NOT NULL,
    manufacture_year    INT   NOT NULL,
    pilot_crew_id       INT   NOT NULL,
    technical_crew_id   INT   NOT NULL,
    service_crew_id     INT   NOT NULL,
    airport_location_id INT   NOT NULL,
    constraint AIRPLANE_PK PRIMARY KEY (airplane_id),
    constraint CHECK_AIRPLANE_YEAR CHECK ( manufacture_year BETWEEN 1900 AND 2022)
);