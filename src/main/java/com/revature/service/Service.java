package com.revature.service;

import java.util.ArrayList;
import java.util.List;
import com.revature.databaseManager.DatabaseManager;
import com.revature.databaseManager.UserSession;
import com.revature.exception.AccountOverdrawnException;

public class Service {
	
    private static UserSession currSession = new UserSession();
    private static DatabaseManager dbManager = new DatabaseManager();
	private static String currUsername;
	private static Double balance = 0.0;
	private static List<String> sessionHistory = new ArrayList<String>();
	
	public Service() {
		super();
		currUsername = "";
		balance = 10.0;
	}
	
	public static boolean checkUsername(String user) {
		//check if the username is here, set current username
		
		currUsername = user;
		currSession.setUser_id(dbManager.getUserId(currUsername));
		return true;
	}
	
	public static void addUser() {
	  currSession.setUsername(currUsername);
	  
	  dbManager.newProfile(currSession);
	  currSession.setUser_id(dbManager.getUserId(currUsername));
	}
	
	public static void addPassword(String password) {
	  currSession.setPassword(password);
	}
	
	public static boolean checkLoginCredentials(String user, String password) {
		//check the password associated with user
	  if(dbManager.usernameInDatabase(user) && dbManager.verifyPassword(user, password)) {
    		currUsername = user;
    		
    		currSession.setUsername(currUsername);
    		currSession.setPassword(password);
    		
    		currSession.setUser_id(dbManager.getUserId(currSession.getUsername()));
    		return true;
    	  }
		//System.out.println("checkLoginCredentials : " + currUsername);
		//System.out.println("check with get : " + getUserName());
		return false;
	}
	
	public static void withdraw(Double value) throws AccountOverdrawnException {
		if (currSession.getBalance() < value) throw new AccountOverdrawnException();
		else {
			currSession.setBalance(currSession.getBalance() - value);
			dbManager.changeBalance(currSession.getBalance(), currUsername);
	        dbManager.newTransaction("Withdrawal", currSession.getBalance() * -1, currSession.getUser_id());
			//sessionHistory.add("Withdrawal : -$" + value);
		}
	}
	
	public static boolean verifyEmail(String newEmail) {
	  currSession.setEmail(newEmail);
	  return true;
	}
	
	public static void deposit(Double value) {
		currSession.setBalance(currSession.getBalance() + value);
		dbManager.changeBalance(currSession.getBalance(), currUsername);
		dbManager.newTransaction("Deposit", currSession.getBalance(), currSession.getUser_id());
		//sessionHistory.add("Deposit :  $" + value);
	}
	
	public static String getBalance() {
	  Double result = dbManager.getBalanceFromDatabase(currUsername);
	  currSession.setBalance(result);
		return result.toString();
	}
	
	public static String getUserName() {
		return currUsername;
	}
	
	public static void resetStaticFields() {
		balance = 0.0;
		currUsername = "";
		sessionHistory.clear();
		currSession.cleanSession();
	}
	
	public static boolean checkDatabaseForUsername(String name) {
		//check the database
		return true;
	}
	
	public static boolean transferMoney(String recipient, double amount) {
		if (amount <= currSession.getBalance()) {
			//sessionHistory.add("Transfer : -$" + amount);
			if (dbManager.usernameInDatabase(recipient)) {
			  currSession.setBalance(currSession.getBalance() - amount);
			  dbManager.changeBalance(currSession.getBalance(), currSession.getUsername());
			  dbManager.changeBalance(dbManager.getBalanceFromDatabase(recipient) + amount, recipient);
			  
			  dbManager.newTransaction("Transfer Out", amount * -1, currSession.getUser_id());
			  dbManager.newTransaction("Transfer In", amount, dbManager.getUserId(recipient));
			  
			  return true;
			}
			
			return false;
		}else return false;
	}
	
	public static List<String> getTransactionHistory() {
		return dbManager.getAllTransactionOfUser(currSession);
	}
}
