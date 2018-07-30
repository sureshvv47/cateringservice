package com.apache.cxf.spring.hibernate.service;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.apache.cxf.spring.hibernate.model.Users;
import com.apache.cxf.spring.hibernate.util.PasswordHandler;

import in.benchresources.cdm.users.UsersListType;
import in.benchresources.cdm.users.UsersType;

@Service("usersServices")
public class UsersServiceImpl extends BaseDao implements IUsersService {

	/**
	 * returns a String value with SUCCESS message after adding a User
	 */
	@Transactional
	@Override
	public String createOrSaveNewUserInfo(String firstname,String lastName,String username,String password,String email,String phone) {
		int userId = 0;
		JSONObject jsonResponse = new JSONObject();
		Users newUser = new Users();
		try{
			if(firstname.trim()!=null)
				newUser.setFirstName(firstname);
			if(lastName.trim()!=null)
				newUser.setLastName(lastName.trim());
			if(username.trim()!=null && !(validateUser(username)))
				newUser.setUsername(username.trim());
			else { 
				jsonResponse.put("status", "failure");
				jsonResponse.put("statusReason", "Username already Registered");
				return jsonResponse.toString();
			}
			if(password.trim()!=null)
				newUser.setPassword(PasswordHandler.getInstance().encrypt(password.trim()));
			if(phone.trim()!=null && !(validateUser(phone)))
				newUser.setPhone(phone.trim());
			else { 
				jsonResponse.put("status", "failure");
				jsonResponse.put("statusReason", "Phone number already Registered");
				return jsonResponse.toString();
			}
			newUser.setRole("user");
			newUser.setStatus("active");
			if(email.trim()!=null && !(validateUser(email)))
				newUser.setEmail(email.trim());
			else { 
				jsonResponse.put("status", "failure");
				jsonResponse.put("statusReason", "Email already Registered");
				return jsonResponse.toString();
			}
			newUser.setCreateDate(new Date());
			userId = (Integer) sessionFactory.getCurrentSession().save(newUser);
			System.out.println("<<<<User Created Successfully>>>>>"+userId);
		}catch(HibernateException hibernateException){
			System.out.println("<<<<<<<UserID creation failed>>>");
			jsonResponse.put("status", "Exception");
			jsonResponse.put("statusReason", "Error while inserting record into db");
			return jsonResponse.toString();
			//hibernateException.printStackTrace();
		}
		jsonResponse.put("status", "success");
		jsonResponse.put("statusReason", "User Registration Successful");
		return jsonResponse.toString();
	}

	@Transactional
	@Override
	public String updateUser(String username, String password) {
		JSONObject jsonResponse = new JSONObject();
		Users updateUser = new Users();
		try{
			if(validateUser(username)) {
				if(password.trim()!=null) {
					Users user = null;
					Session session = sessionFactory.getCurrentSession();
					Criteria criteria = session.createCriteria(Users.class);
					criteria.add(Restrictions.eqOrIsNull("username", username));
					user = (Users)criteria.uniqueResult();
					updateUser.setUserId(user.getUserId());
					updateUser.setUsername(user.getUsername());
					updateUser.setFirstName(user.getFirstName());
					updateUser.setLastName(user.getLastName());
					updateUser.setEmail(user.getEmail());
					updateUser.setPhone(user.getPhone());
					updateUser.setRole(user.getRole());
					updateUser.setStatus("active");
					updateUser.setUserId(getUserIdByUsername(username));
					updateUser.setPassword(PasswordHandler.getInstance().encrypt(password.trim()));
					updateUser.setCreateDate(user.getCreateDate());
					updateUser.setLastModifiedDate(new Date());
					sessionFactory.getCurrentSession().clear();
					sessionFactory.getCurrentSession().update(updateUser);
				}
					jsonResponse.put("status", "updated");
					System.out.println("=======IFF=========");
				} else {
				System.out.println("=======else=========");
				jsonResponse.put("status", "invalid Username");
			}
				
		}catch(HibernateException hibernateException){
			//jsonResponse.put("status", "failure");	
			hibernateException.printStackTrace();
			//return jsonResponse.toString();
			}
		return jsonResponse.toString();
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
	
	/**
	 * retrieves a User object based on the username,email,phone supplied in the formal argument using @PathParam
	 */
	
	@SuppressWarnings("unchecked")
	@Transactional
	//@Override
	public String getUserValidations(String query) {
		JSONObject jsonResponse = new JSONObject();
		if(query.trim() != null && query.length()>0) {
			try{
				List<Users> usersList = sessionFactory.getCurrentSession().createCriteria(Users.class).list();
				for (Users users : usersList) {
					if(users.getUsername().equals(query)){
						System.out.println("UserName Already Exists : " +query);
						jsonResponse.put("status", "invalid");
						break;
					}else if(users.getEmail().equals(query)){
						System.out.println("Email Already Exists : " +query);
						jsonResponse.put("status", "invalid");
						break;
					}else if(users.getPhone().equals(query)){
						System.out.println("Phone Already Exists : " +query);
						jsonResponse.put("status", "invalid");
						break;
					}else{
						jsonResponse.put("status","valid");
					}
				}
			}catch(HibernateException hibernateException){
			hibernateException.printStackTrace();
			}
		}
		System.out.println("<<<<<<<<<<<<>>>>>>>>>"+jsonResponse.toString());
		return jsonResponse.toString();
	}
	
	/**
	 * retrieves a player object based on the playerId supplied in the formal argument using @PathParam
	 */
	@Transactional
	@Override
	public String getUserInfo(String query) {
		UsersType getuser = new UsersType();
		Users user = null;
		JSONObject jsonResponse = new JSONObject();
		// retrieve User based on the username supplied in the formal argument
		try{
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Users.class);
			if (query.matches("^\\d+$")) {
				int userID = Integer.parseInt(query);
				System.out.println("=======query====If======="+query);
				if(validateUserId(userID)) {
					criteria.add(Restrictions.eqOrIsNull("userId", Integer.parseInt(query)));
					user = (Users)criteria.uniqueResult();
				} else {
					jsonResponse.put("status", "Failure");
					jsonResponse.put("statusMessage", "UserId Does not exists");
					return jsonResponse.toString();
				}
			}
			else {
				System.out.println("=======query====else======="+query);
				if(validateUser(query)) {
					criteria.add(Restrictions.eqOrIsNull("username", query));
					user = (Users)criteria.uniqueResult();
				} else {
					jsonResponse.put("status", "Failure");
					jsonResponse.put("statusMessage", "Username Does not exists");
					return jsonResponse.toString();
				}
			}
			
		}catch(HibernateException hibernateException){
			hibernateException.printStackTrace();
		} if (user.getUsername() != null || user.getUsername() != "") {
			jsonResponse.put("userId", user.getUserId());
			jsonResponse.put("firstName", user.getFirstName());
			jsonResponse.put("lastName", user.getLastName());
			jsonResponse.put("username", user.getUsername());
			jsonResponse.put("phone", user.getPhone());
			jsonResponse.put("email", user.getEmail());
			jsonResponse.put("role", user.getRole());
			jsonResponse.put("status", user.getStatus());
			return jsonResponse.toString();
		} else {
				jsonResponse.put("status", "Failure");
				jsonResponse.put("statusMessage", "Invalid UserId or UserName");
				return jsonResponse.toString();
			}
	}

