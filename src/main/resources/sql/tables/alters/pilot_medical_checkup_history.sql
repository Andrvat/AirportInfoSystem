ALTER TABLE PILOT_MEDICAL_CHECKUP_HISTORY
    ADD CONSTRAINT PILOT_MEDICAL_CHECKUP_HISTORY_fk0 FOREIGN KEY (pilot_id) REFERENCES PILOT (pilot_id);