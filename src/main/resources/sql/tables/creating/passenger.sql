CREATE TABLE PASSENGER
(
    id                     INT           NOT NULL,
    surname                VARCHAR2(255) NOT NULL,
    name                   VARCHAR2(255) NOT NULL,
    patronymic             VARCHAR2(255) NOT NULL,
    passport               INT UNIQUE    NOT NULL,
    international_passport INT UNIQUE,
    custom_control         CHAR(1) CHECK (custom_control IN ('N', 'Y')),
    constraint PASSENGER_PK PRIMARY KEY (id)
)