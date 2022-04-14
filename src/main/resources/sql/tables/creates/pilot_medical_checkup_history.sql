CREATE TABLE PILOT_MEDICAL_CHECKUP_HISTORY
(
    pilot_id               INT                                                  NOT NULL,
    medical_checkup_date   DATE                                                 NOT NULL,
    medical_checkup_result CHAR(1) CHECK (medical_checkup_result IN ('N', 'Y')) NOT NULL,
    constraint PILOT_MEDICAL_CHECKUP_HISTORY_PK PRIMARY KEY (pilot_id, medical_checkup_date)
);