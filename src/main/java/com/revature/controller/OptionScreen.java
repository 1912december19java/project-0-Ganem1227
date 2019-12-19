package com.revature.controller;

import com.revature.service.*;

public class OptionScreen extends Menu {
	
	public OptionScreen(String newName) {
		super(newName);
		intro = "---Hello, your bank balance is " + Service.getBalance() + "---";
		prompt = "What would you like to do today?"; 
		options.add("Make a deposit");
		options.add("Make a withdrawal");
		options.add("Transfer money to another account");
		options.add("View previous transactions");
		options.add("Log Out");
		
		hasIntro = true;
	}
	
	protected void interpretOptions(int i) {
		switch(i) {
		case 1 : finished = true;
				 Controller.setState("deposit");
				 break;
		case 2 : finished = true;
				 Controller.setState("withdraw");
				 break;
		case 3 : break;
		case 4 : break;
		case 5 : System.out.println("Logging out, thanks for coming!");
		 		 Controller.setState("mainMenu");
		 		 Service.resetStaticFields();
		 		 finished = true;
		 		 break;
		default : System.out.println("Please input a value in range 1-" + (options.size()));
					  break;
		}
	}
	
	public void show() {
		intro = "---Hello " + Service.getUserName() + ", your bank balance is " + Service.getBalance() + "---";
		super.show();
	}
	
}
