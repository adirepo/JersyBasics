package com.aditya.myapp.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.aditya.myapp.messenger.model.Comment;
import com.aditya.myapp.messenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {
	CommentService cs = new CommentService();
	
	@GET
	public List<Comment> getComments(@PathParam("messageId") long id){
		return cs.getAllComments(id);
	}

	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment){
		return cs.addComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public Comment deleteComment(@PathParam("messageId") long messageId,@PathParam("commentId") long commentId){
		return cs.removeComment(messageId, commentId);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment){
		comment.setCommentId(commentId);
		return cs.updateComment(messageId, comment);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId){
		return cs.getComment(messageId, commentId);
	}
}
