CREATE TABLE APPLICATION_ACCOUNT
(
    LOGIN    VARCHAR2(255 CHAR) PRIMARY KEY,
    PASSWORD VARCHAR2(255 CHAR) NOT NULL,
    ROLE     VARCHAR2(255 CHAR) NOT NULL
);