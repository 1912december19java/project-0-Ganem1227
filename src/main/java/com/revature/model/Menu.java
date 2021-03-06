package com.revature.model;

import java.util.InputMismatchException;
import com.revature.controller.Controller;
import com.revature.service.Service;

public class Menu extends Screen {
	
	public Menu(String newName, Service service) {
		super(newName, service);
	}
	
	public void show() {
		displayIntro();
		finished = false;
		int choice;
		
		while(!finished) {
			displayQuery();
			try {
				choice = Controller.CONSOLE_INPUT.nextInt();
				Controller.CONSOLE_INPUT.nextLine();
				interpretOptions(choice);
			}catch(InputMismatchException e) {
				System.out.println("Invalid input, please try again.");
				Controller.CONSOLE_INPUT.nextLine();
			}
		}
	}
	
	void displayQuery() {
		
		System.out.println(prompt);
		
		for (int i = 0; i < options.size(); i++) {
			System.out.println("[" + (i+1) + "]. " + options.get(i));
		}
		displayInputInstructions();
	}
	
	protected void interpretOptions(int choice) {
		
	}
	
	protected void displayInputInstructions() {
		System.out.print("Please input the corresponding number here: ");
	}
	
}
