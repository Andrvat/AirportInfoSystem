ALTER TABLE DEPARTURE
    ADD CONSTRAINT DEPARTURE_FK0 FOREIGN KEY (airplane_id) REFERENCES AIRPLANE (airplane_id);
ALTER TABLE DEPARTURE
    ADD CONSTRAINT DEPARTURE_FK1 FOREIGN KEY (flight_id) REFERENCES FLIGHT (flight_id);
ALTER TABLE DEPARTURE
    ADD CONSTRAINT DEPARTURE_FK2 FOREIGN KEY (departure_status_id) REFERENCES DEPARTURE_STATUS (departure_status_id);