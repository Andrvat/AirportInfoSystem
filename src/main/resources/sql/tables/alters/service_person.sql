ALTER TABLE SERVICE_PERSON
    ADD CONSTRAINT SERVICE_PERSON_fk0 FOREIGN KEY (service_person_id) REFERENCES EMPLOYEE (employee_id);
ALTER TABLE SERVICE_PERSON
    ADD CONSTRAINT SERVICE_PERSON_fk1 FOREIGN KEY (service_work_direction_id) REFERENCES SERVICE_WORK_DIRECTION (work_direction_id);