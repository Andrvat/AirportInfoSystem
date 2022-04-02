CREATE TABLE AIRPLANE_SERVICE_HISTORY
(
    record_id                INT                                                    NOT NULL,
    airplane_id              INT                                                    NOT NULL,
    salon_cleaning           CHAR(1) CHECK (salon_cleaning IN ('N', 'Y'))           NOT NULL,
    products_stock_refilling CHAR(1) CHECK (products_stock_refilling IN ('N', 'Y')) NOT NULL,
    service_date             DATE                                                   NOT NULL,
    constraint AIRPLANE_SERVICE_HISTORY_PK PRIMARY KEY (record_id)
)