package com.wplab.service;

import java.sql.*;

public class AdminDAOImpl extends DBConnect implements AdminDAO {
    private final String GET_ADMIN_SQL = "SELECT * FROM ADMIN WHERE ADMIN_ID = ? AND PASSWORD = ?";

    public AdminDAOImpl() {
    }

    public AdminDAOImpl(DBConnectionInfo dbInfo) {
        super(dbInfo);
    }

    @Override
    public AdminDO getAdminByUsernameAndPassword(String admin_id, String password) throws SQLException {
        AdminDO admin = null;

        try {
            connect();
            stmt = conn.prepareStatement(GET_ADMIN_SQL);
            stmt.setString(1, admin_id);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new AdminDO();
                admin.setAdmin_id(rs.getString("ADMIN_ID"));
                admin.setPassword(rs.getString("PASSWORD"));
                admin.setEmail(rs.getString("EMAIL"));
            }
        } finally {
            disconnect();
        }

        return admin;
    }
}
