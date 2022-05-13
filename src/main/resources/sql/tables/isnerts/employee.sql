INSERT ALL
    -- Directors of each department
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Серов', 'Иван', 'Александрович', 'Y', TO_DATE('1966/03/23', 'yyyy/mm/dd'), 'НГУ', 23, TO_DATE('2013/05/15', 'yyyy/mm/dd'), 134500, 2, 1, NULL, 2)
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Меликов', 'Николай', 'Алексеевич', 'Y', TO_DATE('1970/05/22', 'yyyy/mm/dd'), 'МГУ', 17, TO_DATE('2016/01/26', 'yyyy/mm/dd'), 250000, 0, 2, NULL, 2)
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Русских', 'Степан', 'Егорович', 'Y', TO_DATE('1975/02/12', 'yyyy/mm/dd'), 'МФТИ', 18, TO_DATE('2014/10/06', 'yyyy/mm/dd'), 120000, 1, 3, NULL, 2)
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Нккрасов', 'Владимир', 'Олегович', 'Y', TO_DATE('1980/05/05', 'yyyy/mm/dd'), 'МГТУ', 21, TO_DATE('2015/09/07', 'yyyy/mm/dd'), 90000, 4, 4, NULL, 2)
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Перина', 'Алина', 'Даниловна', 'N', TO_DATE('1981/09/29', 'yyyy/mm/dd'), 'МГУ', 13, TO_DATE('2015/02/13', 'yyyy/mm/dd'), 170000, 1, 5, NULL, 2)

    -- Pilots
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Савостьянов', 'Александр', 'Дмитриевич', 'Y', TO_DATE('1994/01/09', 'yyyy/mm/dd'), 'СпбГУ', 3, TO_DATE('2015/11/03', 'yyyy/mm/dd'), 124400, 0, 4, 1, 1)
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Сидоров', 'Антон', 'Александрович', 'Y', TO_DATE('1989/10/19', 'yyyy/mm/dd'), 'ОмГУ', 8, TO_DATE('2015/04/01', 'yyyy/mm/dd'), 124300, 1, 4, 1, 1)

    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Легасов', 'Федор', 'Дмитриевич', 'Y', TO_DATE('1990/04/28', 'yyyy/mm/dd'), 'ОмГУ', 7, TO_DATE('2013/11/04', 'yyyy/mm/dd'), 99300, 1, 4, 2, 1)
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Пигасов', 'Владимир', 'Валерьевич', 'Y', TO_DATE('1985/07/23', 'yyyy/mm/dd'), 'НГУ', 15, TO_DATE('2013/12/22', 'yyyy/mm/dd'), 122000, 0, 4, 2, 1)

    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Молоков', 'Антон', 'Денисович', 'Y', TO_DATE('1987/07/10', 'yyyy/mm/dd'), 'НГУ', 9, TO_DATE('2014/01/25', 'yyyy/mm/dd'), 244000, 3, 5, 3, 1)
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Беспалов', 'Степан', 'Дмитриевич', 'Y', TO_DATE('1980/12/09', 'yyyy/mm/dd'), 'КФУ', 8, TO_DATE('2013/04/17', 'yyyy/mm/dd'), 145000, 1, 5, 3, 1)
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Ребров', 'Андрей', 'Олегович', 'Y', TO_DATE('1985/08/12', 'yyyy/mm/dd'), 'НГУ', 11, TO_DATE('2013/02/12', 'yyyy/mm/dd'), 143000, 2, 5, 3, 1)

    -- Technician
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Ребров', 'Антон', 'Андреевич', 'Y', TO_DATE('1979/02/07', 'yyyy/mm/dd'), 'НГТУ', 9, TO_DATE('2014/01/12', 'yyyy/mm/dd'), 142000, 2, 2, 4, 3)
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Кудинов', 'Сергей', 'Васильевич', 'Y', TO_DATE('1978/04/01', 'yyyy/mm/dd'), 'НГТУ', 17, TO_DATE('2014/05/13', 'yyyy/mm/dd'), 98000, 1, 2, 4, 3)

    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Мясоедов', 'Данила', 'Валерьевич', 'Y', TO_DATE('1969/10/09', 'yyyy/mm/dd'), 'ОмГТУ', 15, TO_DATE('2014/10/19', 'yyyy/mm/dd'), 91000, 0, 2, 5, 3)
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Богданов', 'Олег', 'Андреевич', 'Y', TO_DATE('1965/11/11', 'yyyy/mm/dd'), 'ТюмГУ', 22, TO_DATE('2013/08/22', 'yyyy/mm/dd'), 85000, 3, 2, 5, 3)

    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Погодин', 'Руслан', 'Владиславович', 'Y', TO_DATE('1991/11/29', 'yyyy/mm/dd'), 'СпбПУ', 12, TO_DATE('2013/08/02', 'yyyy/mm/dd'), 57000, 4, 2, 6, 3)
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Вставский', 'Владислав', 'Романович', 'Y', TO_DATE('1973/12/27', 'yyyy/mm/dd'), 'СпбГУ', 12, TO_DATE('2015/04/04', 'yyyy/mm/dd'), 79000, 3, 2, 6, 3)

    -- Dispatchers
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Иванченко', 'Сергей', 'Викторович', 'Y', TO_DATE('1971/10/04', 'yyyy/mm/dd'), 'ИТМО', 18, TO_DATE('2013/05/06', 'yyyy/mm/dd'), 55000, 1, 3, 7, 4)

    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Лебедев', 'Андрей', 'Владимирович', 'Y', TO_DATE('1983/07/06', 'yyyy/mm/dd'), 'ПариТех', 15, TO_DATE('2013/04/28', 'yyyy/mm/dd'), 61000, 0, 3, 8, 4)

    -- Cashiers
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Иванова', 'Анна', 'Андреевна', 'N', TO_DATE('1995/11/14', 'yyyy/mm/dd'), 3, TO_DATE('2018/12/20', 'yyyy/mm/dd'), 35000, 0, 1, NULL, 5)

    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Безрукова', 'Елена', 'Александровна', 'N', TO_DATE('1992/10/01', 'yyyy/mm/dd'), 6, TO_DATE('2017/10/21', 'yyyy/mm/dd'), 38000, 1, 1, NULL, 5)
    
    -- Security officers
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Фролова', 'Виктория', 'Даниловна', 'N', TO_DATE('1981/06/10', 'yyyy/mm/dd'), 'НГУ', 9, TO_DATE('2013/10/12', 'yyyy/mm/dd'), 80000, 1, 1, NULL, 6)

    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Антонов', 'Федор', 'Дмитриевич', 'Y', TO_DATE('1983/07/22', 'yyyy/mm/dd'), 'НГУ', 11, TO_DATE('2014/06/09', 'yyyy/mm/dd'), 63900, 2, 1, NULL, 6)
    
    -- Operators
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, university, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Антонова', 'Татьяна', 'Дмитриевна', 'N', TO_DATE('2001/10/04', 'yyyy/mm/dd'), 'НГУ', 2, TO_DATE('2019/08/22', 'yyyy/mm/dd'), 25000, 0, 1, NULL, 7)

    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
        VALUES ('Прокопенко', 'Валерия', 'Руслановна', 'N', TO_DATE('1990/03/10', 'yyyy/mm/dd'), 5, TO_DATE('2015/05/17', 'yyyy/mm/dd'), 29500, 0, 1, NULL, 7)
    
    -- Service persons
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
    VALUES ('Пегасова', 'Екатерина', 'Олеговна', 'N', TO_DATE('2000/05/04', 'yyyy/mm/dd'), 5, TO_DATE('2013/07/02', 'yyyy/mm/dd'), 30000, 1, 4, 9, 8)
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
    VALUES ('Пальчикова', 'Полина', 'Александровна', 'N', TO_DATE('1999/06/11', 'yyyy/mm/dd'), 7, TO_DATE('2014/04/10', 'yyyy/mm/dd'), 29000, 4, 4, 9, 8)
    
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
    VALUES ('Романова', 'Дарья', 'Андреевна', 'N', TO_DATE('1990/11/10', 'yyyy/mm/dd'), 11, TO_DATE('2013/10/19', 'yyyy/mm/dd'), 31200, 6, 4, 10, 8)
    INTO EMPLOYEE (surname, name, patronymic, sex, birth_date, work_experience, employment_date, salary, children_number, department_id, crew_id, specialty_id)
    VALUES ('Пауз', 'Анна', 'Руслановна', 'N', TO_DATE('1979/04/15', 'yyyy/mm/dd'), 12, TO_DATE('2013/06/05', 'yyyy/mm/dd'), 29000, 2, 4, 10, 8)
SELECT 1 FROM DUAL;