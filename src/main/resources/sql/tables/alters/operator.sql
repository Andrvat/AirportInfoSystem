ALTER TABLE OPERATOR
    ADD CONSTRAINT OPERATOR_FK0 FOREIGN KEY (operator_Id) REFERENCES EMPLOYEE (employee_id);
ALTER TABLE OPERATOR
    ADD CONSTRAINT OPERATOR_FK1 FOREIGN KEY (operator_work_direction_id) REFERENCES OPERATOR_WORK_DIRECTION (work_direction_id);