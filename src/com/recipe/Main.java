package com.recipe;

import java.sql.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws SQLException 
	{
		Recipe recipe = new Recipe();
		
		
		User user = new User();
        Review review = new Review();
        Scanner sc = new Scanner(System.in);
        System.out.println("\n*********** WELCOME TO SANTHOSH'S RECIPE MANAGEMENT SYSTEM ***********");
        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Add Recipe");
            System.out.println("4. Add Review");
            System.out.println("5. Display All Recipe");
            System.out.println("6. Search Recipe");
            System.out.println("7. Display Reviews");
            System.out.println("8. Top Rated Recipes");
            System.out.println("9. Logout");
            System.out.println("10. Exit");
            System.out.println("\nEnter your choice");
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    user.createAccount();
                    break;
                case 2:
                    if (user.login()) {
                        System.out.println("Logged in successfully.");
                    }
                    break;
                
                case 3:
                    if (user.isLoggedIn()) {
                    	recipe.addRecipe();
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 4:
                    if (user.isLoggedIn()) {
                        review.addReview(user.getLoggedInUserId());
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 5:
                	recipe.displayAllRecipes();
                	break;
                case 6:
                	recipe.searchRecipe();
                	break;
                case 7:
                	review.displayReview();
                	break;
                case 8:
                	review.topRatedRecipes();
                	break;
                case 9:
                    user.logout();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    System.exit(1);
            }
        }
	}
}

