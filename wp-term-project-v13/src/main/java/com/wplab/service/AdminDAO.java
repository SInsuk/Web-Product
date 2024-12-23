package com.wplab.service;

import java.sql.SQLException;

public interface AdminDAO {
    AdminDO getAdminByUsernameAndPassword(String admin_id, String password) throws SQLException;
}
