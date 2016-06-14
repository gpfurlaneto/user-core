package br.com.gpfurlaneto.user.core.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.gpfurlaneto.UserService;
import br.com.gpfurlaneto.dto.UserDto;

@Stateless
@Path("/user")
public class UserRest {

	@Inject
	private UserService userService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		List<UserDto> users = userService.listAll();
		return Response.status(200).entity(users).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveUser(UserDto userDto) {
		try {
			userService.save(userDto);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.accepted(e).status(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id){
		userService.delete(id);
	}
}
