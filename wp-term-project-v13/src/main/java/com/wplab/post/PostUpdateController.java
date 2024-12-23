package com.wplab.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wplab.service.*;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class PostUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PostUpdateController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postId = request.getParameter("post_id");
        if (postId == null) {
            response.sendRedirect("postList");
            return;
        }

        String dbcpResourceName = getServletContext().getInitParameter("dbcp_resource_name");
        PostDAO postDAO = new PostDAOImplByDBCP(dbcpResourceName);
        PostDO post = postDAO.getPostById(Integer.parseInt(postId));

        if (post == null) {
            response.sendRedirect("postList");
            return;
        }

        request.setAttribute("post", post);
        request.getRequestDispatcher("PostUpdate.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int postId = Integer.parseInt(request.getParameter("post_id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("user_id");

        String dbcpResourceName = getServletContext().getInitParameter("dbcp_resource_name");
        PostDAO postDAO = new PostDAOImplByDBCP(dbcpResourceName);
        PostDO post = postDAO.getPostById(postId);

        if (post == null || (!post.getUser_id().equals(userId) && !userId.equals("admin"))) {
            response.sendRedirect("postList");
            return;
        }

        post.setTitle(title);
        post.setContent(content);
        List<AttachmentDO> attachments = saveUploadedFiles(request, postId);

        postDAO.updatePost(post, attachments);
        response.sendRedirect("postList");
    }

    private List<AttachmentDO> saveUploadedFiles(HttpServletRequest request, int postId) throws IllegalStateException, IOException, ServletException {
        List<AttachmentDO> attachments = new ArrayList<>();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        int fileCount = 0;
        for (Part part : request.getParts()) {
            if (part.getName().equals("file") && fileCount < 3) {
                String fileName = extractFileName(part);
                if (fileName != null && !fileName.isEmpty()) {
                    String filePath = uploadPath + File.separator + fileName;
                    part.write(filePath);

                    AttachmentDO attachment = new AttachmentDO();
                    attachment.setPost_id(postId);
                    attachment.setFileName(fileName);
                    attachment.setFilePath(filePath);
                    attachments.add(attachment);

                    fileCount++;
                }
            }
        }
        return attachments;
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return null;
    }
}





