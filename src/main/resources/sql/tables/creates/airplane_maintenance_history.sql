CREATE TABLE AIRPLANE_MAINTENANCE_HISTORY
(
    record_id            INT                                                NOT NULL,
    maintenance_type_id  INT                                                NOT NULL,
    airplane_id          INT                                                NOT NULL,
    maintenance_date     DATE                                               NOT NULL,
    crew_id              INT                                                NOT NULL,
    final_serviceability CHAR(1) CHECK (final_serviceability IN ('N', 'Y')) NOT NULL,
    fuel_filled_amount   FLOAT                                              NOT NULL,
    constraint AIRPLANE_MAINTENANCE_HISTORY_PK PRIMARY KEY (record_id),
    constraint check_airplane_fuel_filled_amount CHECK ( fuel_filled_amount >= 0)
)