CREATE TABLE FLIGHT_CATEGORY
(
    flight_category_id INT                       NOT NULL,
    category_name      VARCHAR2(255 CHAR) UNIQUE NOT NULL,
    constraint FLIGHT_CATEGORY_PK PRIMARY KEY (flight_category_id)
);