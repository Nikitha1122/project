package com.cg.plp.dao;

import com.cg.plp.bean.Account;
import com.cg.plp.exception.WalletException;

public interface IAccountDao {
String createAccount(Account account) throws WalletException;
double showBalance(String mobile) throws WalletException;
Account deposit(String mobile) throws WalletException;
Account withDraw(String mobile) throws WalletException;
Account printTransactionDetails(String mobile) throws WalletException;

}
