CREATE TABLE EMPLOYEE
(
    employee_id     INT                               NOT NULL,
    surname         VARCHAR2(255 CHAR) NOT NULL,
    name            VARCHAR2(255 CHAR) NOT NULL,
    patronymic      VARCHAR2(255 CHAR) NOT NULL,
    sex             CHAR(1) CHECK (sex IN ('N', 'Y')) NOT NULL,
    birth_date      DATE                              NOT NULL,
    education       VARCHAR2(255 CHAR) NOT NULL DEFAULT 'without education',
    work_experience INT                               NOT NULL,
    employment_date DATE                              NOT NULL,
    salary          FLOAT                             NOT NULL,
    children_number INT                               NOT NULL,
    department_id   INT                               NOT NULL,
    crew_id         INT,
    specialty_id    INT                               NOT NULL,
    constraint EMPLOYEE_PK PRIMARY KEY (employee_id),
    constraint check_employee_work_experience CHECK ( work_experience >= 0),
    constraint check_employee_salary CHECK ( salary >= 0),
    constraint check_employee_children_number CHECK ( children_number >= 0)
)