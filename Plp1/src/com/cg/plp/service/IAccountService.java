package com.cg.plp.service;

import com.cg.plp.bean.Account;
import com.cg.plp.exception.WalletException;

public interface IAccountService {
	String createAccount(Account account) throws WalletException;
	boolean validatePhone(String mobile) throws WalletException;
	double showBalance(String mobile) throws WalletException;
	boolean validateName(String name) throws WalletException;
	boolean validateEmail(String email) throws WalletException;
	boolean validatebalance(double balance) throws WalletException;
	boolean validateAccount(Account account) throws WalletException;
	Account withDraw(String mobile,double withdraw) throws WalletException;
	boolean fundTransfer(String sourcemobile,String destmobile,double transferAmt) throws WalletException;
	Account deposit(String mobile,double depositAmt) throws WalletException;

	

}
