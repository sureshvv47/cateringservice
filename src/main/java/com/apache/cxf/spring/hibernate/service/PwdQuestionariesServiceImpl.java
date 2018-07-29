package com.apache.cxf.spring.hibernate.service;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.apache.cxf.spring.hibernate.util.convertDate;
import com.apache.cxf.spring.hibernate.service.UsersServiceImpl;
import com.apache.cxf.spring.hibernate.model.Users;
import com.apache.cxf.spring.hibernate.model.PwdQuestionaries;
import java.util.GregorianCalendar;

import javax.ws.rs.FormParam;

import org.json.JSONObject;

import in.benchresources.cdm.pwdquestionaries.PwdQuestionariesType;
import in.benchresources.cdm.users.UsersListType;
import in.benchresources.cdm.users.UsersType;

@Service("pwdQuestionariesServices")
public class PwdQuestionariesServiceImpl extends BaseDao implements IPwdQuestionariesService{

	/**
	 * returns a String value with SUCCESS message after adding a User
	 */
	@Transactional
	@Override
	public String createOrSavePwdQuestionaries(String username, String email, String question1, String answer1, String question2, String answer2) {
		int pwdQuestionarieId = 0;
		JSONObject jsonResponse = new JSONObject();
		PwdQuestionaries pwdQuestions = new PwdQuestionaries();
		try{
			if(username.trim()!=null)
				pwdQuestions.setUsername(username);
			if(email.trim()!=null)
				pwdQuestions.setEmail(email);
			if(question1.trim()!=null)
				pwdQuestions.setQuestion1(question1);
			if(answer1.trim()!=null)
				pwdQuestions.setAnswer1(answer1);
			if(question2.trim()!=null)
				pwdQuestions.setQuestion2(question2);
			if(answer2.trim()!=null)
				pwdQuestions.setAnswer2(answer2);
			pwdQuestions.setCreatedDate(new Date());
			pwdQuestions.setLastUpdatedDate(new Date());
			/*	}else{
					pwdQuestions.setLastUpdatedDate(pwdQestionariesType.getLastUpdatedDate().toGregorianCalendar().getTime());
				}*/
			pwdQuestionarieId = (Integer) sessionFactory.getCurrentSession().save(pwdQuestions);
		}catch(HibernateException hibernateException){
			jsonResponse.put("status", "Exception");
			jsonResponse.put("statusMessage", "Exception occurred with service");
			//hibernateException.printStackTrace();
		}
		jsonResponse.put("status", "Password Questions Registered Successfully");
		//status = "Password Questionaries Registered Successfully with ID: "+ pwdQuestionarieId;
		return  jsonResponse.toString();
	}
	
	@Transactional
	 @Override
	 public  String getPwdQuestionaries(String query) {
	  PwdQuestionariesType pwdQuestionaries = new PwdQuestionariesType();
	  JSONObject jsonResponse = new JSONObject();
	  try{
	   PwdQuestionaries pwdQuestions = new PwdQuestionaries();
	   Session session = sessionFactory.getCurrentSession();
	   Criteria criteria = session.createCriteria(PwdQuestionaries.class);
	   criteria.add(Restrictions.or(Restrictions.eqOrIsNull("username", query),Restrictions.eqOrIsNull("email", query)));
	   pwdQuestions = (PwdQuestionaries)criteria.uniqueResult();
	   
	   jsonResponse.put("username", pwdQuestions.getUsername());
	   jsonResponse.put("email", pwdQuestions.getEmail());
	   jsonResponse.put("question1", pwdQuestions.getQuestion1());
	   jsonResponse.put("question2", pwdQuestions.getQuestion2());
	   
	   /*pwdQuestionaries.setUsername(pwdQuestions.getUsername());
	  // pwdQuestionaries.setUserId(pwdQuestions.getUserId());
	  // pwdQuestionaries.setAnswer1(pwdQuestions.getAnswer1());
	  // pwdQuestionaries.setAnswer2(pwdQuestions.getAnswer2());
	   pwdQuestionaries.setQuestion1(pwdQuestions.getQuestion1());
	   pwdQuestionaries.setQuestion2(pwdQuestions.getQuestion2());
	   pwdQuestionaries.setEmail(pwdQuestions.getEmail());
	   pwdQuestionaries.setPwdQuestionarieId(pwdQuestions.getPwdQuestionarieId());
	   pwdQuestionaries.setCreatedDate(convertDate.toXMLGregorianCalendar(pwdQuestions.getCreatedDate()));*/
	//   System.out.println(pwdQuestions.getCreatedDate());
	 //  System.out.println(convertDate.toXMLGregorianCalendar(pwdQuestions.getCreatedDate()));
	   //pwdQuestionaries.setLastModifiedDate((Date) pwdQuestions.getLastUpdatedDate());
	  
	  }catch(HibernateException hibernateException){
	   hibernateException.printStackTrace();
	  }  
	  return  jsonResponse.toString();
	 }
	
	@Transactional
	@Override
	public String validatePwdAnswers(String username, String question1, String answer1, String question2, String answer2) {
		  JSONObject jsonResponse = new JSONObject();
		  try{
			   PwdQuestionaries pwdQuestions = new PwdQuestionaries();
			   Session session = sessionFactory.getCurrentSession();
			   Criteria criteria = session.createCriteria(PwdQuestionaries.class);
			   criteria.add(Restrictions.and(Restrictions.eqOrIsNull("username", username)));
			   pwdQuestions = (PwdQuestionaries)criteria.uniqueResult();
			   if(question1.equalsIgnoreCase(pwdQuestions.getQuestion1()) && question2.equalsIgnoreCase(pwdQuestions.getQuestion2())) {
				   if(answer1.equalsIgnoreCase(pwdQuestions.getAnswer1()) && answer2.equalsIgnoreCase(pwdQuestions.getAnswer2()))
					   	jsonResponse.put("status", "success");
				   else 
					   jsonResponse.put("status", "failure");
			   }
		  }catch(HibernateException hibernateException){
				   hibernateException.printStackTrace(); }
		return jsonResponse.toString();
	}
	
}
