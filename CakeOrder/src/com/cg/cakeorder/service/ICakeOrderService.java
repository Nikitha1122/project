package com.cg.cakeorder.service;

import com.cg.cakeorder.bean.CakeOrder;
import com.cg.cakeorder.bean2.Customer;
import com.cg.cakeorder.exception.CakeException;

public interface ICakeOrderService {
	int placeOrder(Customer customer, CakeOrder cake) throws CakeException;
    boolean validaterequest(String phone) throws CakeException;
    CakeOrder getOrderDetails(int orderid) throws CakeException;


}
