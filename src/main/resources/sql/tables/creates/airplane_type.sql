CREATE TABLE AIRPLANE_TYPE
(
    airplane_type_id INT                       NOT NULL,
    type_name        VARCHAR2(255 CHAR) UNIQUE NOT NULL,
    people_capacity  INT                       NOT NULL,
    cargo_capacity   FLOAT                     NOT NULL,
    CONSTRAINT AIRPLANE_TYPE_PK PRIMARY KEY (airplane_type_id),
    CONSTRAINT CHECK_AIRPLANE_PEOPLE_CAPACITY CHECK ( people_capacity >= 0),
    CONSTRAINT CHECK_AIRPLANE_CARGO_CAPACITY CHECK ( cargo_capacity >= 0)
);