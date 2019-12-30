package com.revature.model;

import java.util.InputMismatchException;
import com.revature.controller.Controller;
import com.revature.service.Service;

public class TransferScreen extends Screen {

	public TransferScreen(String newName) {
		super(newName);
		intro = "---Transfer Money to Recipient---";
		prompt = "Please Enter the Recipient's Username: ";
		finished = false;
	}
	
	public void show(){
		super.show();
		boolean recipientFound = false;
		double amount = 0.0;
		String recipient = "";
		
		while(!recipientFound) {
			
			System.out.print(prompt);
			recipient = Controller.CONSOLE_INPUT.nextLine();
			//Service checks the username
			recipientFound = Service.checkDatabaseForUsername(recipient);
		}
		
		prompt = "Enter amount to transfer: ";
		while(!finished) {
			try {
				System.out.print(prompt);
				amount = Controller.CONSOLE_INPUT.nextDouble();
				finished = Service.transferMoney(recipient, amount);
				
				if(!finished) {
					System.out.println("Not enough funds to complete transaction.");
					finished = true;
				}
				
			}catch(InputMismatchException e) {
				System.out.println("Invalid input, please try again.");
			}finally {
				Controller.CONSOLE_INPUT.nextLine();
			}
		}
		
		Controller.setState("optionScreen");
	}
	
}
