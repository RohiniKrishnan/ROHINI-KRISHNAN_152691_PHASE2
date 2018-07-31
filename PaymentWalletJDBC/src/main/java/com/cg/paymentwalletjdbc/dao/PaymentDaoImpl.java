package com.cg.paymentwalletjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import com.cg.paymentwalletjdbc.dto.AccountDto;
import com.cg.paymentwalletjdbc.exception.IValidateException;
import com.cg.paymentwalletjdbc.exception.ValidateException;
import com.cg.paymentwalletjdbc.util.DBUtil;

public class PaymentDaoImpl<EntityManager> implements IPaymentDao {
	
	public int creatAccount(AccountDto account)throws ValidateException {
		
		Connection con= DBUtil.getConnection();
		int row = 0;
		 String sql="Insert into account values (?,?,?,?,?,?)";
		try {
			PreparedStatement statement= con.prepareStatement(sql);
			
			statement.setString(1, account.getName());
			statement.setString(2, account.getEmail());
			statement.setString(3, account.getPhNum());
			statement.setDouble(4, account.getBalance());
			statement.setString(5, account.getUserId());
			statement.setString(6, account.getPassWord());
			
			row=statement.executeUpdate();
			con.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return row;
		
	}
	

	public String getLogin(String userid, String pass) throws ValidateException {
		Connection connection = DBUtil.getConnection();
		
		String user=null;
		String password=null;
		String sql="Select * from account where userid ='"+userid+"'And Password Like'"+pass+"'";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set=statement.executeQuery();
			while(set.next()) {
				password=set.getString("password");
			}
			user=userid;
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(password.equals(pass)) {
			return user;
		}
		else
		
		return null;
				
	}

	public double showBalance(String userid) {
		Connection connection = DBUtil.getConnection();
		String sql="Select * from account where userid ='"+userid+"'";
		double balance=0;
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			ResultSet set=statement.executeQuery();
			
			while(set.next()) {
				balance=set.getDouble("balance");
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
		
	}

	public void depositAmount(String userId, double amount) {
		Connection connection = DBUtil.getConnection();
		String sql="Update Account set balance=balance+"+amount+"where userId='"+userId+"'";
		String sqlT="Insert into transaction values(?,?)";
		try {
			PreparedStatement statement=connection.prepareStatement(sql);
			PreparedStatement statementT=connection.prepareStatement(sqlT);
			statementT.setString(1,userId);
			statementT.setString(2, amount+"Deposited");
			
			statement.execute();
			statementT.execute();
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public void withDrawAmount(String userId, double amount) {
		Connection con = DBUtil.getConnection();

		String sql = "Update account set balance=balance-" + amount + " where userId ='" + userId + "'";
		String sqlT = "Insert into transaction values (?,?)";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			PreparedStatement statementT = con.prepareStatement(sqlT);
			statementT.setString(1, userId);
			statementT.setString(2, amount + " Withdrawn ");
			statementT.execute();
			statement.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean fundTransfer(String senderUserId, String receiverUserId, double amount) throws ValidateException {
		Connection con = DBUtil.getConnection();
		boolean result = false;
		int row1 = 0;
		int row2 = 0;

		String sql1 = "update account set balance=balance+" + amount + " where userid='" + senderUserId + "'";
		String sql1T = "Insert into transaction values (?,?)";

		String sql2 = "update account set balance=balance-" + amount + " where userid='" + receiverUserId + "'";
		String sql2T = "Insert into transaction values (?,?)";
		try {
			PreparedStatement stmt1 = con.prepareStatement(sql1);
			PreparedStatement statement1T = con.prepareStatement(sql1T);
			statement1T.setString(1, receiverUserId);
			statement1T.setString(2, amount + " Received From " + senderUserId);

			PreparedStatement stmt2 = con.prepareStatement(sql2);
			PreparedStatement statement2T = con.prepareStatement(sql2T);
			statement2T.setString(1, senderUserId);
			statement2T.setString(2, amount + " Sent to " + receiverUserId);

			statement1T.execute();
			statement2T.execute();
			row1 = stmt1.executeUpdate();
			row2 = stmt2.executeUpdate();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if ((row1 != 0) && (row2 != 0)) {
			result = true;
		} else
			throw new ValidateException(IValidateException.Error5);
		return result;

	}
	public ArrayList<String> printTransactions(String userId) {

		Connection con = DBUtil.getConnection();
		String sql = "Select transactionInfo from transaction where userid='" + userId + "'";
		ArrayList<String> list = new ArrayList<String>();
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				list.add(set.getString("transactionInfo"));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	
}
