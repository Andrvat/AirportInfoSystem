CREATE TABLE EMPLOYEE
(
    employee_id     INT                               NOT NULL,
    surname         VARCHAR2(255 CHAR) NOT NULL,
    name            VARCHAR2(255 CHAR) NOT NULL,
    patronymic      VARCHAR2(255 CHAR),
    sex             CHAR(1) CHECK (sex IN ('N', 'Y')) NOT NULL,
    university      VARCHAR2(255 CHAR) DEFAULT 'no education',
    birth_date      DATE                              NOT NULL,
    work_experience INT                               NOT NULL,
    employment_date DATE                              NOT NULL,
    salary          FLOAT                             NOT NULL,
    children_number INT                               NOT NULL,
    department_id   INT                               NOT NULL,
    crew_id         INT,
    specialty_id    INT                               NOT NULL,
    constraint EMPLOYEE_PK PRIMARY KEY (employee_id),
    constraint CHECK_EMPLOYEE_WORK_EXP CHECK ( work_experience >= 0),
    constraint CHECK_EMPLOYEE_SALARY CHECK ( salary >= 0),
    constraint CHECK_EMPLOYEE_CHILDREN CHECK ( children_number >= 0)
);