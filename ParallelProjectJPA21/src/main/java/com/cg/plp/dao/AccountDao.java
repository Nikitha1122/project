package com.cg.plp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.plp.bean.Account;
import com.cg.plp.exception.WalletException;
import com.cg.plp.util.EMUtil;


public class AccountDao implements IAccountDao {


	


	public String createAccount(Account account) throws WalletException{

		EntityManager em = EMUtil.getEntitiManager();

		em.getTransaction().begin();
		em.merge(account);
		em.getTransaction().commit();

		em.close();

		return account.getMobile();
	}




	public double showBalance(String mobile) throws WalletException {

		Account acc = getAccountDetails(mobile);
		return acc.getBalance();



	}

	public double deposit(String mobile, double amt) throws WalletException {

		Account account = getAccountDetails(mobile);
		double newBal = account.getBalance() + amt;

		account.setBalance(newBal);
	//	account.setModDate("" + new Date());

		EntityManager em = EMUtil.getEntitiManager();

		em.getTransaction().begin();
		em.merge(account);
		em.getTransaction().commit();

		em.close();
		return account.getBalance();	

	}

	public double withdraw(String mobile, double amt) throws WalletException {

		Account account = getAccountDetails(mobile);
		double newBal = account.getBalance() - amt;

		account.setBalance(newBal);
		//account.setModDate("" + new Date());

		EntityManager em = EMUtil.getEntitiManager();

		em.getTransaction().begin();
		em.merge(account);
		em.getTransaction().commit();

		em.close();
		return account.getBalance();

	}

	public boolean fundTransfer(String mobile1, String mobile2, double amount) throws WalletException {
		withdraw(mobile1, amount);
		deposit(mobile2, amount);

		return true;

	}

	public Account printTransaction(String mobile) throws WalletException {
		Account account = getAccountDetails(mobile);

		return account;
	}
	private Account getAccountDetails(String mobile) throws WalletException { //
		// TODO Auto-generated method stub

		EntityManager em = EMUtil.getEntitiManager();
		Account acc = null;
		try {
			TypedQuery<Account> accQuery = em.createQuery("SELECT a FROM Account a WHERE MOBILE=?", Account.class);
			accQuery.setParameter(1, mobile);

			acc = accQuery.getSingleResult();
			em.close();
		} catch (NoResultException e) {
			// TODO: handle exception
			throw new WalletException("Account does not exist");
		}
		return acc;
	}

}


