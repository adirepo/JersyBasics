package com.aditya.myapp.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aditya.myapp.messenger.database.DatabaseClass;
import com.aditya.myapp.messenger.model.Comment;
import com.aditya.myapp.messenger.model.Message;

public class CommentService {

private Map <Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId){
		Message msg = messages.get(messageId);
		return new ArrayList<Comment>(msg.getComments().values());
	}
	
	public Comment getComment(long messageId, long commentId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment){
		Message message = messages.get(messageId);
		Map<Long, Comment> comments = message.getComments();
		comment.setCommentId(comments.size()+1);
		comments.put(comment.getCommentId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment){
		if(comment.getCommentId() <= 0){
			return null;
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comments.put(comment.getCommentId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
