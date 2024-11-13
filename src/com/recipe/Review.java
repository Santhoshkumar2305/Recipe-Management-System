package com.recipe;

import java.util.*;
import java.sql.*;

public class Review {
	public void addReview(int userId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Recipe ID: ");
        int recipeId = sc.nextInt();
        
        System.out.println("Enter your rating (1-5): ");
        int rating = sc.nextInt();
        
        System.out.println("Enter your review: ");
        String reviewText = sc.next();
        
        try (Connection conn = DBConnect.getConnection()) {
            String query = "insert into review (userId, recipeId, rating, review) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, recipeId);
            pstmt.setInt(3, rating);
            pstmt.setString(4, reviewText);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Review added successfully!");
            } else {
                System.out.println("Error in adding review.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void displayReview()
	{
		try (Connection conn = DBConnect.getConnection()) {
	        String reviewQuery = "select re.recipeid, re.recipename, re.recipetype, r.userid, u.username, "
	                           + "r.rating, r.review "
	                           + "from recipe re "
	                           + "join review r on re.recipeid = r.recipeid "
	                           + "join userdetails u on r.userid = u.userid "
	                           + "order by re.recipeid";
	        
	        PreparedStatement pstmt = conn.prepareStatement(reviewQuery);
	        ResultSet rs = pstmt.executeQuery();
	        
	        System.out.println("Recipe Review Details:");
	        System.out.println("----------------------------------------------------------------------------------------------------------");
	        System.out.printf("%-10s %-20s %-20s %-10s %-15s %-10s %-20s%n", 
	                          "Recipe ID", "Recipe Name", "Recipe Type", "User ID", 
	                          "Username", "Rating", "Review");
	        System.out.println("----------------------------------------------------------------------------------------------------------");
	        
	        while (rs.next()) {
	            int recipeId = rs.getInt("recipeid");
	            String recipeName = rs.getString("recipename");
	            String recipeType = rs.getString("recipetype");
	            int userId = rs.getInt("userid");
	            String username = rs.getString("username");
	            int rating = rs.getInt("rating");
	            String review = rs.getString("review");
	            
	            System.out.printf("%-10d %-20s %-20s %-10d %-15s %-10d %-40s%n", 
	                              recipeId, recipeName, recipeType, userId, 
	                              username, rating, review);
	        }
	        System.out.println();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public void topRatedRecipes()
	{
		try (Connection conn = DBConnect.getConnection()) {
	        String topQuery = "select * from review_View order by rating desc limit 7";
	        
	        PreparedStatement pstmt = conn.prepareStatement(topQuery);
	        ResultSet rs = pstmt.executeQuery();
	        
	        System.out.println("Top Rated Recipes:");
	        System.out.println("----------------------------------------------------------------------------------------------------------");
	        System.out.printf("%-10s %-20s %-20s %-10s %-15s %-10s %-20s%n", 
	                          "Recipe ID", "Recipe Name", "Recipe Type", "User ID", 
	                          "Username", "Rating", "Review");
	        System.out.println("----------------------------------------------------------------------------------------------------------");
	        
	        while (rs.next()) {
	            int recipeId = rs.getInt("recipeid");
	            String recipeName = rs.getString("recipename");
	            String recipeType = rs.getString("recipetype");
	            int userId = rs.getInt("userid");
	            String username = rs.getString("username");
	            int rating = rs.getInt("rating");
	            String review = rs.getString("review");
	            
	            System.out.printf("%-10d %-20s %-20s %-10d %-15s %-10d %-40s%n", 
	                              recipeId, recipeName, recipeType, userId, 
	                              username, rating, review);
	        }
	        System.out.println();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
}
