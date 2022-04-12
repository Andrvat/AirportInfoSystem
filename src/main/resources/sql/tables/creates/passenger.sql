CREATE TABLE PASSENGER
(
    passenger_id           INT                       NOT NULL,
    surname                VARCHAR2(255 CHAR)        NOT NULL,
    name                   VARCHAR2(255 CHAR)        NOT NULL,
    patronymic             VARCHAR2(255 CHAR),
    passport               VARCHAR2(255 CHAR) UNIQUE NOT NULL,
    international_passport VARCHAR2(255 CHAR) UNIQUE,
    custom_control         CHAR(1) CHECK (custom_control IN ('N', 'Y')),
    birth_date             DATE                      NOT NULL,
    constraint PASSENGER_PK PRIMARY KEY (passenger_id)
)