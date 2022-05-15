create or replace PROCEDURE buy_ticket_by_id(in_passenger_id IN NUMBER, in_departure_id IN NUMBER, in_seat IN NUMBER)
    IS
BEGIN
    UPDATE ticket SET ticket_status_id = 3 WHERE departure_id = in_departure_id AND seat = in_seat;
    UPDATE ticket_status_history
    SET passenger_id = in_passenger_id
    WHERE departure_id = in_departure_id AND seat = in_seat AND ticket_status_id = 3
      AND status_set_date = (SELECT * FROM (SELECT status_set_date
                                            FROM ticket_status_history
                                            WHERE departure_id = in_departure_id AND seat = in_seat AND ticket_status_id = 3
                                            ORDER BY status_set_date DESC)
                             WHERE ROWNUM = 1);

END buy_ticket_by_id;