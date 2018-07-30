package com.apache.cxf.spring.hibernate.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.json.JSONObject;

import in.benchresources.cdm.users.UsersListType;
import in.benchresources.cdm.users.UsersType;


@Path("/usersServices")
public interface IUsersService {

	// Basic CRUD operations for Users Service

	//http://localhost:8085/ApacheCXF-API/services/usersServices/addUser
	//@CrossOrigin(origins = "*", allowedHeaders = "*")
	@POST
	@Path("addUser")   //@Consumes({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON})
	//@Produces({MediaType.APPLICATION_FORM_URLENCODED})
	//public String createOrSaveNewUserInfo(@RequestBody UsersType user);
	public String createOrSaveNewUserInfo(@FormParam("firstName") String firstName,@FormParam("lastName") String lastName,
            @FormParam("username") String username,
            @FormParam("password") String password,@FormParam("email") String email,@FormParam("phone") String phone);

	//http://localhost:8085/ApacheCXF-API/services/usersServices/userValidations/{query}
	@GET
	@Path("userValidations/{query}")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public String getUserValidations(@PathParam("query") String query);

	// http://localhost:8085/ApacheCXF-API/services/usersServices/getUser/{query}
	@GET
	@Path("getUser/{query}")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public String getUserInfo(@PathParam("query") String query);
	
	// http://localhost:8085/ApacheCXF-API/services/usersServices/userLogin
	@POST
	@Path("userLogin")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public String userLogin(@FormParam("username") String username, @FormParam("password") String password);

	// http://localhost:8085/ApacheCXF-API/services/usersServices/updateUser
	@PUT
	@Path("updateUser")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public String updateUser(@FormParam("username") String username, @FormParam("password") String password);

	// http://localhost:8085/ApacheCXF-API/services/playerservice/deleteplayer
	@DELETE
	@Path("deleteUser")
	@Consumes({MediaType.APPLICATION_JSON,})
	@Produces({MediaType.APPLICATION_FORM_URLENCODED})
	public String deleteUsersInfo(UsersType userId);

	// http://localhost:8085/ApacheCXF-API/services/playerservice/getallplayer
	@GET
	@Path("getAllUsers")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public UsersListType getAllUsersInfo();
}