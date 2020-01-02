package com.revature.model;

import com.revature.controller.Controller;
import com.revature.service.Service;

public class MainMenu extends Menu {
	public MainMenu(String newName, Service service) {
		super(newName, service);
		intro = "---Welcome to the Bank!---";
		prompt = "Would you like to log in or register a new account?";
		options.add("Log in");
		options.add("Register");
		options.add("Quit");
		hasIntro = true;
	}
	
	protected void interpretOptions(int choice) {
		switch(choice) {
		case 1 : Controller.setState("logIn");
			     finished = true;
			     break;
		case 2 : Controller.setState("register");
				 finished = true;
				 break;
		case 3 : System.out.println("Thanks for coming, closing application.");
				 finished = true;
				 Controller.quit = true;
				 break;
		default : System.out.println("Please input a value in range 1-" + (options.size()));
		}
	}
	
}
