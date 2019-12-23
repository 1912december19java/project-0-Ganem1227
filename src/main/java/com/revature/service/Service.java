package com.revature.service;

import java.util.ArrayList;

import com.revature.exception.AccountOverdrawnException;

public class Service {
	
	private static String currUsername;
	private static Double balance = 0.0;
	private static ArrayList<String> sessionHistory = new ArrayList<String>();
	
	public Service() {
		super();
		currUsername = "";
		balance = 10.0;
	}
	
	public static boolean checkUsername(String user) {
		//check if the username is here, set current username
		
		currUsername = user;
		return true;
	}
	
	public static boolean checkLoginCredentials(String user, String password) {
		//check the password associated with user
		currUsername = user;
		//System.out.println("checkLoginCredentials : " + currUsername);
		//System.out.println("check with get : " + getUserName());
		return true;
	}
	
	public static void withdraw(Double value) throws AccountOverdrawnException {
		if (balance < value) throw new AccountOverdrawnException();
		else {
			balance -= value;
			sessionHistory.add("Withdrawal : -$" + value);
		}
	}
	
	public static boolean verifyEmail(String newEmail) {
		return true;
	}
	
	public static void deposit(Double value) {
		balance += value;
		sessionHistory.add("Deposit :  $" + value);
	}
	
	public static String getBalance() {
		return balance.toString();
	}
	
	public static String getUserName() {
		return currUsername;
	}
	
	public static void resetStaticFields() {
		balance = 0.0;
		currUsername = "";
		sessionHistory.clear();
	}
	
	public static boolean checkDatabaseForUsername(String name) {
		//check the database
		return true;
	}
	
	public static boolean transferMoney(String recipient, double amount) {
		if (amount <= balance) {
			balance -= amount;
			sessionHistory.add("Transfer : -$" + amount);
			return true;
		}else return false;
	}
	
	public static ArrayList<String> getTransactionHistory() {
		return sessionHistory;
	}
}
