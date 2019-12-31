package com.revature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class DatabaseManager {
  private static Connection cnn;
  private static Logger log = Logger.getLogger(DatabaseManager.class);
  
  static {
    
    log.trace("Begin connection to DB");

    try {
      cnn = DriverManager.getConnection(
          System.getenv("AWS_URL"), 
          System.getenv("AWS_USERNAME"), 
          System.getenv("AWS_PASSWORD"));
      log.info("Connected to Database.");
    }catch(SQLException e) {
      log.error("Failed to connect to database", e);
    }
  }
  
  public void save(UserSession userSession) {
    PreparedStatement stmt = null;
    
    log.trace("Save user session");
    
    try {
      stmt = cnn.prepareStatement("UPDATE roster SET balance = ? WHERE username = ?");
      stmt.setDouble(1, userSession.getBalance());
      stmt.setString(2, userSession.getUsername());
      
      stmt.execute();
    }catch(SQLException e) {
      e.printStackTrace();
    }

  }
  
  public void newTransaction(String transactionType, double amount, int user_id) {
    PreparedStatement stmt = null;
    log.trace("Make new Transaction");
    
    try {
      stmt = cnn.prepareStatement("INSERT INTO transactions (transaction_type, amount, username_id)"
          + "VALUES (?, ?, ?)");
      stmt.setString(1, transactionType);
      stmt.setDouble(2, amount);
      stmt.setInt(3, user_id);
      
      stmt.execute();
    }catch(SQLException e) {
      e.printStackTrace();
    }
  }
  
  public int getUserId(String username) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int result = 0;
    
    log.trace("Fetching user ID from database");
    
    try {
      stmt = cnn.prepareStatement("SELECT id FROM roster WHERE username = ?");
      stmt.setString(1, username);
      
      stmt.execute();
      
      rs = stmt.getResultSet();
      if(rs.next()) {
        result = rs.getInt("id");
      }
    }catch(SQLException e) {
      e.printStackTrace();
    }
    
    return result;
    
  }
  
  public void changeBalance(double amount, String username) {
    PreparedStatement stmt = null;
    
    log.trace("Change balance: " + amount);
    
    try {
      stmt = cnn.prepareStatement("UPDATE roster SET balance = ? WHERE username = ?");
      stmt.setDouble(1, amount);
      stmt.setString(2, username);
      
      stmt.execute();
      
    }catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void newProfile(UserSession userSession) {
    PreparedStatement stmt = null;
    
    log.trace("Make new user: " + userSession.getUsername());
    try {
      stmt = cnn.prepareStatement("INSERT INTO roster(username, pass, email, balance) VALUES (?, ?, ?, ?)");
      stmt.setString(1, userSession.getUsername());
      stmt.setString(2, userSession.getPassword());
      stmt.setString(3, userSession.getEmail());
      stmt.setDouble(4, 0.0);
      
      stmt.execute();
    }catch(SQLException e) {
      System.out.println("Creating new profile failed.");
      e.printStackTrace();
    }
  }
  
  public List<String> getAllTransactionOfUser(UserSession userSession){
    List<String> transactions = new ArrayList<String>();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    log.trace("Fetch all transactions of user:" + userSession.getUsername());
    
    try {
      stmt = cnn.prepareStatement("SELECT * FROM transactions WHERE username_id = ?");
      stmt.setInt(1, userSession.getUser_id());
      //stmt.setInt(1, 1);
      
      if(stmt.execute()) {
        rs = stmt.getResultSet();
        
        while(rs.next()) {
          transactions.add(rs.getString("date_of_transaction") + " : " + rs.getString("transaction_type") + " : " + rs.getDouble("amount"));
        }
        
      }
    }catch(SQLException e) {
      
      System.out.println("Failed to retrieve transactions.");
    }
    
    return transactions;
  }
  
  
  public boolean usernameInDatabase(String user) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    log.trace("Check if "+ user + " is in Database");
    
    try {
      stmt = cnn.prepareStatement("SELECT count(username) FROM roster WHERE username = ?");
      stmt.setString(1, user);
      stmt.execute();
      
      rs = stmt.getResultSet();
      
      while(rs.next()) {
        if (rs.getInt("count") != 0) return true;
        else return false;
      }
      
    }catch(SQLException e) {
      log.trace("SQLException in usernameInDatabase");
      e.printStackTrace();
    }
    return false;
  }
  
  public boolean verifyPassword(String user, String password) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    log.trace("Verify password :" + password);
    
    try {
      stmt = cnn.prepareStatement("SELECT pass FROM roster WHERE username = ?");
      stmt.setString(1, user);
      
      stmt.execute();
      
      rs = stmt.getResultSet();
      
      while(rs.next()) {
        if (rs.getString("pass").equals(password)) return true;
        else return false;
      }
      
      
    }catch(SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  public double getBalanceFromDatabase(String user) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    log.trace("Get Balance from Database of user: " + user);
    
    try {
      
      stmt = cnn.prepareStatement("SELECT balance FROM roster WHERE username = ?");
      stmt.setString(1, user);
      stmt.execute();
      
      rs = stmt.getResultSet();
      
      while(rs.next()) {
        return rs.getDouble("balance");
      }
      
    }catch(SQLException e) {
      e.printStackTrace();
    }
    return 0.0;
  }
  
}
