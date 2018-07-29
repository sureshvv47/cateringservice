package com.apache.cxf.spring.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pwdQuestionaries")
public class PwdQuestionaries {

	@Id 
	@GeneratedValue
	@Column(name = "pwdQuestionarieId")
	private int pwdQuestionarieId;

	@Column(name= "email")
	private String email;
	
	@Column(name= "username")
	private String username;
	
	@Column(name= "question1")
	private String question1;
	
	@Column(name= "answer1")
	private String answer1;

	@Column(name= "question2")
	private String question2;
	
	@Column(name= "answer2")
	private String answer2;

	@Column(name= "createdDate")
	private Date createdDate;
	
	@Column(name= "lastUpdatedDate")
	private Date lastUpdatedDate;
	
	@Column(name = "userId")
	private int userId;

	
	/**
	 * 
	 */
	public PwdQuestionaries() {
		super();
	}

	/**
	 * @return the pwdQuestionarieId
	 */
	public int getPwdQuestionarieId() {
		return pwdQuestionarieId;
	}

	/**
	 * @param pwdQuestionarieId the pwdQuestionarieId to set
	 */
	public void setPwdQuestionarieId(int pwdQuestionarieId) {
		this.pwdQuestionarieId = pwdQuestionarieId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the question1
	 */
	public String getQuestion1() {
		return question1;
	}

	/**
	 * @param question1 the question1 to set
	 */
	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	/**
	 * @return the answer1
	 */
	public String getAnswer1() {
		return answer1;
	}

	/**
	 * @param answer1 the answer1 to set
	 */
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	/**
	 * @return the question2
	 */
	public String getQuestion2() {
		return question2;
	}

	/**
	 * @param question2 the question2 to set
	 */
	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	/**
	 * @return the answer2
	 */
	public String getAnswer2() {
		return answer2;
	}

	/**
	 * @param answer2 the answer2 to set
	 */
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the lastUpdatedDate
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	/**
	 * @param lastUpdatedDate the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
}
