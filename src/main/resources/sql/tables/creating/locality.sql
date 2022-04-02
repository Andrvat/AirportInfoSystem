CREATE TABLE LOCALITY
(
    locality_id        INT                                              NOT NULL,
    country            VARCHAR2(255 CHAR)                               NOT NULL,
    locality_name      VARCHAR2(255 CHAR)                               NOT NULL,
    weather_conditions CHAR(1) CHECK (weather_conditions IN ('N', 'Y')) NOT NULL,
    time_zone          INT                                              NOT NULL,
    constraint LOCALITY_PK PRIMARY KEY (locality_id)
)