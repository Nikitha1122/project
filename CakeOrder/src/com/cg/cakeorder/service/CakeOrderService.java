package com.cg.cakeorder.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.cg.cakeorder.bean.CakeOrder;
import com.cg.cakeorder.bean2.Customer;
import com.cg.cakeorder.dao.CakeOrderDao;
import com.cg.cakeorder.dao.ICakeOrderDao;
import com.cg.cakeorder.exception.CakeException;

public class CakeOrderService implements ICakeOrderService {
	
	ICakeOrderDao cakeorderDao=new CakeOrderDao();

	@Override
	public int placeOrder(Customer customer, CakeOrder cake)
			throws CakeException {
		
		return cakeorderDao.placeOrder(customer, cake);
	}

	
	
	
	public double calculate(String type) throws CakeException
	{
		double cost=0;
		
		
		FileInputStream fis=null;
		try {
			fis=new FileInputStream("caketype.properties");
			Properties p=new Properties();
			p.load(fis);
			
			if(p.getProperty(type)==null){
				throw new CakeException("give valid name");}
			cost=Double.parseDouble(p.getProperty(type));
		} catch (FileNotFoundException e) {
			throw new CakeException(e.getMessage());
		} catch (IOException e) {
			throw new CakeException(e.getMessage());
		}
		
			try {
				fis.close();
			} catch (IOException e) {
				throw new CakeException(e.getMessage());
			}
			return cost;
		
			
	}




	@Override
	public boolean validaterequest(String phone) throws CakeException {
		// TODO Auto-generated method stub
		if(!phone.matches("\\d{10}"))
		{
			throw new CakeException("give correct 10 number");
		}
		return true;
	}




	@Override
	public CakeOrder getOrderDetails(int orderid) throws CakeException {
		// TODO Auto-generated method stub
		return cakeorderDao.getOrderDetails(orderid) ;
	}

}
