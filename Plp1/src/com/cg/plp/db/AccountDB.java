package com.cg.plp.db;

import java.time.LocalDate;
import java.util.HashMap;

import com.cg.plp.bean.Account;

public class AccountDB {
	private static HashMap<String, Account> accountDb = new HashMap<String, Account>();

	public static HashMap<String, Account> getAccountDb() {
		return accountDb;
	}

	static {
		accountDb.put("1111111111", new Account("nikitha", "1111111111",
				"nikki@gmail.com", 500.0, LocalDate.now()));
		
		accountDb.put("2222222222", new Account("Ramesh", "2222222222",
				"ramesh@gmail.com", 4000.0, LocalDate.now()));
		
		accountDb.put("3333333333", new Account("Shruthi", "3333333333",
				"shruthi@gmail.com", 2100.0, LocalDate.now()));
		
		accountDb.put("4444444444", new Account("Anjali", "4444444444",
				"anjali@gmail.com", 6000.0, LocalDate.now()));

	}

}
