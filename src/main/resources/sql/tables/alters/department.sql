ALTER TABLE DEPARTMENT
    ADD CONSTRAINT DEPARTMENT_fk0 FOREIGN KEY (director_id) REFERENCES EMPLOYEE (employee_id);