package com.apache.cxf.spring.hibernate.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import in.benchresources.cdm.feedback.FeedbackType;


@Path("/feedbackServices")
public interface IFeedbackService {

	//http://HSC-PG00AZYN:8080/ApacheCXF-API/services/feedbackServices/addFeedback
	@POST
	@Path("addFeedback")
	//@Consumes({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	//@Produces({MediaType.APPLICATION_FORM_URLENCODED})
	//public String createOrSaveFeedbackInfo(FeedbackType feedbackType);
	public String createOrSaveFeedbackInfo(@FormParam("name") String name, @FormParam("email") String email, @FormParam("subject") String subject, @FormParam("message") String message);
}
