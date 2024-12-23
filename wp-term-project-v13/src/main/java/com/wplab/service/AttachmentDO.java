package com.wplab.service;

public class AttachmentDO {
    private int attachment_id;
    private int post_id;
    private String fileName;
    private String filePath;

    public AttachmentDO() {}

    public AttachmentDO(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
    
	public int getAttachment_id() {
		return attachment_id;
	}
	
	public void setAttachment_id(int attachment_id) {
		this.attachment_id = attachment_id;
	}
	
	public int getPost_id() {
		return post_id;
	}
	
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public String toString() {
		return "AttachmentDO [attachment_id=" + attachment_id + ", post_id=" + post_id + ", fileName=" + fileName
				+ ", filePath=" + filePath + "]";
	}
    

    
}
