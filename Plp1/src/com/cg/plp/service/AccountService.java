package com.cg.plp.service;

import com.cg.plp.bean.Account;
import com.cg.plp.dao.AccountDao;
import com.cg.plp.dao.IAccountDao;
import com.cg.plp.exception.WalletException;

public class AccountService implements IAccountService {
	IAccountDao accountDao =new AccountDao();

	@Override
	public String createAccount(Account account) throws WalletException {
		// TODO Auto-generated method stub
		return accountDao.createAccount(account);
	}

	@Override
	public boolean validatePhone(String mobile) throws WalletException {
		// TODO Auto-generated method stub
		if(!mobile.matches("\\d{10}"))
			throw new WalletException("mobile number must contain exactly 10 digits");
		return true;
	}

	@Override
	public double showBalance(String mobile) throws WalletException {
		// TODO Auto-generated method stub
		
		return accountDao.showBalance(mobile);
	}

	@Override
	public boolean validateName(String name) throws WalletException {
		// TODO Auto-generated method stub
		if(name.isEmpty()||name==null)
		{
			throw new WalletException("Name cannot be empty");
		}
		else
		{
		if(!name.matches("[A-Z][A-Za-z]\\s{3,}"))
			throw new WalletException("name should start with capital letter");
		}
		return true;
	}

	@Override
	public boolean validateEmail(String email) throws WalletException {
		// TODO Auto-generated method stub
		if(!email.matches("[a-z0-9_]+@[a-z]+\\.com"))
			throw new WalletException("give valid email");
		return true;
	}

	@Override
	public boolean validatebalance(double balance) throws WalletException {
		// TODO Auto-generated method stub
		if(balance>0)
		{
			return true;
		}
		else
		{
			throw new WalletException("balance must be greater than zero");
		}
	}

	@Override
	public boolean validateAccount(Account account) throws WalletException {
		// TODO Auto-generated method stub
		if(validateName(account.getName()) && validatePhone(account.getMobile())  && 
				validateEmail(account.getEmail()) &&  validatebalance(account.getbalance()))
		{
			return true;
		}
		return false;
		
	}

}
