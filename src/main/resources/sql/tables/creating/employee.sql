CREATE TABLE Employee (
    Id INT NOT NULL,
    Surname VARCHAR2(255) NOT NULL,
    Name VARCHAR2(255) NOT NULL,
    Patronymic VARCHAR2(255) NOT NULL,
    Sex CHAR(1) CHECK (Sex IN ('N','Y')) NOT NULL,
    BirthdayDate DATE NOT NULL,
    EmploymentDate DATE NOT NULL,
    Salary FLOAT NOT NULL,
    ChildrenNumber INT NOT NULL,
    DepartmentId INT NOT NULL,
    CrewId INT,
    constraint Employee_PK PRIMARY KEY (Id))