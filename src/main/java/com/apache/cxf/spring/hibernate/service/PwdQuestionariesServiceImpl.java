package com.apache.cxf.spring.hibernate.service;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.apache.cxf.spring.hibernate.model.PwdQuestionaries;
import org.json.JSONObject;
import com.apache.cxf.spring.hibernate.service.UsersServiceImpl;

import in.benchresources.cdm.pwdquestionaries.PwdQuestionariesType;


@SuppressWarnings("unused")
@Service("pwdQuestionariesServices")
public class PwdQuestionariesServiceImpl extends BaseDao implements IPwdQuestionariesService{
	/**
	 * returns a String value with SUCCESS message after adding a User
	 */
	@Transactional
	@Override
	public String createOrSavePwdQuestionaries(String username, String email, String question1, String answer1, String question2, String answer2) {
		PwdQuestionaries pwdQuestions = new PwdQuestionaries();
		JSONObject jsonResponse = new JSONObject();
		UsersServiceImpl userUtils = new UsersServiceImpl();
		boolean status = false;
		try{
			if(username.trim()!=null && userUtils.validateUser(username)) {
				if(email.trim()!=null && userUtils.validateUser(email)) {
					if(question1.trim()!=null && answer1.trim()!=null && question2.trim()!=null && answer2.trim() != null )
					status = true;
				}
			}
			if(status) {
				pwdQuestions.setUsername(username);
				pwdQuestions.setEmail(email);
				pwdQuestions.setQuestion1(question1);
				pwdQuestions.setAnswer1(answer1);
				pwdQuestions.setQuestion2(question2);
				pwdQuestions.setAnswer2(answer2);
				pwdQuestions.setCreatedDate(new Date());
				pwdQuestions.setLastUpdatedDate(new Date());
				sessionFactory.getCurrentSession().save(pwdQuestions);
				jsonResponse.put("status", "Password Questions Registered Successfully");
			} else {
				jsonResponse.put("status", "failure");
				jsonResponse.put("statusMessage", "invalid username");
			}	
		}catch(HibernateException hibernateException){
			jsonResponse.put("status", "Exception");
			jsonResponse.put("statusMessage", "Exception occurred with service");
			//hibernateException.printStackTrace();
		}
		return  jsonResponse.toString();
	}
	
	@Transactional
	 @Override
	 public  String getPwdQuestionaries(String query) {
		JSONObject jsonResponse = new JSONObject();
		UsersServiceImpl userUtils = new UsersServiceImpl();
	  try{
		  if (userUtils.validateUser(query)) {
			  	PwdQuestionaries pwdQuestions = new PwdQuestionaries();
			  	Session session = sessionFactory.getCurrentSession();
			  	Criteria criteria = session.createCriteria(PwdQuestionaries.class);
			  	criteria.add(Restrictions.or(Restrictions.eqOrIsNull("username", query),Restrictions.eqOrIsNull("email", query)));
			  	pwdQuestions = (PwdQuestionaries)criteria.uniqueResult();  
			  	jsonResponse.put("username", pwdQuestions.getUsername());
			  	jsonResponse.put("email", pwdQuestions.getEmail());
			  	jsonResponse.put("question1", pwdQuestions.getQuestion1());
			  	jsonResponse.put("question2", pwdQuestions.getQuestion2());
		  } else {
			  jsonResponse.put("status", "failure");
			  jsonResponse.put("statusMessage", "Invalid username !!");
		  }
	  }catch(HibernateException hibernateException){
	   hibernateException.printStackTrace();
	  }  
	  return  jsonResponse.toString();
	 }
	
	@Transactional
	@Override
	public String validatePwdAnswers(String username, String question1, String answer1, String question2, String answer2) {
		  JSONObject jsonResponse = new JSONObject();
		  UsersServiceImpl userUtils = new UsersServiceImpl();
		  try{
			  if(userUtils.validateUser(username)) {
				  PwdQuestionaries pwdQuestions = new PwdQuestionaries();
				  Session session = sessionFactory.getCurrentSession();
				  Criteria criteria = session.createCriteria(PwdQuestionaries.class);
				  criteria.add(Restrictions.and(Restrictions.eqOrIsNull("username", username)));
				  pwdQuestions = (PwdQuestionaries)criteria.uniqueResult();
				  if(question1.equalsIgnoreCase(pwdQuestions.getQuestion1()) && answer1.equalsIgnoreCase(pwdQuestions.getAnswer1())) {
					  if(question2.equalsIgnoreCase(pwdQuestions.getQuestion2()) && answer2.equalsIgnoreCase(pwdQuestions.getAnswer2()))
						  jsonResponse.put("status", "success");
					  else 
						  jsonResponse.put("status", "failure");
				  }
			  } else {
				  jsonResponse.put("status", "failure");
				  jsonResponse.put("statusMessage","Validation Failed for User");
			  }
		  }catch(HibernateException hibernateException){
				   hibernateException.printStackTrace(); }
		return jsonResponse.toString();
	}
}
