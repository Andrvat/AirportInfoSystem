CREATE TABLE TICKET_STATUS
(
    ticket_status_id INT                       NOT NULL,
    description      VARCHAR2(255 CHAR) UNIQUE NOT NULL,
    constraint TICKET_STATUS_PK PRIMARY KEY (ticket_status_id)
);