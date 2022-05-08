CREATE TABLE LOCATION
(
    location_id        INT                                              NOT NULL,
    country            VARCHAR2(255 CHAR)                               NOT NULL,
    location_name      VARCHAR2(255 CHAR)                               NOT NULL,
    weather_conditions CHAR(1) CHECK (weather_conditions IN ('N', 'Y')) NOT NULL,
    timezone           INT                                              NOT NULL,
    constraint LOCALITY_PK PRIMARY KEY (location_id),
    constraint CHECK_LOCATION_TIMEZONE CHECK ( timezone BETWEEN -12 AND 12)

);