package com.cg.paymentwalletjdbc.service;

import java.util.ArrayList;

import com.cg.paymentwalletjdbc.dao.PaymentDaoImpl;
import com.cg.paymentwalletjdbc.dto.AccountDto;
import com.cg.paymentwalletjdbc.exception.IValidateException;
import com.cg.paymentwalletjdbc.exception.ValidateException;

public class AccountServiceImpl implements  IAccountService{
	
	AccountDto dto=new AccountDto();
	PaymentDaoImpl dao=new PaymentDaoImpl();
		
	public boolean validate(AccountDto dto) throws ValidateException {
		boolean result=false;
		String namePattern="[A-Z a-z]+";
		String noPattern="[0-9]{10}";
		String emailPattern="[a-z]{1}[a-z 0-9_]+@gmail.com";
		if(dto.getName().matches(namePattern)) {
			if(dto.getPhNum().matches(noPattern)) {
				if(dto.getEmail().matches(emailPattern)) {
					result=true;
				}else throw new ValidateException(IValidateException.Error3);
			}else throw new ValidateException(IValidateException.Error2);
		}else throw new ValidateException(IValidateException.Error1);
		return result;
	}
		public int creatAccount(AccountDto dto) throws ValidateException {
				int row;
		row=dao.creatAccount(dto);
		return row;
	}
		public String getLogin(String userid, String pass) throws ValidateException {
			if(dao.getLogin(userid,pass)!=null) {
				return dao.getLogin(userid, pass);
			}
			else throw new ValidateException(IValidateException.Error4);
				
		}
		
	
		public double showBalance(String userid) {
		 return dao.showBalance(userid);
	}
		public boolean depositAmount(String userId,double amount) {
			boolean result= false;
					if(amount>0) {
						dao.depositAmount(userId, amount);
						result=true;
					}
		return result;
	}
	
	public boolean withDrawAmount(String userId, double amount) {
		boolean result=false;
		if(dao.showBalance(userId) >= amount) {
			dao.withDrawAmount(userId, amount);
			result=true;
		}
		return result;
	}
	public boolean fundTransfer(String senderUserId,String receiverUserId,double amount) throws ValidateException {
		boolean result=false;
		if(dao.showBalance(senderUserId)>=amount) {
			if(dao.fundTransfer(senderUserId, receiverUserId, amount)) {
				result=true;
			}
		}
		return result;
	}
	public ArrayList<String> printTransactions(String userId) {
		ArrayList<String> list = dao.printTransactions(userId);
		return list;
	}
	
	
}
