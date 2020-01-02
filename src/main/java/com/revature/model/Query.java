package com.revature.model;

import java.util.InputMismatchException;
import com.revature.controller.Controller;
import com.revature.service.Service;

public class Query extends Screen {
	public Query(String newName, Service service) {
		super(newName, service);
	}
	
	public void show() {
		super.show();
		Double input = 0.0;
		
		while(!finished) {
			try {
				System.out.print(prompt);
				input = Controller.CONSOLE_INPUT.nextDouble();
				interpretOption(input);
			}catch (InputMismatchException e) {
				System.out.println("Invalid input, please try again");
			}finally {
				Controller.CONSOLE_INPUT.nextLine();
			}
		}
	}
	
	protected void interpretOption(double i) {
		
	}
}
