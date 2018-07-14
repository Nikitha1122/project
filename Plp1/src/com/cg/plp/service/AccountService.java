package com.cg.plp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

	@Override
	public Account withDraw(String mobile, double withdraw)
			throws WalletException {
		// TODO Auto-generated method stub
		if(!mobile.matches("\\d{10}"))
		{
			throw new WalletException("mobile number must contain exactly 10 digits");
		}
		Account acc=accountDao.withDraw(mobile);
		if(withdraw>acc.getbalance() || withdraw<=0)
		{
			throw new WalletException("amount should be withdrawn is lesser"
					+ " than available and greater than 0 ");
			
		}
		acc.setbalance(acc.getbalance()-withdraw);
		acc.setDate(LocalDate.now());
		return acc;
		
	
	}

	@Override
	public boolean fundTransfer(String sourcemobile, String destmobile,
			double transferAmt) throws WalletException {
		// TODO Auto-generated method stub
		if(!sourcemobile.matches("\\d{10}"))
		{
			throw new WalletException("mobile number should contain 10 digits");
		}
		if(!destmobile.matches("\\d{10}"))
		{
			throw new WalletException("mobile number should contain 10 digits");
		}
		IAccountService service=new AccountService();
		Account acc1=service.withDraw(sourcemobile, transferAmt);
		Account acc2=service.deposit(destmobile,transferAmt);
		return true;
	}

	

	@Override
	public Account deposit(String mobile, double depositAmt)
			throws WalletException {
		// TODO Auto-generated method stub
		if(!mobile.matches("\\{10}"))
		{
			throw new WalletException("Mobile number should contain 10 digits");
			
		}
		Account acc=accountDao.deposit(mobile);
		if(depositAmt<=0)
		{
			throw new WalletException("deposit amount must be greater than 0");
		}
		acc.setbalance(acc.getbalance()+depositAmt);
		acc.setDate(LocalDate.now());
		return acc;
		
	}

	

	

}
