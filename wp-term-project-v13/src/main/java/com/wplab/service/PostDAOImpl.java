package com.wplab.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl extends DBConnect implements PostDAO {

    private final String ADDPOST_SQL = 
            "INSERT INTO \"Post\" (TITLE, CONTENT, USER_ID) VALUES (?, ?, ?)";
    private final String ADDATTACHMENT_SQL = 
            "INSERT INTO ATTACHMENT (POST_ID, FILENAME, FILE_PATH) VALUES (?, ?, ?)";
    
    private final String UPDATEPOST_SQL = 
            "UPDATE \"Post\" SET TITLE = ?, CONTENT = ? WHERE POST_ID = ?";
    
    private final String DELETEATTACHMENT_SQL = 
            "DELETE FROM \"ATTACHMENT\" WHERE POST_ID = ?";
    private final String DELETEPOST_SQL = 
            "DELETE FROM \"Post\" WHERE POST_ID = ?";

    private final String GETPOSTBYID_SQL = 
            "SELECT * FROM \"Post\" WHERE POST_ID = ?";
    private final String GETATTACHMENTBYPOSTID_SQL =
            "SELECT * FROM \"ATTACHMENT\" WHERE POST_ID = ?";
    
    private final String GETALLPOST_SQL = 
            "SELECT * FROM \"Post\"";
    private final String SEARCHPOST_SQL = 
            "SELECT * FROM \"Post\" WHERE TITLE LIKE ? OR CONTENT LIKE ? OR USER_ID LIKE ?";

    private final String getTotalPostCountForKeyword =
    		"SELECT COUNT(*) FROM \"Post\" WHERE TITLE LIKE ? OR CONTENT LIKE ? OR USER_ID LIKE ?";
    private final String getPosts =
    		"SELECT * FROM \"Post\" LIMIT ? OFFSET ?";
    private final String TotalPostCount =
    		"SELECT COUNT(*) FROM \"Post\"";
    
    public PostDAOImpl() {
    }

    public PostDAOImpl(DBConnectionInfo dbInfo) {
        super(dbInfo);
    }

    @Override
    public void addPost(PostDO post, List<AttachmentDO> attachments) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connect();

            stmt = conn.prepareStatement(ADDPOST_SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getUser_id());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int postId = rs.getInt(1);
                saveAttachments(conn, postId, attachments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
    }

    private void saveAttachments(Connection conn, int postId, List<AttachmentDO> attachments) {
        PreparedStatement stmt = null;
        try {
            for (AttachmentDO attachment : attachments) {
                stmt = conn.prepareStatement(ADDATTACHMENT_SQL);
                stmt.setInt(1, postId);
                stmt.setString(2, attachment.getFileName());
                stmt.setString(3, attachment.getFilePath());
                stmt.executeUpdate();
                stmt.close();  // 중첩된 루프 내에서 자원 해제
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, null);
        }
    }

    @Override
    public int updatePost(PostDO post, List<AttachmentDO> attachments) {
        int result = 0;
        PreparedStatement stmt = null;

        try {
            connect();

            stmt = conn.prepareStatement(UPDATEPOST_SQL);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setInt(3, post.getPost_id());
            result = stmt.executeUpdate();
            stmt.close();  // 자원 해제

            deleteAttachments(post.getPost_id());
            saveAttachments(conn, post.getPost_id(), attachments);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        return result;
    }

    @Override
    public int deletePost(int post_id) {
        int result = 0;
        PreparedStatement stmt = null;

        try {
            connect();

            stmt = conn.prepareStatement(DELETEPOST_SQL);
            stmt.setInt(1, post_id);
            result = stmt.executeUpdate();
            stmt.close();  // 자원 해제

            deleteAttachments(post_id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, conn);
        }
        return result;
    }

    private void deleteAttachments(int post_id) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(DELETEATTACHMENT_SQL);
            stmt.setInt(1, post_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, stmt, null);
        }
    }

    @Override
    public List<PostDO> ListPost() {
        List<PostDO> postList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connect();

            stmt = conn.prepareStatement(GETALLPOST_SQL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                PostDO post = new PostDO();
                post.setPost_id(rs.getInt("POST_ID"));
                post.setTitle(rs.getString("TITLE"));
                post.setUser_id(rs.getString("USER_ID"));
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        return postList;
    }

    @Override
    public PostDO getPostById(int postId) {
        PostDO post = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connect();

            stmt = conn.prepareStatement(GETPOSTBYID_SQL);
            stmt.setInt(1, postId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                post = new PostDO();
                post.setPost_id(rs.getInt("POST_ID"));
                post.setTitle(rs.getString("TITLE"));
                post.setContent(rs.getString("CONTENT"));
                post.setUser_id(rs.getString("USER_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        return post;
    }

    @Override
    public List<AttachmentDO> getAttachmentsByPostId(int postId) {
        List<AttachmentDO> attachments = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connect();

            stmt = conn.prepareStatement(GETATTACHMENTBYPOSTID_SQL);
            stmt.setInt(1, postId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                AttachmentDO attachment = new AttachmentDO();
                attachment.setAttachment_id(rs.getInt("ATTACHMENT_ID"));
                attachment.setPost_id(rs.getInt("POST_ID"));
                attachment.setFileName(rs.getString("FILENAME"));
                attachment.setFilePath(rs.getString("FILE_PATH"));
                attachments.add(attachment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        return attachments;
    }

    @Override
    public List<PostDO> searchPosts(String keyword) {
        List<PostDO> searchPost = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connect();

            stmt = conn.prepareStatement(SEARCHPOST_SQL);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                PostDO post = new PostDO();
                post.setPost_id(rs.getInt("POST_ID"));
                post.setTitle(rs.getString("TITLE"));
                post.setUser_id(rs.getString("USER_ID"));
                searchPost.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }

        return searchPost;
    }

    private void closeResources(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (stmt != null) {
                stmt.close();
                System.out.println("PreparedStatement closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int getTotalPostCountForKeyword(String keyword) {
        int count = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connect();
            stmt = conn.prepareStatement(getTotalPostCountForKeyword);
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");
            rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        return count;
    }

    @Override
    public List<PostDO> getPosts(int page, int pageSize) {
        List<PostDO> postList = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connect();
            stmt = conn.prepareStatement(getPosts);
            stmt.setInt(1, pageSize);
            stmt.setInt(2, (page - 1) * pageSize);
            rs = stmt.executeQuery();
            while (rs.next()) {
                PostDO post = new PostDO();
                post.setPost_id(rs.getInt("POST_ID"));
                post.setTitle(rs.getString("TITLE"));
                post.setUser_id(rs.getString("USER_ID"));
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        return postList;
    }

    @Override
    public int getTotalPostCount() {
        int count = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connect();
            stmt = conn.prepareStatement(TotalPostCount);
            rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt, conn);
        }
        return count;
    }

}





