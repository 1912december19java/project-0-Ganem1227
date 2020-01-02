package com.revature.model;

import com.revature.controller.Controller;
import com.revature.exception.PasswordToShortException;
import com.revature.service.Service;

public class RegisterQuery extends Screen {
	public RegisterQuery(String newName, Service service) {
		super(newName, service);
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
			
			if (newUsername.length() == 0) {
			  System.out.println("Username too short, please try again.");
			  continue;
			}
			
			userValid = !service.checkUsername(newUsername);
			if(!userValid) System.out.println("Username taken, please try again.");
		}
		
		while(!passwordValid) {
			System.out.print("Enter your new password: ");
			newPassword = Controller.CONSOLE_INPUT.nextLine();
			
			try {
			  passwordLength(newPassword);
			}catch (PasswordToShortException e) {
			  System.out.println("Password too short. Please try again!");
			  continue;
			}
			
			
			System.out.print("Confirm your new password: ");
			confirmPassword = Controller.CONSOLE_INPUT.nextLine();
			
			if (newPassword.equals(confirmPassword)) {
			  passwordValid = true;
			  service.addPassword(newPassword);
			}
			else System.out.println("Password mismatch, please reenter your new password.");
		}
		
		while(!finished) {
			System.out.print("Enter your email: ");
			newEmail = Controller.CONSOLE_INPUT.nextLine();
			//This is where service verifies your email
			service.verifyEmail(newEmail);
			finished = service.addUser();
			if(finished) {
			  Controller.setState("optionScreen");
			} else {
			  Controller.setState("mainMenu");
			  finished = true;
			}
		}
	}
	
	
	private void passwordLength(String pass) throws PasswordToShortException {
	  if (pass.length() == 0)throw new PasswordToShortException();
	}
}
