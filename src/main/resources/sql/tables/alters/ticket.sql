ALTER TABLE TICKET
    ADD CONSTRAINT TICKET_fk0 FOREIGN KEY (departure_id) REFERENCES DEPARTURE (departure_id);
ALTER TABLE TICKET
    ADD CONSTRAINT TICKET_fk1 FOREIGN KEY (ticket_status_id) REFERENCES TICKET_STATUS (ticket_status_id);
ALTER TABLE TICKET
    ADD CONSTRAINT TICKET_fk2 FOREIGN KEY (passenger_id) REFERENCES PASSENGER (passenger_id);