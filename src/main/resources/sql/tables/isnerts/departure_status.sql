INSERT ALL
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Registration is underway', NULL)
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Boarding is underway', NULL)
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Boarding completed', NULL)
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Flew out', NULL)
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Cancelled', 'Bad weather conditions')
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Cancelled', 'Military conflict')
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Detained', 'Bad weather conditions')
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Detained', 'Overlap in the schedule')
SELECT 1 FROM DUAL;