CREATE TABLE MAINTENANCE_TYPE
(
    maintenance_type_id INT                       NOT NULL,
    description         VARCHAR2(255 CHAR) UNIQUE NOT NULL,
    constraint MAINTENANCE_TYPE_PK PRIMARY KEY (maintenance_type_id)
)