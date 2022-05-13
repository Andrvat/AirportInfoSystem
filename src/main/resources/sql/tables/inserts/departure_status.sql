INSERT ALL
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Идет регистрация', NULL)
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Идет посадка', NULL)
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Посадка завершена', NULL)
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Вылетел', NULL)
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Отменен', 'Плохие погодные условия')
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Отменен', 'Военный конфликт')
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Задержан', 'Плохие погодные условия')
    INTO DEPARTURE_STATUS (description, reason) VALUES ('Задержан', 'Наложение в расписании')
SELECT 1 FROM DUAL;