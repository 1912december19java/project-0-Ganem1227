package com.revature.model;

import com.revature.controller.Controller;
import com.revature.service.Service;

public class DepositQuery extends Query {
	public DepositQuery(String newName) {
		super(newName);
		intro = "---Deposit Money---";
		prompt = "Amount of money to deposit: ";
	}
	
	protected void interpretOption(double i) {
		Service.deposit(i);
		finished = true;
		Controller.setState("optionScreen");
	}
}
