package com.cg.cakeorder.db;

import java.util.HashMap;

import com.cg.cakeorder.bean.CakeOrder;
import com.cg.cakeorder.bean2.Customer;

public class CakeOrderDb {
	private static HashMap<Integer, CakeOrder> cakeDb=
			new HashMap<Integer, CakeOrder>();
	
	private static HashMap<Integer, Customer> customerDb=
			new HashMap<Integer, Customer>();

	public static HashMap<Integer, CakeOrder> getCakeDb() {
		return cakeDb;
	}

	public static HashMap<Integer, Customer> getCustomerDb() {
		return customerDb;
	}

}
