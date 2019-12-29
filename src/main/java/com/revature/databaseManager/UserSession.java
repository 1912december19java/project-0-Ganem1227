package com.revature.databaseManager;

public class UserSession {
  private String username;
  private String password;
  private String email;
  private int user_id;
  private double balance;
  
  public UserSession(String username) {
    this.username = username;
    this.balance = 0.0;
  }
  
  public UserSession() {
    this.username = "test_username";
    this.balance = 0.00;
  }
  
  public void cleanSession() {
    username = "";
    password = "";
    email = "";
    user_id = 0;
    balance = 0.00;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }
  
}
