CREATE TABLE SPECIALTY
(
    specialty_id   INT                       NOT NULL,
    specialty_name VARCHAR2(255 CHAR) UNIQUE NOT NULL,
    constraint SPECIALTY_PK PRIMARY KEY (specialty_id)
);