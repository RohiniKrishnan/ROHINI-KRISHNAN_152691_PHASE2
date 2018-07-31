package com.cg.paymentwalletjdbc.dao;

import java.util.ArrayList;

import com.cg.paymentwalletjdbc.dto.AccountDto;
import com.cg.paymentwalletjdbc.exception.ValidateException;

public interface IPaymentDao  {
	public int creatAccount(AccountDto dto) throws ValidateException;
	public String getLogin(String userid, String pass) throws ValidateException;
	public double showBalance(String userid);
	public void depositAmount(String userId,double amount);
	public void withDrawAmount(String userId, double amount);
	public boolean fundTransfer(String senderUserId,String receiverUserId,double amount) throws ValidateException;
	public ArrayList<String> printTransactions(String userId);
}
