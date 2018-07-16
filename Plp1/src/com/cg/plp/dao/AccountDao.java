package com.cg.plp.dao;

import java.util.HashMap;

import com.cg.plp.bean.Account;
import com.cg.plp.db.AccountDB;
import com.cg.plp.exception.WalletException;

public class AccountDao implements IAccountDao {
static HashMap<String, Account> accMap = AccountDB.getAccountDb();
	
	public String createAccount(Account account) throws WalletException{
		if(accMap.containsKey(account.getMobile()))
			throw new WalletException("Account already exists in the Database");
		accMap.put(account.getMobile(), account);
		return account.getMobile();
	}
	
	@Override
	public Account getAccountDetails(String mobile) throws WalletException {
		// TODO Auto-generated method stub
		try {
			Account acc = accMap.get(mobile);
			return acc;
		} catch (Exception e) {
			// TODO: handle exception
			throw new WalletException(e.getMessage());
		}
	}
	
	@Override
	public double showBalance(String mobile) throws WalletException {
		// TODO Auto-generated method stub
		Account acc = accMap.get(mobile);
		if(acc == null)
			throw new WalletException("Account Does not exist");
		return acc.getBalance();
	}

	@Override
	public double deposit(String mobile, double amt) throws WalletException {
		// TODO Auto-generated method stub
		Account acc = accMap.get(mobile);
		double finalBal = acc.getBalance() + amt; 
		acc.setBalance(finalBal);
		return finalBal;
	}

	@Override
	public double withdraw(String mobile, double amt) throws WalletException {
		// TODO Auto-generated method stub
		Account acc = accMap.get(mobile);
		if(acc.getBalance() < amt)
			throw new WalletException("Account balance is Low");
		double withdrawAmount =  acc.getBalance()- amt;
		acc.setBalance(withdrawAmount);
		return withdrawAmount;
	}

	@Override
	public boolean fundTransfer(String mobile1, String mobile2, double amount) throws WalletException {
		// TODO Auto-generated method stub
		Account acc1 = accMap.get(mobile1);
		Account acc2 = accMap.get(mobile2);
		
		if(acc1 == null || acc2 == null)
			throw new WalletException("Account doesnot exist. Amount can't be transferred");
		
		if(amount > acc1.getBalance())
			throw new WalletException("Account balance is low");
		double bal = acc1.getBalance()-amount;			//withdraw from mobile1
		acc1.setBalance(bal);	
		acc2.setBalance(acc2.getBalance()+amount);		//deposit in mobile2
		return true;
	}

	
	

}
