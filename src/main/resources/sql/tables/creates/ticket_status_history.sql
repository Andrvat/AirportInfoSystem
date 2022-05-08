CREATE TABLE TICKET_STATUS_HISTORY
(
    record_id        INT  NOT NULL,
    departure_id     INT  NOT NULL,
    seat             INT  NOT NULL,
    ticket_status_id INT  NOT NULL,
    status_set_date  DATE NOT NULL,
    passenger_id     INT,
    constraint TICKET_STATUS_HISTORY_PK PRIMARY KEY (record_id),
    constraint CHECK_TICKET_HISTORY_SEAT CHECK ( seat > 0)
);