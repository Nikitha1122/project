package com.cg.cakeorder.dao;


import com.cg.cakeorder.bean.CakeOrder;
import com.cg.cakeorder.bean2.Customer;
import com.cg.cakeorder.exception.CakeException;

public interface ICakeOrderDao {
	int placeOrder(Customer customer, CakeOrder cake) throws CakeException;
	CakeOrder getOrderDetails(int orderid) throws CakeException;

}
