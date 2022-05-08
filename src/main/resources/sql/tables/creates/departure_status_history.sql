CREATE TABLE DEPARTURE_STATUS_HISTORY
(
    record_id       INT  NOT NULL,
    departure_id    INT  NOT NULL,
    status_id       INT  NOT NULL,
    status_set_date DATE NOT NULL,
    constraint DEPARTURE_STATUS_HISTORY_PK PRIMARY KEY (record_id)
)
