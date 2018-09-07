package com.cg.cakeorder.ui;

import java.time.LocalDate;
import java.util.Scanner;

import com.cg.cakeorder.bean.CakeOrder;
import com.cg.cakeorder.bean2.Customer;
import com.cg.cakeorder.exception.CakeException;
import com.cg.cakeorder.service.CakeOrderService;
import com.cg.cakeorder.service.ICakeOrderService;

public class Client {
	ICakeOrderService cakeservice=new CakeOrderService();
	
	Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		Client c=new Client();
		String option="";
		while(true)
		{
			System.out.println("1.place the cake order");
			System.out.println("2.display the cake order");
			System.out.println("3.Exit");
			System.out.println("enter your choice");
			option=c.sc.nextLine();
			switch(option)
			{
			case "1":
				c.placeOrder();
				break;
			case "2":
				c.displayOrder();
				break;
			case "3":
				System.exit(0);
				default:
					System.out.println("please enter option in between 1 and 3 ");
					
			}
			
		}
	}
	
	private void placeOrder()
	{
		Customer cus=new Customer();
		CakeOrder cake=new CakeOrder();
		CakeOrderService cs=new CakeOrderService();
		System.out.println("enter the customer name");
		cus.setCustName(sc.nextLine());
		System.out.println("enter the address");
		cus.setAddress(sc.nextLine());
		System.out.println("enter phone number");
		cus.setPhone(sc.nextLine());
		System.out.println("enter type of cake");
		String type=sc.nextLine();
		try {
			boolean result=cakeservice.validaterequest(cus.getPhone());
			double price=cs.calculate(type);
			price +=500.0;
			cake.setTotalPrice(price);
			System.out.println("price" +cake.getTotalPrice());
			System.out.println("order date:"+LocalDate.now());
			if(result)
			{
				int id=cakeservice.placeOrder(cus, cake);
				System.out.println("cake order is successfully places with order id:" +id);
				
			}
		} catch (CakeException e) {
			System.out.println();
			System.err.println(e.getMessage());
			System.out.println();
			
		}
		
		
	}
	
	private void displayOrder()
	{
		System.out.println("enter orderid");
		int id=Integer.parseInt(sc.nextLine());
		CakeOrder co;
		try {
		co=cakeservice.getOrderDetails(id);
		System.out.println("custid" +co.getCustomerId());
		System.out.println("orderid" +co.getOrderId());
		System.out.println("total" +co.getTotalPrice());
	} catch (CakeException e) {
		System.out.println();
		System.err.println(e.getMessage());
		System.out.println();
		
	}
		
	}
	

}
