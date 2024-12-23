package com.wplab.service;

import java.util.List;

public interface PostDAO {
    void addPost(PostDO post, List<AttachmentDO> attachments);
    int updatePost(PostDO post, List<AttachmentDO> attachments);
    int deletePost(int postId);
    List<PostDO> ListPost();
    PostDO getPostById(int postId);
    List<AttachmentDO> getAttachmentsByPostId(int postId);
    List<PostDO> searchPosts(String keyword);
	int getTotalPostCountForKeyword(String keyword);
	List<PostDO> getPosts(int page, int pageSize);
	int getTotalPostCount();
}
