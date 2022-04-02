CREATE TABLE TICKET_STATUS_HISTORY
(
    record_id             INT  NOT NULL,
    departure_id          INT  NOT NULL,
    seat                  INT  NOT NULL,
    ticket_status_id      INT  NOT NULL,
    record_formation_date DATE NOT NULL,
    constraint TICKET_STATUS_HISTORY_PK PRIMARY KEY (record_id),
    constraint check_ticket_status_history_seat CHECK ( seat > 0)
)