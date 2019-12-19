package com.revature.controller;

import java.util.InputMismatchException;

public class Query extends Screen {
	public Query(String newName) {
		super(newName);
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
