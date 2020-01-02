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
	  if(i >= 0) {
		Service.deposit(i);
	  }else {
	    System.out.println("Invalid input. Please input a positive value.");
	  }
	  finished = true;
	  Controller.setState("optionScreen");
	}
}
