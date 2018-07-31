package com.cg.paymentwalletjdbc.dto;

import java.util.ArrayList;

public class AccountDto extends CustomerDto {
	private double balance;
	private ArrayList<String> transaction = new ArrayList<String>();

	public AccountDto() {
			
	}

	public ArrayList<String> getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction.add(transaction);
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
