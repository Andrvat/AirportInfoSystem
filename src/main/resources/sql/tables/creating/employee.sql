CREATE TABLE EMPLOYEE
(
    id              INT                               NOT NULL,
    surname         VARCHAR2(255) NOT NULL,
    name            VARCHAR2(255) NOT NULL,
    patronymic      VARCHAR2(255) NOT NULL,
    sex             CHAR(1) CHECK (SEX IN ('N', 'Y')) NOT NULL,
    birth_date      DATE                              NOT NULL,
    employment_date DATE                              NOT NULL,
    salary          FLOAT                             NOT NULL,
    children_number INT                               NOT NULL,
    department_id   INT                               NOT NULL,
    crew_id         INT,
    CONSTRAINT EMPLOYEE_PK PRIMARY KEY (id),
    CONSTRAINT EMPLOYEE_FK0 FOREIGN KEY (department_id) REFERENCES DEPARTMENT (id),
    CONSTRAINT EMPLOYEE_FK1 FOREIGN KEY (crew_id) REFERENCES CREW (id)
)