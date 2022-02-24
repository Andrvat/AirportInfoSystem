package controller;

import dbConnection.OracleDbConnector;
import lombok.Builder;

@Builder
public class ControllerManager {

    private final OracleDbConnector connector;

    public OracleDbConnector getConnector() {
        return connector;
    }
}
