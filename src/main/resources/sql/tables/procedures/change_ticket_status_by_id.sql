create or replace PROCEDURE change_ticket_status_by_id(in_passenger_id IN NUMBER, in_departure_id IN NUMBER, in_seat IN NUMBER, in_ticket_status_id IN NUMBER)
    IS
BEGIN
    UPDATE ticket SET ticket_status_id = in_ticket_status_id WHERE departure_id = in_departure_id AND seat = in_seat;
    UPDATE ticket_status_history
    SET passenger_id = in_passenger_id
    WHERE departure_id = in_departure_id AND seat = in_seat AND ticket_status_id = in_ticket_status_id
      AND status_set_date = (SELECT * FROM (SELECT status_set_date
                                            FROM ticket_status_history
                                            WHERE departure_id = in_departure_id AND seat = in_seat AND ticket_status_id = in_ticket_status_id
                                            ORDER BY status_set_date DESC)
                             WHERE ROWNUM = 1);

END change_ticket_status_by_id;