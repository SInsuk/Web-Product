package com.wplab.service;

import java.sql.Timestamp;
import java.util.List;

public class PostDO {
    private int post_id;
    private String user_id;
    private String title;
    private String content;
    private List<AttachmentDO> attachments;
    
    // 기본 생성자
    public PostDO() {}

    // 새로 추가한 생성자
    public PostDO(String title, String content, String user_id) {
        this.title = title;
        this.content = content;
        this.user_id = user_id;
    }

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<AttachmentDO> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentDO> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "PostDO [post_id=" + post_id + ", user_id=" + user_id + ", title=" + title + ", content=" + content
				+ ", attachments=" + attachments + "]";
	}
}