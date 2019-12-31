package com.revature.model;

import com.revature.controller.Controller;
import com.revature.service.*;

public class LogIn extends Screen{
	
	public LogIn(String newName) {
		super(newName);
		prompt = "Username: ";
		intro = "---Log In---";
	}
	
	protected void resetPrompts() {
	  super.resetPrompts();
	  prompt = "Username: ";
	}
	
	public void show() {
		displayIntro();
		String user = "";
		String pass = "";
		//Controller.CONSOLE_INPUT.next();
		resetPrompts();
		
		while(!finished) {
			System.out.print(prompt);
			user = Controller.CONSOLE_INPUT.nextLine();

			prompt = "Password: ";
			System.out.print(prompt);
			pass = Controller.CONSOLE_INPUT.nextLine();
			
			finished = Service.checkLoginCredentials(user, pass);
			//Service.checkLoginCredentials(user, pass);
			
			if(!finished) {
			  System.out.println("Wrong username or password.");
			  Controller.setState("mainMenu");
			  break;
			}
			else {
			  System.out.println("Log in successful!");
			  Controller.setState("optionScreen");
			}
		}	
		
		
	}
}
