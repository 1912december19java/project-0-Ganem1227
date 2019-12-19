package com.revature.controller;

import com.revature.service.*;

public class WithdrawQuery extends Query {

	public WithdrawQuery(String newName) {
		super(newName);
		intro = "---Withdraw Money---";
		prompt = "Amount to withdraw: ";
	}
	
	protected void interpretOption(double i) {
		try {
			Service.withdraw(i);
			finished = true;
		}catch(AccountOverdrawnException e) {
			System.out.println("Not enough funds");
		}finally {
			Controller.setState("optionScreen");
		}
	}
	
}
