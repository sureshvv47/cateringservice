package com.apache.cxf.spring.hibernate.service;

import java.util.Date;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apache.cxf.spring.hibernate.model.Feedback;

//import in.benchresources.cdm.feedback.FeedbackType;

@Service("feedbackServices")
public class FeedbackServiceImpl extends BaseDao implements IFeedbackService {

	@Transactional
	@Override
	public String createOrSaveFeedbackInfo(String name, String email, String subject, String message ) {
		int feedbackId = 0;
		String staus;
		Feedback feedback = new Feedback();
		try{
			feedback.setEmail(email);
			feedback.setName(name);
			feedback.setMessage(message);
			feedback.setSubject(subject);
			feedback.setSubmittedDate(new Date());
			//.toGregorianCalendar().getTime()
			feedbackId = (Integer) sessionFactory.getCurrentSession().save(feedback);
		}catch(HibernateException hibernateException){
			hibernateException.printStackTrace();
		}
		staus ="Feedback information saved successfully with feedbackId :"+ feedbackId;
		return staus;
	}
}
