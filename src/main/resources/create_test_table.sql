CREATE TABLE ticket(
   id NUMBER(5),
   owner_name VARCHAR2(50 CHAR),
   owner_last_name VARCHAR2(50 CHAR),
   owner_patronymic VARCHAR2(50 CHAR),
   purchase_date DATE,
   departure_place VARCHAR2(100 CHAR),
   arrival_place VARCHAR2(100 CHAR),
   flight_id NUMBER(5),
   total_price NUMBER(10)
)