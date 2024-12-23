package com.wplab.service;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    int addUser(UserDO user);
    int updateUser(UserDO user);
    int deleteUser(UserDO user);
    UserDO getUser(UserDO user);
    List<UserDO> ListUser();
    UserDO getUserByUsernameAndPassword(String user_id, String password);
    boolean isUserIdExist(String userId);
    boolean isEmailExist(String email);
	boolean isAdminIdExist(String user_id);
	boolean isAdminEmailExist(String email);
	boolean isEmailExistExceptUser(String email, String user_id);
}
