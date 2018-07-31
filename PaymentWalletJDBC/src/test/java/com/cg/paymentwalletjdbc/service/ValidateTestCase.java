package com.cg.paymentwalletjdbc.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.paymentwalletjdbc.dao.IPaymentDao;
import com.cg.paymentwalletjdbc.dao.PaymentDaoImpl;
import com.cg.paymentwalletjdbc.dto.AccountDto;
import com.cg.paymentwalletjdbc.exception.ValidateException;



public class ValidateTestCase {

	IAccountService service=new AccountServiceImpl();
	IPaymentDao dao=new PaymentDaoImpl();
		@Test
		public void CheckForZeroDeposittest() throws ValidateException {
			boolean condition=false;
			AccountDto account=new AccountDto();
			account.setUserId("9786503850");
			dao.creatAccount(account);
			condition=service.depositAmount("9786503850", 0.0);
			assertFalse(condition);
		}
		@Test
		public void CheckForValidAmount() throws ValidateException {
			boolean condition=false;
			AccountDto account=new AccountDto();
			account.setUserId("9786503850");
			dao.creatAccount(account);
			condition=service.depositAmount("9786503850", 500.0);
			assertTrue(condition);
			
		}
		@Test (expected=ValidateException.class)
		public void CheckForInvalidNameTest() throws ValidateException {
			AccountDto wallet=new AccountDto();
			wallet.setName("fd65f46");
			wallet.setPhNum("9786503850");
			wallet.setEmail("rohini@gmail.com");
			service.validate(wallet);
		}
		
		@Test
		public void CheckForValidNameTest() throws ValidateException {
			AccountDto wallet=new AccountDto();
			wallet.setName("Rohini");
			wallet.setPhNum("9786503850");
			wallet.setEmail("rohini@gmail.com");
			boolean condition=service.validate(wallet);
			assertTrue(condition);
		}
		
		@Test (expected=ValidateException.class)
		public void CheckForInvalidPhoneNumberTest() throws ValidateException {
			AccountDto wallet=new AccountDto();
			wallet.setName("Rohini");
			wallet.setPhNum("9789789");
			wallet.setEmail("abcd@gmail.com");
			boolean condition=service.validate(wallet);
			assertFalse(condition);
		}
		
		@Test
		public void CheckForValidPhoneNumberTest() throws ValidateException {
			AccountDto wallet=new AccountDto();
			wallet.setName("Rohini");
			wallet.setPhNum("9789789789");
			wallet.setEmail("ranjith@gmail.com");
			boolean condition=service.validate(wallet);
			assertTrue(condition);
		}
		
		@Test (expected=ValidateException.class)
		public void CheckForInvalidEmailTest() throws ValidateException {
			AccountDto wallet=new AccountDto();
			wallet.setName("Rohini");
			wallet.setPhNum("9786503850");
			wallet.setEmail("4gfgaff");
			boolean condition=service.validate(wallet);
			assertFalse(condition);
		}
		
		@Test
		public void CheckForValidEmailTest() throws ValidateException {
			AccountDto wallet=new AccountDto();
			wallet.setName("Rohini");
			wallet.setPhNum("9786503850");
			wallet.setEmail("rohini@gmail.com");
			boolean condition=service.validate(wallet);
			assertTrue(condition);
		}
}
