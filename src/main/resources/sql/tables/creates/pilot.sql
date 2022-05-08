CREATE TABLE PILOT
(
    pilot_id              INT                                                 NOT NULL,
    medical_checkup_date  DATE                                                NOT NULL,
    professional_aptitude CHAR(1) CHECK (professional_aptitude IN ('N', 'Y')) NOT NULL,
    flight_hours          INT                                                 NOT NULL,
    eng_level_id          VARCHAR(2 CHAR)                                     NOT NULL,
    constraint PILOT_PK PRIMARY KEY (pilot_id),
    constraint CHECK_PILOT_FLIGHT_HOURS CHECK ( flight_hours >= 0)
);