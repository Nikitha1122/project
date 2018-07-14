package com.cg.plp.dao;

import java.util.HashMap;

import com.cg.plp.bean.Account;
import com.cg.plp.db.AccountDB;
import com.cg.plp.exception.WalletException;

public class AccountDao implements IAccountDao {
	static HashMap<String, Account> accountmap=
			AccountDB.getAccountDb();

	@Override
	public String createAccount(Account account) throws WalletException {
		// TODO Auto-generated method stub
		if(accountmap.containsKey(account.getMobile()))
				throw new WalletException("account with id" +account.getMobile()+ " exists");
		accountmap.put(account.getMobile(), account);
		return account.getMobile();
	}

	@Override
	public double showBalance(String mobile) throws WalletException {
		// TODO Auto-generated method stub
		Account acc=accountmap.get(mobile);
		if(acc==null)
			throw new WalletException("Account does not exists");

		return acc.getbalance();
	}

	@Override
	public Account deposit(String mobile) throws WalletException {
		// TODO Auto-generated method stub
		Account acc=accountmap.get(mobile);
		if(acc==null)
			throw new WalletException("Mobile number does not exist");
		return acc;
	}

	@Override
	public Account withDraw(String mobile) throws WalletException {
		// TODO Auto-generated method stub
		Account acc=accountmap.get(mobile);
		if(acc==null)
			throw new WalletException("Mobile number does not exist");
		return acc;
	}

	@Override
	public Account printTransactionDetails(String mobile)
			throws WalletException {
		// TODO Auto-generated method stub
		Account acc=accountmap.get(mobile);
		if(acc==null)
		{
			throw new WalletException("The mobile number does not exist");
		}
		return acc;
		
	}

}