	@SuppressWarnings("unchecked")
	public boolean validateUser(String query) {
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
		}catch(HibernateException hibernateException){
			hibernateException.printStackTrace();
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
			hibernateException.printStackTrace();
		}
		return status;
	}
	/** validate user based on login credentials and returns user object with all details
	 * 
	 */
	
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
			hibernateException.printStackTrace();
		}
		return status;
	}
	@Transactional
	@Override
	public String userLogin(String username, String password) {
		Users user = null;
		JSONObject jsonResponse = new JSONObject();
		// retrieve User based on the username supplied in the formal argument
		if (validateCredentials(username, password).equals("success")) {
			//System.out.println("Valid TestCase");
			try{
				Session session = sessionFactory.getCurrentSession();
				Criteria criteria = session.createCriteria(Users.class);
				criteria.add(Restrictions.eqOrIsNull("username", username));
				user = (Users)criteria.uniqueResult();
				jsonResponse.put("userId", user.getUserId());
				jsonResponse.put("firstName", user.getFirstName());
				jsonResponse.put("lastName", user.getLastName());
				jsonResponse.put("username", user.getUsername());
				jsonResponse.put("phone", user.getPhone());
				jsonResponse.put("email", user.getEmail());
				jsonResponse.put("role", user.getRole());
				jsonResponse.put("status",user.getStatus());
			}catch(HibernateException hibernateException){
				jsonResponse.put("status", "Exception");
				jsonResponse.put("statusMessage", "Exception occurred while updating password. Please contact Administrator");
				return jsonResponse.toString();
				//hibernateException.printStackTrace();
			}
			return jsonResponse.toString();
		} else {
			//System.out.println("InValid");
			jsonResponse.put("status", "Failure");
			jsonResponse.put("statusMessage", "Invalid Credentials or User Does not exist !!!");
			return jsonResponse.toString();	
		}
		
	}
	/**
	 * returns a String value with SUCCESS message after updating a user
	 */


	/**
	 * returns a String value with SUCCESS message after deleting a user
	 */
	@Transactional
	@Override
	public String deleteUsersInfo(UsersType playerType) {

		// delete user info & return SUCCESS message
		Users deletePlayer = new Users();
		sessionFactory.getCurrentSession().delete(deletePlayer);
		return "Player information deleted successfully";
	}

	/**
	 * retrieves all user stored
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	@Override
	public UsersListType getAllUsersInfo() {
		// get all player info from database
		List<Users> lstPlayer = sessionFactory.getCurrentSession().createCriteria(Users.class).list();
		UsersListType playerListType = new UsersListType();
		for(Users player : lstPlayer) {
			UsersType playerType = new UsersType();
		}
		return playerListType;
	}
}