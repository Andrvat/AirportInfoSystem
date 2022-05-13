CREATE TABLE DEPARTMENT
(
    department_id   INT                       NOT NULL,
    department_name VARCHAR2(255 CHAR) UNIQUE NOT NULL,
    constraint DEPARTMENT_PK PRIMARY KEY (department_id)
);