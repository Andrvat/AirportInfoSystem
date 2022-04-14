CREATE TABLE DEPARTURE_STATUS
(
    departure_status_id INT                       NOT NULL,
    description         VARCHAR2(255 CHAR) UNIQUE NOT NULL,
    reason              VARCHAR2(255 CHAR),
    constraint DEPARTURE_STATUS_PK PRIMARY KEY (departure_status_id)
);