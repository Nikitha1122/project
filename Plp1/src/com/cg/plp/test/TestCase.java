package com.cg.plp.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cg.plp.bean.Account;
import com.cg.plp.exception.WalletException;
import com.cg.plp.service.AccountService;
import com.cg.plp.service.IAccountService;

public class TestCase {
	Account account;
	IAccountService service;
	
	@Before
	public void init()
	{
		service=new AccountService();
	}
	
	@Test
	//Wrong mobile number
	public void testAccountMobile()
	{
		account=new Account();
		account.setName("Rosy");
		account.setMobile("77887");
		account.setEmail("rosy1@cg.com");
		account.setbalance(4000.0);
		
		try {
			if(service.validatePhone(account.getMobile()))
			{
				String m=service.createAccount(account);
				System.out.println("Account created for mobile id:" +m);
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println("wrong mobile :" +e.getMessage());
			assertEquals("mobile number must contain exactly 10 digits", e.getMessage());
		}
		
		
	}
	
	@Test
	//Wrong name
	public void testAccountName()
	{
		account=new Account();
		account.setName("rosy");
		account.setMobile("5555555555");
		account.setEmail("rosy1@cg.com");
		account.setbalance(4000.0);
	
		try {
			if(service.validateName(account.getName()))
			{
				service.createAccount(account);
				System.out.println("account created");
		
		}
		}catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println("wrong name :" +e.getMessage());
			assertEquals("name should start with capital letter", e.getMessage());
		}
			
		}
	@Test
	//Wrong email
	public void testAccountEmail()
	{
		account=new Account();
		account.setName("Rosy");
		account.setMobile("5555555555");
		account.setEmail("rosy1cg.com");
		account.setbalance(4000.0);
		
		try {
			if(service.validateEmail(account.getEmail()))
			{
				service.createAccount(account);
				System.out.println("account created");
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println("wrong emial :" +e.getMessage());
			assertEquals("give valid email", e.getMessage());
		}
	}
	
	@Test
	//balance less than 0
	public void testAccountbalance()
	{
		account=new Account();
		account.setName("Rosy");
		account.setMobile("5555555555");
		account.setEmail("rosy1@cg.com");
		account.setbalance(-4000);
		
		try {
			if(service.validatebalance(account.getbalance()))
			{
				service.createAccount(account);
				System.out.println("account created");
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println("give correct balance :" +e.getMessage());
			assertEquals("balance must be greater than zero", e.getMessage());
		}
	}
	
	@Test
	//empty name
	public void testaccountEmptyName()
	{
		account=new Account();
		account.setName("");
		account.setMobile("5555555555");
		account.setEmail("rosy1@cg.com");
		account.setbalance(4000.0);
		
		try {
			if(service.validateName(account.getName()))
			{
				service.createAccount(account);
				System.out.println("account created");
			}
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println("give name :" +e.getMessage());
			assertEquals("Name cannot be empty", e.getMessage());
		}
	}
	
	@Test
	public void testCreateAccount()
	{
		account=new Account();
		account.setName("Rosy");
		account.setMobile("5555555555");
		account.setEmail("rosy1@cg.com");
		account.setbalance(4000.0);
		
		String mobile;
		try {
			mobile = service.createAccount(account);
			assertNotNull(mobile);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	
	@Test
	public void testExistingAccount()
	{
		account=new Account();
		//account.setName("Rosy");
		account.setMobile("1111111111");
		//account.setEmail("rosy1@cg.com");
		//account.setbalance(4000.0);
		
		try {
			service.createAccount(account);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("account with id" +account.getMobile()+ " exists", e.getMessage());
		}
	}
	
	@Test
	public void testShowBalanceNonExistingAccount()
	{
		account=new Account();
		account.setMobile("45612");
		try {
			service.showBalance(account.getMobile());
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			assertEquals("Account does not exists", e.getMessage());
		}
		
	}
	
	@Test
	public void testAccountExisting()
	{
		
	}
		
	
	

	/*@Test
	public void test() {
		
	}*/


}
