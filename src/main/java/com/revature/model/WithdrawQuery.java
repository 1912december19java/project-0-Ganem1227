package com.revature.model;

import com.revature.controller.Controller;
import com.revature.exception.AccountOverdrawnException;
import com.revature.service.*;

public class WithdrawQuery extends Query {

	public WithdrawQuery(String newName, Service service) {
		super(newName, service);
		intro = "---Withdraw Money---";
		prompt = "Amount to withdraw: ";
	}
	
	protected void interpretOption(double i) {
		try {
		  if(i >= 0) {
		    service.withdraw(i);
		  }else {
		    System.out.println("Please enter a positive number.");
		  }
			
		}catch(AccountOverdrawnException e) {
			System.out.println("Not enough funds");
			
		}finally {
			Controller.setState("optionScreen");
			finished = true;
		}
	}
	
}
