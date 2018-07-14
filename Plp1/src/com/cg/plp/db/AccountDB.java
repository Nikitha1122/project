package com.cg.plp.db;

import java.time.LocalDate;
import java.util.HashMap;

import com.cg.plp.bean.Account;

public class AccountDB {
	private static HashMap<String, Account> accountDb=new HashMap<String, Account>();

	public static HashMap<String, Account> getAccountDb() {
		return accountDb;
	}
	static
	{
		accountDb.put("1111111111", new Account("Jhon", "1111111111", 
				"jhon@cg.com", 50000.0, LocalDate.now()));
		accountDb.put("2222222222", new Account("Mark", "2222222222", 
				"mark@cg.com", 30000.0, LocalDate.now()));
		accountDb.put("3333333333", new Account("sara", "3333333333", 
				"sara@cg.com", 25000.0, LocalDate.now()));
	}

}
