package com.cg.paymentwalletjdbc.service;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.cg.paymentwalletjdbc.dto.AccountDto;
import com.cg.paymentwalletjdbc.exception.ValidateException;


public interface IAccountService {
	public int creatAccount(AccountDto dto) throws ValidateException;
	public String getLogin(String userid, String pass) throws ValidateException;
	public double showBalance(String userid);
	public boolean depositAmount(String userId,double amount);
	public boolean withDrawAmount(String userId, double amount);
	public boolean fundTransfer(String senderNumber,String receiverNumber,double amount) throws ValidateException;
	public ArrayList<String> printTransactions(String userId);
	public boolean validate(AccountDto accountdto) throws ValidateException;
}
