package com.aditya.myapp.messenger.model;

import java.util.Date;

public class Comment {
	
	private long commentId;
	private String comment;
	private String author;
	private Date created;
	
	public Comment(){
	}
	
	public Comment(long commentId, String author, String comment){
		this.commentId = commentId;
		this.author = author;
		this.comment = comment;
		this.created = new Date();
	}
	
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
