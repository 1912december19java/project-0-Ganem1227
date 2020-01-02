package com.revature.controller;

import java.util.Scanner;
import com.revature.model.DepositQuery;
import com.revature.model.LogIn;
import com.revature.model.MainMenu;
import com.revature.model.OptionScreen;
import com.revature.model.RegisterQuery;
import com.revature.model.Screen;
import com.revature.model.TransferScreen;
import com.revature.model.WithdrawQuery;
import com.revature.service.Service;
import java.util.Map;
import java.util.HashMap;

public class Controller {

	static public Scanner CONSOLE_INPUT = new Scanner(System.in);
	static Screen currScreen;
	static Map<String, Screen> states = new HashMap<String, Screen>();
	private Service service;
	
	static public Boolean quit;
	
	public Controller() {
		super();
		quit = false;
		service = new Service();
		MainMenu mainMenu = new MainMenu("mainMenu", service);
		LogIn logIn = new LogIn("logIn", service);
		OptionScreen optionScreen = new OptionScreen("optionScreen", service);
		RegisterQuery register = new RegisterQuery("register", service);
		WithdrawQuery withdraw = new WithdrawQuery("withdraw", service);
		DepositQuery deposit = new DepositQuery("deposit", service);
		TransferScreen transfer = new TransferScreen("transfer", service);
		
		states.put("mainMenu", mainMenu);
		states.put("logIn", logIn);
		states.put("optionScreen", optionScreen);
		states.put("register", register);
		states.put("withdraw", withdraw);
		states.put("deposit", deposit);
		states.put("transfer", transfer);
		
		
		setState("mainMenu");
	}
	
	public void init() {
		
		while(!quit) {
			currScreen.show();
		}
	}
	
	public static void setState(String newState) {
		currScreen = states.get(newState);
	}
}