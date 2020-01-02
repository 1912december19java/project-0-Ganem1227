package com.revature.model;

import java.util.List;
import com.revature.controller.Controller;
import com.revature.service.*;

public class OptionScreen extends Menu {
	
	public OptionScreen(String newName, Service service) {
		super(newName, service);
		//intro = "---Hello, your bank balance is $" + Service.getBalance() + "---";
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
		case 3 : finished = true;
				 Controller.setState("transfer");
				 break;
		case 4 : printTransactionHistory();
				 break;
		case 5 : System.out.println("Logging out, thanks for coming!");
		 		 Controller.setState("mainMenu");
		 		 service.resetStaticFields();
		 		 finished = true;
		 		 break;
		default : System.out.println("Please input a value in range 1-" + (options.size()));
					  break;
		}
	}
	
	public void show() {
		intro = "---Hello " + Service.getUserName() + ", your bank balance is $" + service.getBalance() + "---";
		service.fetchUserId();
		super.show();
	}
	
	public void printTransactionHistory() {
		List<String> history = service.getTransactionHistory();
		System.out.println("---Transaction History---");
		
		for (int i = 0; i < history.size(); i++) {
			System.out.println(history.get(i));
		}
	}
	
}
