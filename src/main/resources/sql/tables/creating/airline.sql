CREATE TABLE AIRLINE
(
    airline_id INT                       NOT NULL,
    name       VARCHAR2(255 CHAR) UNIQUE NOT NULL,
    constraint AIRLINE_PK PRIMARY KEY (airline_id)
);