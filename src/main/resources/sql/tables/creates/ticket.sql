CREATE TABLE TICKET
(
    departure_id     INT NOT NULL,
    seat             INT NOT NULL,
    ticket_status_id INT NOT NULL,
    bag_max_capacity INT,
    constraint TICKET_PK PRIMARY KEY (departure_id, seat),
    constraint CHECK_TICKET_SEAT CHECK ( seat > 0),
    constraint CHECK_BAG_MAX_CAPACITY CHECK ( bag_max_capacity >= 0)
);