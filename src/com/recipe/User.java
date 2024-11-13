package com.recipe;

import java.sql.*;
import java.util.*;

public class User { 
	    private int loggedInUserId = -1; 
	    
	    public void createAccount() {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Enter Username: ");
	        String username = sc.next();
	        System.out.println("Enter Password: ");
	        String password = sc.next();
	        
	        try (Connection conn = DBConnect.getConnection()) {
	            String query = "insert into userdetails (username, password) VALUES (?, ?)";
	            PreparedStatement pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, username);
	            pstmt.setString(2, password);
	            
	            int rowsAffected = pstmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Account created successfully!");
	            } else {
	                System.out.println("Error in creating account.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public boolean login() {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Enter Username: ");
	        String username = sc.next();
	        System.out.println("Enter Password: ");
	        String password = sc.next();
	        
	        try (Connection conn = DBConnect.getConnection()) {
	            String query = "select userId FROM userdetails WHERE username = ? AND password = ?";
	            PreparedStatement pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, username);
	            pstmt.setString(2, password);
	            
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                loggedInUserId = rs.getInt("userId");
	                System.out.println("Login successful! Welcome " + username);
	                return true;
	            } else {
	                System.out.println("Invalid username or password.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return false;
	    }
	    
	    public void logout() {
	        if (loggedInUserId != -1) {
	            loggedInUserId = -1;
	            System.out.println("Logout successful.");
	        } else {
	            System.out.println("No user is currently logged in.");
	        }
	    }
	    
	    public boolean isLoggedIn() {
	        return loggedInUserId != -1;
	    }
	    
	    public int getLoggedInUserId() {
	        return loggedInUserId;
	    }
	}

