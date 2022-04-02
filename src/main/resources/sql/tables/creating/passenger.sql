CREATE TABLE PASSENGER
(
    passenger_id           INT                NOT NULL,
    surname                VARCHAR2(255 CHAR) NOT NULL,
    name                   VARCHAR2(255 CHAR) NOT NULL,
    patronymic             VARCHAR2(255 CHAR),
    passport               INT UNIQUE         NOT NULL,
    international_passport INT UNIQUE,
    custom_control         CHAR(1) CHECK (custom_control IN ('N', 'Y')),
    constraint PASSENGER_PK PRIMARY KEY (passenger_id),
    constraint check_passenger_passport CHECK ( passport > 0),
    constraint check_passenger_international_passport CHECK ( international_passport > 0)
)