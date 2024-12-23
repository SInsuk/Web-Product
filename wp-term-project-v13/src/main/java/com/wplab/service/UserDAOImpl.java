package com.wplab.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends DBConnect implements UserDAO {

    private final String ADD_SQL = 
            "INSERT INTO \"User\" (user_id, password, email) VALUES (?, ?, ?)";
    private final String UPDATE_SQL = 
            "UPDATE \"User\" SET PASSWORD = ?, EMAIL = ? WHERE USER_ID = ?";
    private final String DeletAttach =
    		"DELETE FROM ATTACHMENT WHERE POST_ID IN (SELECT POST_ID FROM \"Post\" WHERE USER_ID = ?)";
    private final String DeletePost =
    		"DELETE FROM \"Post\" WHERE USER_ID = ?";
    private final String DeleteUser =
    		"DELETE FROM \"User\" WHERE USER_ID = ?";
    private final String GET_SQL = 
            "SELECT * FROM \"User\" WHERE USER_ID = ?";
    private final String LIST_SQL = 
            "SELECT * FROM \"User\" order by USER_ID asc";
    private final String LOGIN_SQL =
            "SELECT * FROM \"User\" WHERE USER_ID = ? AND PASSWORD = ?";
    private final String CHECK_USER_EXIST_SQL =
            "SELECT COUNT(*) FROM \"User\" WHERE USER_ID = ?";
    private final String CHECK_EMAIL_EXIST_SQL = 
    		"SELECT COUNT(*) FROM \"User\" WHERE EMAIL = ?";
    private final String CHECK_ADMIN_EXIST_SQL =
            "SELECT COUNT(*) FROM \"ADMIN\" WHERE ADMIN_ID = ?";
    private final String AdminEmailExist =
    		"SELECT COUNT(*) FROM \"ADMIN\" WHERE EMAIL = ?";
    private final String EmailExistExceptUser=
    		"SELECT COUNT(*) FROM \"User\" WHERE EMAIL = ? AND USER_ID != ?";
    public UserDAOImpl() {
    }

    public UserDAOImpl(DBConnectionInfo dbInfo) {
        super(dbInfo);
    }

    @Override
    public int addUser(UserDO user) {
        int result = 0;

        try {
            connect();

            stmt = conn.prepareStatement(ADD_SQL);

            stmt.setString(1, user.getUser_id());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public int updateUser(UserDO user) {
        int result = 0;

        try {
            connect();

            stmt = conn.prepareStatement(UPDATE_SQL);

            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getUser_id());

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public int deleteUser(UserDO user) {
        int result = 0;

        try {
            connect();

            conn.setAutoCommit(false); // 트랜잭션 시작

            // 먼저 사용자가 작성한 첨부파일 삭제
            stmt = conn.prepareStatement(DeletAttach);
            stmt.setString(1, user.getUser_id());
            stmt.executeUpdate();

            // 사용자가 작성한 게시글 삭제
            stmt = conn.prepareStatement(DeletePost);
            stmt.setString(1, user.getUser_id());
            stmt.executeUpdate();

            // 사용자를 삭제
            stmt = conn.prepareStatement(DeleteUser);
            stmt.setString(1, user.getUser_id());

            result = stmt.executeUpdate();

            conn.commit(); // 트랜잭션 커밋
        } catch (SQLException e) {
            try {
                conn.rollback(); // 롤백
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public UserDO getUser(UserDO user) {
        UserDO result = null;

        try {
            connect();

            stmt = conn.prepareStatement(GET_SQL);

            stmt.setString(1, user.getUser_id());

            rs = stmt.executeQuery();

            if (rs.next()) {
                result = new UserDO();

                result.setUser_id(rs.getString("USER_ID"));
                result.setPassword(rs.getString("PASSWORD"));
                result.setEmail(rs.getString("EMAIL"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public List<UserDO> ListUser() {
        List<UserDO> userList = null;

        try {
            connect();

            stmt = conn.prepareStatement(LIST_SQL);

            rs = stmt.executeQuery();

            if (rs.isBeforeFirst()) {
                userList = new ArrayList<>();

                while (rs.next()) {
                    UserDO user = new UserDO();

                    user.setUser_id(rs.getString("USER_ID"));
                    user.setPassword(rs.getString("PASSWORD"));
                    user.setEmail(rs.getString("EMAIL"));

                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userList;
    }

    @Override
    public UserDO getUserByUsernameAndPassword(String user_id, String password) {
        UserDO user = null;

        try {
            connect();

            stmt = conn.prepareStatement(LOGIN_SQL);

            stmt.setString(1, user_id);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new UserDO();
                user.setUser_id(rs.getString("USER_ID"));
                user.setPassword(rs.getString("PASSWORD"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }


    @Override
    public boolean isUserIdExist(String userId) {
        boolean exists = false;

        try {
            connect();

            stmt = conn.prepareStatement(CHECK_USER_EXIST_SQL);

            stmt.setString(1, userId);

            rs = stmt.executeQuery();

            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }

    public boolean isAdminIdExist(String adminId) {
        boolean exists = false;

        try {
            connect();

            stmt = conn.prepareStatement(CHECK_ADMIN_EXIST_SQL);

            stmt.setString(1, adminId);

            rs = stmt.executeQuery();

            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }
    
    
    @Override
    public boolean isEmailExist(String email) {
        boolean exists = false;
        try {
            connect();
            stmt = conn.prepareStatement(CHECK_EMAIL_EXIST_SQL);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exists;
    }

    @Override
    public boolean isAdminEmailExist(String email) {
        boolean exists = false;

        try {
            connect();
            stmt = conn.prepareStatement(AdminEmailExist);
            stmt.setString(1, email);

            rs = stmt.executeQuery();

            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }
    
    
    @Override
    public boolean isEmailExistExceptUser(String email, String userId) {
        boolean exists = false;
        
        try {
            connect();
            stmt = conn.prepareStatement(EmailExistExceptUser);
            stmt.setString(1, email);
            stmt.setString(2, userId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exists;
    }



}