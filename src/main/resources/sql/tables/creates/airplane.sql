CREATE TABLE AIRPLANE
(
    airplane_id         INT   NOT NULL,
    airplane_type_id    INT   NOT NULL,
    manufacture_year    INT   NOT NULL,
    pilot_crew_id       INT   NOT NULL,
    technical_crew_id   INT   NOT NULL,
    service_crew_id     INT   NOT NULL,
    airport_locality_id INT   NOT NULL,
    airline_id          INT   NOT NULL,
    constraint AIRPLANE_PK PRIMARY KEY (airplane_id),
    constraint check_airplane_manufacture_year CHECK ( manufacture_year BETWEEN 1900 AND 2022),
    constraint check_airplane_load_capacity CHECK ( manufacture_year >= 0)
);