package com.wplab.service;

import java.sql.*;

public class DBConnect {
    private DBConnectionInfo dbInfo;
    protected Connection conn = null;
    protected PreparedStatement stmt = null;
    protected ResultSet rs = null;

    public DBConnect() {
    }

    public DBConnect(DBConnectionInfo dbInfo) {
        this.dbInfo = dbInfo;
    }

    public void setDbInfo(DBConnectionInfo dbInfo) {
        this.dbInfo = dbInfo;
    }

    protected void connect() throws SQLException {
        if (conn == null || conn.isClosed()) {
            // 1. load JDBC driver class
            try {
                if (dbInfo == null) {
                    throw new SQLException("DBConnectionInfo is not set.");
                }
                Class.forName(dbInfo.getDriverClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException("Driver class not found", e);
            }
            // 2. create DB connection
            conn = DriverManager.getConnection(
                dbInfo.getUrl(),
                dbInfo.getUsername(),
                dbInfo.getPassword());
        }
    }

    protected void disconnect() throws SQLException {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }
}
