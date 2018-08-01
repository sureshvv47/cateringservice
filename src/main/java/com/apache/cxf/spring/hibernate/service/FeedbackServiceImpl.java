package com.apache.cxf.spring.hibernate.service;

import java.util.Date;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.json.JSONObject;
import com.apache.cxf.spring.hibernate.model.Feedback;

import in.benchresources.cdm.feedback.FeedbackType;
import in.benchresources.cdm.feedback.FeedbackListType;

@Service("feedbackServices")
public class FeedbackServiceImpl extends BaseDao implements IFeedbackService {

	@Transactional
	@Override
	public String createOrSaveFeedbackInfo(String name, String email, String subject, String message ) {
		int feedbackId = 0;
		JSONObject jsonResponse = new JSONObject();
		Feedback feedback = new Feedback();
		try{
			feedback.setEmail(email);
			feedback.setName(name);
			feedback.setMessage(message);
			feedback.setSubject(subject);
			feedback.setSubmittedDate(new Date());
			feedbackId = (Integer) sessionFactory.getCurrentSession().save(feedback);
			System.out.println("<<<<Feedback Submitted with ID>>>>>"+feedbackId);
			jsonResponse.put("status", "feedback successfully submitted");
		}catch(HibernateException hibernateException){
			jsonResponse.put("status", "feedback not submitted. Please try after sometime");
			hibernateException.printStackTrace();
		}
		return jsonResponse.toString();
	}
}
