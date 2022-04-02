CREATE TABLE AIRPLANE_TYPE
(
    airplane_type_id INT                       NOT NULL,
    type_name        VARCHAR2(255 CHAR) UNIQUE NOT NULL,
    people_capacity  INT                       NOT NULL,
    constraint AIRPLANE_TYPE_PK PRIMARY KEY (airplane_type_id),
    constraint check_airplane_people_capacity CHECK ( people_capacity >= 0)
);