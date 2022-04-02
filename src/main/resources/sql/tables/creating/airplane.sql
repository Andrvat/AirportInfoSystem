CREATE TABLE AIRPLANE
(
    airplane_id         INT   NOT NULL,
    airplane_type_id    INT   NOT NULL,
    manufacture_year    INT   NOT NULL,
    pilot_crew_id       INT   NOT NULL,
    technical_crew_id   INT   NOT NULL,
    service_crew_id     INT   NOT NULL,
    load_capacity       FLOAT NOT NULL,
    airport_locality_id INT   NOT NULL,
    airline_id          INT   NOT NULL,
    constraint AIRPLANE_PK PRIMARY KEY (airplane_id)
);