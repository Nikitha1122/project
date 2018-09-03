package com.cg.plp.bean;

import java.sql.Date;
import java.time.LocalDate;



import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Account {
	private String name;
	@Id
	private String mobile;
	private String email;
	private double balance;
	private Date doj;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	@Override
	public String toString() {
		return "Account [name=" + name + ", mobile=" + mobile + ", email=" + email + ", balance=" + balance + ", doj="
				+ doj + "]";
	}
	public Account(String name, String mobile, String email, double balance, Date doj) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.balance = balance;
		this.doj = doj;
	}
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
