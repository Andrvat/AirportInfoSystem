CREATE TABLE SERVICE_WORK_DIRECTION
(
    work_direction_id INT                       NOT NULL,
    direction_name    VARCHAR2(255 CHAR) UNIQUE NOT NULL,
    constraint SERVICE_WORK_DIRECTION_PK PRIMARY KEY (work_direction_id)
);