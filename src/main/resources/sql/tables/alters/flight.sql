ALTER TABLE FLIGHT
    ADD CONSTRAINT FLIGHT_fk0 FOREIGN KEY (airplane_type_id) REFERENCES AIRPLANE_TYPE (airplane_type_id);
ALTER TABLE FLIGHT
    ADD CONSTRAINT FLIGHT_fk1 FOREIGN KEY (departure_location_id) REFERENCES LOCATION (location_id);
ALTER TABLE FLIGHT
    ADD CONSTRAINT FLIGHT_fk2 FOREIGN KEY (arrival_location_id) REFERENCES LOCATION (location_id);
ALTER TABLE FLIGHT
    ADD CONSTRAINT FLIGHT_fk3 FOREIGN KEY (airline_id) REFERENCES AIRLINE (airline_id);
ALTER TABLE FLIGHT
    ADD CONSTRAINT FLIGHT_fk4 FOREIGN KEY (flight_category_id) REFERENCES FLIGHT_CATEGORY (flight_category_id);