ALTER TABLE FLIGHT_DAY
    ADD CONSTRAINT FLIGHT_DAY_FK0 FOREIGN KEY (flight_id) REFERENCES FLIGHT (flight_id);