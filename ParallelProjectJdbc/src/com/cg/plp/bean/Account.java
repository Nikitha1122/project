package com.cg.plp.bean;

import java.sql.Date;
import java.time.LocalDate;

public class Account {
	private String name;
	private String mobile;
	private String email;
	private double balance;
	private Date date;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Account(String name, String mobile, String email, double balance,
			Date date) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.balance = balance;
		this.date = date;
	}
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	
	

}
