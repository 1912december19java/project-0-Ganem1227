package com.revature.databaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
  private static Connection cnn;
  private UserSession currSession;
  
  static {
    try {
      cnn = DriverManager.getConnection(
          System.getenv("AWS_URL"), 
          System.getenv("AWS_USERNAME"), 
          System.getenv("AWS_PASSWORD"));
      System.out.println("Connected!");
    }catch(SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void save(UserSession userSession) {
    PreparedStatement stmt = null;
    
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
    
    try {
      stmt = cnn.prepareStatement("INSERT INTO transactions (transaction_type, amount, username_id)"
          + "VALUES (?, CAST(? AS NUMERIC), ?)");
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
    
    try {
      stmt = cnn.prepareStatement("UPDATE roster SET balance = CAST(? AS NUMERIC) WHERE username = ?");
      stmt.setDouble(1, amount);
      stmt.setString(2, username);
      
      stmt.execute();
      
    }catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public void newProfile(UserSession userSession) {
    PreparedStatement stmt = null;
    
    try {
      stmt = cnn.prepareStatement("INSERT INTO roster(username, pass, email, balance) VALUES (?, ?, ?, CAST(? AS NUMERIC))");
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
    
    try {
      stmt = cnn.prepareStatement("SELECT * FROM transactions WHERE username_id = ?");
      stmt.setInt(1, userSession.getUser_id());
      //stmt.setInt(1, 1);
      
      if(stmt.execute()) {
        rs = stmt.getResultSet();
        
        while(rs.next()) {
          transactions.add(rs.getString("transaction_type") + " : " + rs.getDouble("amount"));
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
      e.printStackTrace();
    }
    return false;
  }
  
  public boolean verifyPassword(String user, String password) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
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
