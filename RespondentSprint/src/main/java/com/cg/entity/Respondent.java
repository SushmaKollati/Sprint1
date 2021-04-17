package com.cg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="respondent")
public class Respondent {
	
	@Id
	@NotEmpty(message="respondentId should not be Empty")
	@Size(min=5, max=20)
	private long respondentId;
	
	@NotEmpty(message="respondentemailId should not be Empty")
	@Size(min=5, max=20)
	private String emailId;
	
	@NotEmpty(message="respondentpassword should not be Empty")
	@Size(min=5, max=20)
	private String password;
	public Respondent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Respondent(long respondentId, String emailId, String password) {
		super();
		this.respondentId = respondentId;
		this.emailId = emailId;
		this.password = password;
	}

	public long getRespondentId() {
		return respondentId;
	}
	public void setRespondentId(long respondentId) {
		this.respondentId = respondentId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Respondent [respondentId=" + respondentId + ", emailId=" + emailId + ", password=" + password + "]";
	}
	
 
	
}
