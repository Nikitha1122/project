package com.cg.capstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.capstore.exception.DiscountException;
import com.cg.capstore.service.ApplyDiscountInterface;

@RestController
public class DiscountController {
	
	@Autowired
	ApplyDiscountInterface service;
	
	@RequestMapping(value="/discount")
	public String  Discount(String pid) throws DiscountException
	{
		try {
			return service.Discount(pid);
		} catch (DiscountException e) {
			throw new DiscountException(e.getMessage());
		}
	}

}
