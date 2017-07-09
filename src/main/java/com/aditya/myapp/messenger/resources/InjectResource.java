package com.aditya.myapp.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectResource {

	@GET
	public String getParams(@Context UriInfo uriInfo){
		return uriInfo.getQueryParameters().toString();
	}
	
	@GET
	@Path("/headers")
	public String getHeaderParams(@Context HttpHeaders httpHeaders){
		return httpHeaders.getRequestHeaders().toString();
	}
}
