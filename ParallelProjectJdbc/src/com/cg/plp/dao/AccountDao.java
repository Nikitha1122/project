package com.cg.plp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.cg.plp.bean.Account;
import com.cg.plp.exception.WalletException;
import com.cg.plp.util.DBUtil;

public class AccountDao implements IAccountDao {

	
	public String createAccount(Account account) throws WalletException{
		Connection connection = DBUtil.getConnection();
		try {
			PreparedStatement preparedStatement=connection.
	    	prepareStatement("INSERT INTO account values(?,?,?,?,?)");
			preparedStatement.setString(1, account.getName());
			preparedStatement.setString(2, account.getMobile());
			preparedStatement.setString(3, account.getEmail());
			preparedStatement.setDouble(4, account.getBalance());
			preparedStatement.setDate(5, account.getDate());
			
			int result=preparedStatement.executeUpdate();
			
			if(result==1)
			{
				Statement stat=connection.createStatement();
				ResultSet rs=stat.executeQuery("SELECT MOBILE FROM ACCOUNT");
				
				if(rs!=null)
					rs.next();
				String id=rs.getString(1);
				return id;
			}
			else
			{
				throw new WalletException("Unable to insert into account");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new WalletException(e.getMessage());
		}
	}
	
	
	
	@Override
	public double showBalance(String mobile) throws WalletException {
		
		Connection con=DBUtil.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select balance from account where mobile=?");
			ps.setString(1, mobile);
			ResultSet rs=ps.executeQuery();
			if(rs!=null)
				rs.next();
			Double m=rs.getDouble("balance");
			return m;
			
		} catch (SQLException e) {
			throw new WalletException(e.getMessage());
		}
		
		
		
	}

	@Override
	public double deposit(String mobile, double amt) throws WalletException {
		Connection con=DBUtil.getConnection();
		PreparedStatement ps;
		PreparedStatement ps1;
		double bal1=0;
		
		try {
			ps = con.prepareStatement("select * from account where mobile=?");
			ps.setString(1, mobile);
			ResultSet rs=ps.executeQuery();
			if(rs!=null)
			{
				rs.next();
				Account acc=new Account();
				double bal=rs.getDouble("balance")+amt;
				acc.setName(rs.getString("name"));
				acc.setEmail(rs.getString("email"));
				acc.setMobile(rs.getString("mobile"));
				acc.setBalance(bal);
				ps1=con.prepareStatement("update account set balance=? where mobile=?");
				ps1.setDouble(1, acc.getBalance());
				ps1.setString(2, acc.getMobile());
				int result=ps1.executeUpdate();
				if(result==1)
				{
					bal1=acc.getBalance();
				}
				else
				{
					throw new WalletException("account with mobile does not exist");
				}
				return bal1;
				
				
			}
			else
			{
				throw new WalletException("account with mobile does not exist");
			}
		} catch (SQLException e) {
			throw new WalletException(e.getMessage());
		}
		
		
	}

	@Override
	public double withdraw(String mobile, double amt) throws WalletException {
		
		Connection con=DBUtil.getConnection();
		PreparedStatement ps;
		PreparedStatement ps1;
		double bal1=0;
		
		try {
			ps = con.prepareStatement("select * from account where mobile=?");
			ps.setString(1, mobile);
			ResultSet rs=ps.executeQuery();
			if(rs!=null)
			{
				rs.next();
				Account acc=new Account();
				double bal=rs.getDouble("balance")-amt;
				acc.setName(rs.getString("name"));
				acc.setEmail(rs.getString("email"));
				acc.setMobile(rs.getString("mobile"));
				acc.setBalance(bal);
				ps1=con.prepareStatement("update account set balance=? where mobile=?");
				ps1.setDouble(1, acc.getBalance());
				ps1.setString(2, acc.getMobile());
				int result=ps1.executeUpdate();
				if(result==1)
				{
					bal1=acc.getBalance();
				}
				else
				{
					throw new WalletException("account with mobile does not exist");
				}
				return bal1;
				
				
			}
			else
			{
				throw new WalletException("account with mobile does not exist");
			}
		} catch (SQLException e) {
			throw new WalletException(e.getMessage());
		}
		
		
		
		
	}

	@Override
	public boolean fundTransfer(String mobile1, String mobile2, double amount) throws WalletException {
		double balance=deposit(mobile1, amount);
		double balance1=withdraw(mobile2, amount);
		return true;
		
	}
	
	@Override
	public Account printTransaction(String mobile) throws WalletException {
		
		Connection con=DBUtil.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from account where mobile=?");
			ps.setString(1, mobile);
			ResultSet rs=ps.executeQuery();
			if(rs!=null)
			{
				rs.next();
				Account acc=new Account();
				acc.setName(rs.getString("name"));
				acc.setEmail(rs.getString("email"));
				acc.setMobile(rs.getString("mobile"));
				acc.setBalance(rs.getDouble("balance"));
				return acc;
			}
			else
			{
				throw new WalletException("account with mobile number does not exist");
			}
		} catch (SQLException e) {
			throw new WalletException(e.getMessage());
		}
		
		
		
		
		
	
}
}
