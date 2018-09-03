package com.cg.capstore.service;


import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.capstore.beans.DiscountBean;
import com.cg.capstore.beans.ProductBean;
import com.cg.capstore.dao.DiscountRepo;
@Service
public class ApplyDiscountImpl implements ApplyDiscountInterface{

	@Autowired
	DiscountRepo repo;
	@Override
	public String Discount(String pid) {
		
		double totalPrice=0;
		String result="";
		ProductBean product=repo.getProduct(pid);
		Double price=product.getPrice();
		DiscountBean discount=product.getDiscount();
		double discountPercent=(double) discount.getDiscountPercent();
		Date discountDate=discount.getTimePeriod();  //discount date
		if(Date.valueOf(LocalDate.now()).before(discountDate) ||Date.valueOf(LocalDate.now()).equals(discountDate) ){
			System.err.println("true");
			double discountAmount=price*(discountPercent/100);
		    totalPrice=price-discountAmount;
		    result="final Price:"+totalPrice;
		} 
		else {
			System.err.println("false");
			totalPrice=price;
			result="Discount period has been expired\nfinal Price:"+totalPrice;
		}
		
		return result;
	}

}
