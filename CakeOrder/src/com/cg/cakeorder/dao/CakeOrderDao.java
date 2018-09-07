package com.cg.cakeorder.dao;

import java.util.HashMap;
import java.util.Random;

import com.cg.cakeorder.bean.CakeOrder;
import com.cg.cakeorder.bean2.Customer;
import com.cg.cakeorder.exception.CakeException;
import com.cg.cakeorder.db.*;

/**
 * Class Name: CakeOrderDao
 * Interface implemented:ICakeOrderDao
 * No.of methods:2
 * purpose :data access
 * @author npannala
 *Date of Creation:
 */
public class CakeOrderDao implements ICakeOrderDao {
	static HashMap<Integer, Customer> customerMap=
			CakeOrderDb.getCustomerDb();
	
	static HashMap<Integer, CakeOrder> cakeMap=
			CakeOrderDb.getCakeDb();
	
	Random rand=new Random();

	
	/**
	 * Name of method:placeOrder
	 * parameters expected:Customer object and CakeOrder object
	 * Return type:int,orderid
	 * purpose:update the order details in the map.
	 */

	@Override
	public int placeOrder(Customer customer, CakeOrder cake)
			throws CakeException {
		int customerId=rand.nextInt(1000);
		int orderId=rand.nextInt(1000);
		cake.setCustomerId(customerId);
		cake.setOrderId(orderId);
		
		customerMap.put(customerId, customer);
		cakeMap.put(orderId, cake);
		return orderId;
	}

	@Override
	public CakeOrder getOrderDetails(int orderid) throws CakeException {
		CakeOrder co=cakeMap.get(orderid);
		if(co==null)
		{
			throw new CakeException("cake with" +orderid+ "does not exist");
		}
		return co;
	}

	
	

}
