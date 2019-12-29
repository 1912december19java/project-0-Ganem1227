package com.revature.controller;

import com.revature.service.Service;

public class RegisterQuery extends Screen {
	public RegisterQuery(String newName) {
		super(newName);
	}
	
	public void show() {
		
		boolean userValid = false;
		boolean passwordValid =false;
		super.show();
		String newUsername = "";
		String newPassword = "";
		String confirmPassword = "";
		String newEmail = "";
		
		while(!userValid) {
			System.out.print("Enter your new username: ");
			newUsername = Controller.CONSOLE_INPUT.nextLine();
			userValid = Service.checkUsername(newUsername);
			if(!userValid) System.out.println("Username taken, please try again.");
		}
		
		while(!passwordValid) {
			System.out.print("Enter your new password: ");
			newPassword = Controller.CONSOLE_INPUT.nextLine();
			System.out.print("Confirm your new password: ");
			confirmPassword = Controller.CONSOLE_INPUT.nextLine();
			
			if (newPassword.equals(confirmPassword)) {
			  passwordValid = true;
			  Service.addPassword(newPassword);
			}
			else System.out.println("Password mismatch, please reenter your new password.");
		}
		
		while(!finished) {
			System.out.print("Enter your email: ");
			newEmail = Controller.CONSOLE_INPUT.nextLine();
			//This is where service verifies your email
			finished = Service.verifyEmail(newEmail);
			if(finished) Service.addUser();
		}
		
		
		Controller.setState("optionScreen");
	}
}
