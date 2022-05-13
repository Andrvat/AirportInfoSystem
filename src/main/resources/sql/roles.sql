DROP ROLE c##admin;
CREATE ROLE c##admin;

GRANT ALL PRIVILEGES TO c##admin;


DROP ROLE c##airport_manager;
CREATE ROLE c##airport_manager;

GRANT SELECT ON TICKET_STATUS TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON AIRPLANE_MAINTENANCE_HISTORY TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON ENG_LEVEL TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON TECHNICIAN TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON SECURITY_OFFICER TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON DISPATCHER_WORK_DIRECTION TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON DEPARTURE_STATUS TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON DEPARTMENT TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON AIRPLANE TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON SPECIALIZATION TO c##airport_manager;
GRANT SELECT ON FLIGHT TO c##airport_manager;
GRANT SELECT ON AIRLINE TO c##airport_manager;
GRANT SELECT ON TICKET TO c##airport_manager;
GRANT SELECT ON LOCATION TO c##airport_manager;
GRANT SELECT ON FLIGHT_DAY TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON DIRECTOR TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON AIRPLANE_SERVICE_HISTORY TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON PILOT_MEDICAL_CHECKUP_HISTORY TO c##airport_manager;
GRANT SELECT ON DEPARTURE TO c##airport_manager;
GRANT SELECT ON DEPARTURE_STATUS_HISTORY TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON EMPLOYEE TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON PILOT TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON CREW TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON OPERATOR_WORK_DIRECTION TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON CASHIER_WORK_DIRECTION TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON MAINTENANCE_TYPE TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON OPERATOR TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON SECURITY_WORK_DIRECTION TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON DISPATCHER TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON TECHNICIAN_WORK_DIRECTION TO c##airport_manager;
GRANT SELECT, INSERT, DELETE, UPDATE ON CASHIER TO c##airport_manager;


DROP ROLE c##passenger;
CREATE ROLE c##passenger;

GRANT SELECT, INSERT, UPDATE ON TICKET_STATUS TO c##passenger;
GRANT SELECT, INSERT, UPDATE ON PASSENGER TO c##passenger;
GRANT SELECT ON DEPARTURE_STATUS TO c##passenger;
GRANT SELECT ON FLIGHT TO c##passenger;
GRANT SELECT ON AIRLINE TO c##passenger;
GRANT SELECT ON TICKET TO c##passenger;
GRANT SELECT ON LOCATION TO c##passenger;
GRANT SELECT ON FLIGHT_DAY TO c##passenger;
GRANT SELECT ON DEPARTURE TO c##passenger;
GRANT SELECT ON DEPARTURE_STATUS_HISTORY TO c##passenger;
GRANT SELECT ON AIRPLANE_TYPE TO c##passenger;


DROP ROLE c##airline;
CREATE ROLE c##airline;

GRANT SELECT, INSERT, DELETE, UPDATE ON TICKET_STATUS TO c##airline;
GRANT SELECT ON PASSENGER TO c##airline;
GRANT SELECT ON AIRPLANE_MAINTENANCE_HISTORY TO c##airline;
GRANT SELECT, INSERT, DELETE, UPDATE ON DEPARTURE_STATUS TO c##airline;
GRANT SELECT, INSERT, DELETE, UPDATE ON AIRPLANE TO c##airline;
GRANT SELECT, INSERT, DELETE, UPDATE ON FLIGHT TO c##airline;
GRANT SELECT, INSERT, DELETE, UPDATE ON AIRLINE TO c##airline;
GRANT SELECT, INSERT, DELETE, UPDATE ON TICKET TO c##airline;
GRANT SELECT, INSERT, DELETE, UPDATE ON LOCATION TO c##airline;
GRANT SELECT, INSERT, DELETE, UPDATE ON FLIGHT_DAY TO c##airline;
GRANT SELECT ON AIRPLANE_SERVICE_HISTORY TO c##airline;
GRANT SELECT, INSERT, DELETE, UPDATE ON DEPARTURE TO c##airline;
GRANT SELECT, INSERT, DELETE, UPDATE ON DEPARTURE_STATUS_HISTORY TO c##airline;
GRANT SELECT ON MAINTENANCE_TYPE TO c##airline;
GRANT SELECT, INSERT, UPDATE ON AIRPLANE_TYPE TO c##airline;
GRANT SELECT ON FLIGHT_CATEGORY TO c##airline;

