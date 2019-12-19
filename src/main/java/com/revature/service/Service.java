package com.revature.service;

public class Service {
	
	private static String currUsername;
	private static Double balance = 0.0;
	
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
		}
	}
	
	public static void deposit(Double value) {
		balance += value;
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
	}
}
