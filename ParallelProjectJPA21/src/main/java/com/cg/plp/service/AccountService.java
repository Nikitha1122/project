package com.cg.plp.service;

import com.cg.plp.bean.Account;
import com.cg.plp.dao.AccountDao;
import com.cg.plp.dao.IAccountDao;
import com.cg.plp.exception.WalletException;

public class AccountService implements IAccountService {
	IAccountDao accountDao = new AccountDao();

	
	

	public String createAccount(Account account) throws WalletException {
		// TODO Auto-generated method stub		
		return accountDao.createAccount(account);
	}
	
	public boolean validateAccount(Account acc) throws WalletException{
		if(validateMobile(acc.getMobile()) && validateName(acc.getName()) 
				&& validateEmail(acc.getEmail()) && validateBalance(acc.getBalance()))
			return true;
		return false;
	}

		public boolean validateMobile(String mobile) throws WalletException {
			if (!mobile.matches("\\d{10}"))
				throw new WalletException("Mobile number should contain 10 digits");
			return true;
		}
		public boolean validateName(String name) throws WalletException {
			if(name.isEmpty())
				throw new WalletException("Name cannot be empty");
			else
				if (!name.matches("[A-Za-z]{2,}"))
					throw new WalletException("Name must contain only alphabets");
			return true;
		}
		public boolean validateEmail(String email) throws WalletException {
			if (!email.matches("[A-Za-z0-9]{3,}@{1}[a-z]{2,}\\.com"))
				throw new WalletException("Invalid Email ID");
			return true;
		}
		public boolean validateBalance(double balance) throws WalletException {
			if (balance <= 0)
				throw new WalletException("Balance must be a number greater than zero");
			return true;
		}


	public double showBalance(String mobile) throws WalletException {
		// TODO Auto-generated method stub
		if(!validateMobile(mobile))
			throw new WalletException();
		return accountDao.showBalance(mobile);
	}


	public double deposit(String mobile, double amt) throws WalletException {
		// TODO Auto-generated method stub
		return accountDao.deposit(mobile, amt);
	}

	
	public double withdraw(String mobile, double amt) throws WalletException {
		// TODO Auto-generated method stub
		Account acc=accountDao.printTransaction(mobile);
		if (!mobile.matches("\\d{10}"))
			throw new WalletException("Mobile number should contain 10 digits");
		if(amt>acc.getBalance())
			throw new WalletException("Amount is less");
		
		return accountDao.withdraw(mobile, amt);
	}

	
	public boolean fundTransfer(String mobile1, String mobile2, double amount)
			throws WalletException {
		// TODO Auto-generated method stub
		if (!mobile1.matches("\\d{10}"))
			throw new WalletException("Mobile number should contain 10 digits");
		if (!mobile2.matches("\\d{10}"))
			throw new WalletException("Mobile number should contain 10 digits");
		if(amount<0)
			throw new WalletException("amount should be greater than zero");
		return accountDao.fundTransfer(mobile1, mobile2, amount);
	}
	

	public Account printTransaction(String mobile) throws WalletException {
		// TODO Auto-generated method stub
		return accountDao.printTransaction(mobile);
	}
}
