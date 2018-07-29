package com.apache.cxf.spring.hibernate.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import in.benchresources.cdm.pwdquestionaries.PwdQuestionariesType;

@Path("/pwdQuestionariesServices")
public interface IPwdQuestionariesService {

	@POST
	@Path("addPwdQuestions")
	//@Consumes({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	//public String createOrSavePwdQuestionaries(PwdQuestionariesType userType);
	public String createOrSavePwdQuestionaries(@FormParam("username") String username,
            @FormParam("email") String email, @FormParam("question1") String question1, @FormParam("answer1") String answer1,
            @FormParam("question2") String question2, @FormParam("answer2") String answer2);
	
	@GET
	 @Path("getPwdQuestions/{query}")
	 @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	 @Produces({MediaType.APPLICATION_JSON})
	 public String getPwdQuestionaries(@PathParam("query") String query);
	
	@POST
	@Path("validatePwdAnswers")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public String validatePwdAnswers(@FormParam("username") String username, @FormParam("question1") String question1, @FormParam("answer1") String answer1,
            @FormParam("question2") String question2, @FormParam("answer2") String answer2);
}