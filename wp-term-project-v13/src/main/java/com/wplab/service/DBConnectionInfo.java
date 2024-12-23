package com.wplab.service;

public class DBConnectionInfo {
    private static final String DRIVER_CLASS_NAME = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public String getDriverClassName() {
        return DRIVER_CLASS_NAME;
    }

    public String getUrl() {
        return DB_URL;
    }

    public String getUsername() {
        return DB_USER;
    }

    public String getPassword() {
        return DB_PASSWORD;
    }
}
