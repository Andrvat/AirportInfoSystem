CREATE TABLE PASSENGER
(
    passenger_id           INT                               NOT NULL,
    surname                VARCHAR2(255 CHAR)                NOT NULL,
    name                   VARCHAR2(255 CHAR)                NOT NULL,
    patronymic             VARCHAR2(255 CHAR),
    sex                    CHAR(1) CHECK (sex IN ('N', 'Y')) NOT NULL,
    birth_date             DATE                              NOT NULL,
    passport               VARCHAR2(20 CHAR) UNIQUE          NOT NULL,
    international_passport VARCHAR2(20 CHAR) UNIQUE,
    custom_control         CHAR(1) CHECK (custom_control IN ('N', 'Y')),
    cargo                  CHAR(1 CHAR) CHECK (cargo IN ('N', 'Y')),
    constraint PASSENGER_PK PRIMARY KEY (passenger_id)
);