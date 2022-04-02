ALTER TABLE AIRPLANE
    ADD CONSTRAINT AIRPLANE_fk0 FOREIGN KEY (airplane_type_id) REFERENCES AIRPLANE_TYPE (airplane_type_id);
ALTER TABLE AIRPLANE
    ADD CONSTRAINT AIRPLANE_fk1 FOREIGN KEY (pilot_crew_id) REFERENCES CREW (crew_id);
ALTER TABLE AIRPLANE
    ADD CONSTRAINT AIRPLANE_fk2 FOREIGN KEY (technical_crew_id) REFERENCES CREW (crew_id);
ALTER TABLE AIRPLANE
    ADD CONSTRAINT AIRPLANE_fk3 FOREIGN KEY (service_crew_id) REFERENCES CREW (crew_id);
ALTER TABLE AIRPLANE
    ADD CONSTRAINT AIRPLANE_fk4 FOREIGN KEY (airport_locality_id) REFERENCES LOCALITY (locality_id);
ALTER TABLE AIRPLANE
    ADD CONSTRAINT AIRPLANE_fk5 FOREIGN KEY (airline_id) REFERENCES AIRLINE (airline_id);
