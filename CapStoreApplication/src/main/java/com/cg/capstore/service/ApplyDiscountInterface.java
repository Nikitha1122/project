package com.cg.capstore.service;

import com.cg.capstore.beans.ProductBean;
import com.cg.capstore.exception.DiscountException;

public interface ApplyDiscountInterface {
	public String Discount(String pid) throws DiscountException;

}
