INSERT ALL
    INTO PASSENGER (surname, name, patronymic, sex, birth_date, passport, international_passport, custom_control, cargo)
        VALUES ('Серов', 'Данил', 'Иванович', 'Y', TO_DATE('1970/09/13', 'yyyy/mm/dd'), '3424 433419', '52 0022196', 'Y', 'Y')
    INTO PASSENGER (surname, name, patronymic, sex, birth_date, passport, international_passport, custom_control, cargo)
        VALUES ('Даниелян', 'Ахмед', NULL, 'Y', TO_DATE('1994/12/03', 'yyyy/mm/dd'), '1593 486534', NULL, NULL, 'Y')
    INTO PASSENGER (surname, name, patronymic, sex, birth_date, passport, international_passport, custom_control, cargo)
        VALUES ('Иванов', 'Александр', 'Владимирович', 'Y', TO_DATE('2001/02/15', 'yyyy/mm/dd'), '5935 495272', NULL, NULL, 'N')
    INTO PASSENGER (surname, name, patronymic, sex, birth_date, passport, international_passport, custom_control, cargo)
        VALUES ('Потапов', 'Сергей', 'Владимирович', 'Y', TO_DATE('1998/12/15', 'yyyy/mm/dd'), '4392 493554', NULL, NULL, 'Y')
    INTO PASSENGER (surname, name, patronymic, sex, birth_date, passport, international_passport, custom_control, cargo)
        VALUES ('Иванова', 'Лариса', 'Игоревна', 'N', TO_DATE('2000/04/10', 'yyyy/mm/dd'), '4343 323122', NULL, NULL, 'N')
    INTO PASSENGER (surname, name, patronymic, sex, birth_date, passport, international_passport, custom_control, cargo)
        VALUES ('Роберто', 'Джеймс', NULL, 'Y', TO_DATE('1995/01/27', 'yyyy/mm/dd'), '4234 432423', '42 4625884', 'N', 'Y')
SELECT 1 FROM DUAL;