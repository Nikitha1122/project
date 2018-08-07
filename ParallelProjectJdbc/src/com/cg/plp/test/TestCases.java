package com.cg.plp.test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import org.junit.Before;
import org.junit.Test;

import com.cg.plp.bean.Account;
import com.cg.plp.exception.WalletException;
import com.cg.plp.service.AccountService;
import com.cg.plp.service.IAccountService;

public class TestCases {
	/*IAccountDao dao;
	AccountDb db;*/
	Account acc;
	IAccountService service;
	
	@Before
	public void init() {
		// TODO Auto-generated method stub
		service = new AccountService();
		acc = new Account();
	}
	
	@Test
	//Given wrong mobile number.
	public void testAccountMobile(){
		
		acc.setName("Nikitha");
		acc.setMobile("11223366");
		acc.setEmail("nikki@gmail.com");
		acc.setBalance(500.0);
		acc.setDate(Date.valueOf(LocalDate.now()));
		
		try {
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (WalletException e) {
			// TODO: handle exception
			//System.out.println("wrong mobile:"+e.getMessage());
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}
	
	@Test
	// Given wrong name i.e. contains numbers 
	public void testAccountName(){
		
		acc.setName("1232456");
		acc.setMobile("1234567890");
		acc.setEmail("nikki@gmail.com");
		acc.setBalance(500.0);
		
		try {
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (WalletException e) {
			// TODO: handle exception
			//System.out.println("name with numbers:"+e.getMessage());
			assertEquals("Name must contain only alphabets", e.getMessage());
		}	
	}
	@Test
	// given an empty name string
	public void testAccountName1(){
		
		acc.setName("");
		acc.setMobile("1234567890");
		acc.setEmail("nikki@gmail.com");
		acc.setBalance(500.0);
		
		try {
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (WalletException e) {
			// TODO: handle exception
			//System.out.println("empty name:"+e.getMessage());
			assertEquals("Name cannot be empty", e.getMessage());
		}	
	}
	
	@Test
	public void testAccountEmail(){
		
		acc.setName("nikitha");
		acc.setMobile("1234567890");
		acc.setEmail("nikkigmail.com");
		acc.setBalance(500.0);
		
		try {
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (WalletException e) {
			// TODO: handle exception
			//System.out.println("wrong id:"+e.getMessage());
			assertEquals("Invalid Email ID", e.getMessage());
		}	
	}
	
	@Test
	public void testAccountBalance(){
		
		acc.setName("nikitha");
		acc.setMobile("1234567890");
		acc.setEmail("nikki@cg.com");
		acc.setBalance(-500);
		//System.out.println(acc.getBalance());
		try {
			
			if(service.validateAccount(acc))
				service.createAccount(acc);
		} catch (WalletException e) {
			// TODO: handle exception
			//System.out.println("negative balance:"+e.getMessage());
			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}	
	}
	
	@Test
	public void testCreateAccount(){
		
		acc.setName("nikitha");
		acc.setMobile("1231231231");
		acc.setEmail("nik@cg.com");
		acc.setBalance(500);
		acc.setDate(Date.valueOf(LocalDate.now()));
		
		try {
			String mobile = service.createAccount(acc);
			assertNotNull(mobile);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		}
		
	}
	@Test
public void testCreateAccount1(){
		
		acc.setName("preethi");
		acc.setMobile("4444444444");
		acc.setEmail("pre@cg.com");
		acc.setBalance(2500);
		acc.setDate(Date.valueOf(LocalDate.now()));
		
		try {
			String mobile = service.createAccount(acc);
			assertNotNull(mobile);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		}
		
	}
	@Test
	public void testCreateAccount2(){
			
			acc.setName("Jhony");
			acc.setMobile("3333333333");
			acc.setEmail("jon@cg.com");
			acc.setBalance(3500);
			acc.setDate(Date.valueOf(LocalDate.now()));
			
			try {
				String mobile = service.createAccount(acc);
				assertNotNull(mobile);
			} catch (WalletException e) {
				// TODO Auto-generated catch block
				//System.out.println(e.getMessage());
			}
			
		}
	
	
	
	@Test
	public void testShowBalanceNonExistingAccount(){
		acc.setMobile("1212121212");
		try {
			service.showBalance(acc.getMobile());
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Exhausted Resultset", e.getMessage());
		}
	}
	
	@Test
	public void testShowBalanceExistingAccountWrongNumber(){
		acc.setMobile("11111111");
		try {
			service.showBalance(acc.getMobile());
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
		
	}
	
	@Test
	public void testShowBalanceExistingName(){
		acc.setMobile("1111111111");
		try {
			acc = service.printTransaction(acc.getMobile());
			service.showBalance(acc.getMobile());
			assertEquals("nikitha",acc.getName() );
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	
	@Test
	public void testShowBalanceExisting(){
		acc.setMobile("1111111111");
		
		try {
			//acc = service.getAccountDetails(acc.getMobile());
			//System.out.println(service.showBalance(acc.getMobile()));
			assertEquals(500.0, service.showBalance(acc.getMobile()),0.5);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	
	@Test
	public void testDepositCheckMobile(){
		acc.setMobile("1111111");
		double depositAmt = 1500.0;
		try {
			if(service.validateMobile(acc.getMobile())){
				//acc = service.getAccountDetails(acc.getMobile());
				double bal = service.deposit(acc.getMobile(), depositAmt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
		
	}
	
	@Test
	public void testDepositCheckBalance(){
		acc.setMobile("1111111111");
		
		double depositAmt = -1500.0;
		try {
			if(service.validateBalance(acc.getBalance())){
				//acc = service.getAccountDetails(acc.getMobile());
				double bal = service.deposit(acc.getMobile(), depositAmt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}
		
	}
	
	@Test
	public void testDepositCorrect(){
		acc.setMobile("1111111111");
		double depositAmt = 1500.0;
		try {
			if(service.validateBalance(acc.getBalance())){
				double bal = service.deposit(acc.getMobile(), depositAmt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			
		}
	} //Account balance is Low
	
	@Test
	public void testWithdrawCheckMobile(){
		acc.setMobile("11111111");
		double withdrawAmt = 1500.0;
		try {
			if(service.validateMobile(acc.getMobile())){
				double bal = service.withdraw(acc.getMobile(), withdrawAmt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
		
	}
	
	@Test
	public void testWithdrawCheckBalance(){
		acc.setMobile("1111111111");
		double amt = -1500.0;
		try {
			if(service.validateBalance(acc.getBalance())){
				//acc = service.getAccountDetails(acc.getMobile());
				double bal = service.withdraw(acc.getMobile(), amt);
				assertNotEquals(acc.getBalance(), bal);
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}
		
	}
	/*
	@Test
	public void testWithdrawMoreAmount(){
		acc.setMobile("4444444444");
		
		double withdrawAmt = 1500.0;
		try {
			double bal = service.withdraw(acc.getMobile(), withdrawAmt);
			assertNotEquals(acc.getBalance(), bal);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Amount is less", e.getMessage());
			
		}
	}*/
	
	@Test
	public void testWithdrawCorrect(){
		acc.setMobile("4444444444");
		double withdrawAmt = 1500.0;
		try {
			double bal = service.withdraw(acc.getMobile(), withdrawAmt);
			assertNotEquals(acc.getBalance(), bal);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Exhausted Resultset", e.getMessage());
			
		}
	}
	
	@Test
	public void testFundTransferMobile1Valid(){
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setMobile("333333");
		acc2.setMobile("2222222222");
		double amount = 100;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}
	
	@Test
	public void testFundTransferMobile2Valid(){
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setMobile("3333333333");
		acc2.setMobile("222222");
		double amount = 100;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}
	
	@Test
	public void testFundTransferAmountValidation(){
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setMobile("3333333333");
		acc2.setMobile("4444444444");
		double amount = -100;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("amount should be greater than zero", e.getMessage());
		}
	}
	
	@Test
	public void testFundTransferMoreAmount(){
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setMobile("3333333333");
		acc2.setMobile("2222222222");
		double amount = 1500;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Exhausted Resultset", e.getMessage());
		}
	}	
	
	@Test
	public void testFundTransferNonExistingAmount(){
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setMobile("3333333333");
		acc2.setMobile("1234567890");
		double amount = 150;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Exhausted Resultset", e.getMessage());
		}
	}
	
	@Test
	public void testFundTransferExistingAmount(){
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setMobile("3333333333");
		acc2.setMobile("2222222222");
		double amount = 150;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testPrintTransaction(){
		acc = new Account();
		acc.setMobile("1111111111");
		try {
			Account ac = service.printTransaction(acc.getMobile());
			assertNotNull(ac);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			//System.out.println(e.getMessage());
		}
		
	}
	
	
}
