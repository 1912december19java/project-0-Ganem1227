package com.revature.controller;

import com.revature.service.Service;

public class RegisterQuery extends Screen {
	public RegisterQuery(String newName) {
		super(newName);
	}
	
	public void show() {
		
		boolean userValid = false;
		super.show();
		String newUsername = "";
		String newPassword = "";
		String confirmPassword = "";
		
		while(!userValid) {
			System.out.print("Enter your new username: ");
			newUsername = Controller.CONSOLE_INPUT.nextLine();
			userValid = Service.checkUsername(newUsername);
		}
		
		while(!finished) {
			System.out.print("Enter your new password: ");
			newPassword = Controller.CONSOLE_INPUT.nextLine();
			System.out.print("Confirm your new password: ");
			confirmPassword = Controller.CONSOLE_INPUT.nextLine();
			
			if (newPassword.equals(confirmPassword)) finished = true;
			else System.out.println("Password mismatch, please reenter your new password.");
		}
		
		Controller.setState("optionScreen");
	}
}
