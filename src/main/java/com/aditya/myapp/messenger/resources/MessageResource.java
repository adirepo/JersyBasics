package com.aditya.myapp.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.aditya.myapp.messenger.bean.MessageFilterBean;
import com.aditya.myapp.messenger.model.Message;
import com.aditya.myapp.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {

	MessageService ms = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@BeanParam MessageFilterBean msb){
		if(msb.getYear() > 0){
			return ms.getMessagesForYear(msb.getYear());
		}else if(msb.getStart() + msb.getSize() > 0){
			return ms.getMessagesPeginated(msb.getStart(), msb.getSize());
		}
		System.out.println("Host name: "+msb.getHost());
		return ms.getMessages();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		Message addedmessage = ms.addMessage(message);
		String messageId = String.valueOf(addedmessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(messageId).build();
		return Response
				.created(uri)
				.entity(addedmessage)
				.build() ;
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id){
		ms.removeMessage(id);
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message){
		message.setId(id);
		return ms.updateMessage(message);
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo){
		Message message = ms.getMessage(id);
		message = addSelfMessageUrl(uriInfo, message);
		message = addProfileUrl(uriInfo, message);
		return message;
	}

	@Path("/{messageId}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CommentResource manageComments(){
		return new CommentResource();
	}

	private Message addSelfMessageUrl(UriInfo uriInfo, Message message) {
		String url = uriInfo.getAbsolutePathBuilder().toString();
		message.addLink(url, "self");
		return message;
	}
	
	private Message addProfileUrl(UriInfo uriInfo, Message message) {
		String url = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).toString();
		message.addLink(url, "profile");
		return message;
	}
}
