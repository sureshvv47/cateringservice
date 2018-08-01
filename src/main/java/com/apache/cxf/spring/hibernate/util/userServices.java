package com.apache.cxf.spring.hibernate.util;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.apache.cxf.spring.hibernate.model.Users;
import com.apache.cxf.spring.hibernate.service.BaseDao;

public class userServices extends BaseDao {

	public static void main(String args[]) {
		
	
	}
	
	public int getUserIdByUsername(String username) {
		Users user = null;
		int userId =0;
		try{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Users.class);
			criteria.add(Restrictions.eqOrIsNull("username", username));
			user = (Users)criteria.uniqueResult();
			userId = user.getUserId();

		}catch(HibernateException hibernateException){
			//jsonResponse.put("status", "failure");	
			hibernateException.printStackTrace();
			//return jsonResponse.toString();
			}
		return userId;
	}
	
	@SuppressWarnings("unchecked")
	public boolean validateUser(String query) {
		System.out.println("<<<User util service>>>>");
		boolean status=false;
		try{
			List<Users> usersList = sessionFactory.getCurrentSession().createCriteria(Users.class).list();
			for (Users users : usersList) {
				if(users.getUsername().equals(query)) {
					status=true;
					break;
				} else if(users.getEmail().equals(query)) {
					status = true;
					break;
				} else if (users.getPhone().equals(query)) {
					status = true;
					break;
				}
			}
			System.out.println("<<successfully executed service>>"+status);
		}catch(HibernateException hibernateException){
			status=false;
			//hibernateException.printStackTrace();
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public boolean validateUserId(int userId) {
		boolean status=false;
		try{
			List<Users> usersList = sessionFactory.getCurrentSession().createCriteria(Users.class).list();
			for (Users users : usersList) {
				if(users.getUserId() == userId) {
						status=true;
				}
			}

		}catch(HibernateException hibernateException){
			status=false;
			//hibernateException.printStackTrace();
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	public String validateCredentials(String username, String password) {
		String status = "";
		try{
			List<Users> usersList = sessionFactory.getCurrentSession().createCriteria(Users.class).list();
			for (Users users : usersList) {
				if(users.getUsername().equals(username)) {
					if (users.getPassword().equals(password)) 
						status = "success";
					else
						status = "failure";
					}
			}
		}catch(HibernateException hibernateException){
			status="failure";
			hibernateException.printStackTrace();
		}
		return status;
	}
}