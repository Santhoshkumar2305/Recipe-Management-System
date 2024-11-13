package com.recipe;

import java.sql.*;
import java.util.*;
import java.io.IOException;
import java.io.FileWriter;

public class Recipe implements Managable{
	Scanner sc = new Scanner(System.in);
	public void addRecipeWithIngredients()
	{
		System.out.println("You can Add Your Recipes here");
	}
	
	public void addRecipe()
	{
		System.out.println("\nChoose the recipe type:");
		System.out.println("1.Vegetarian Recipe");
		System.out.println("2.Non-Vegetarian Recipe");
		VegetarianRecipe vegetarianRecipeObj;
		NonvegetarianRecipe nonvegetarianRecipeObj;
		int choice = sc.nextInt();
		if(choice==1)
		{
			vegetarianRecipeObj= new VegetarianRecipe();
			vegetarianRecipeObj.addRecipeWithIngredients();
		}
		else
		{
			nonvegetarianRecipeObj= new NonvegetarianRecipe();
			nonvegetarianRecipeObj.addRecipeWithIngredients();
		}
	}
	
	protected void insertRecipeIngredient(int rec_id,int ingr_id,int ingr_quantity,String ingr_unit)
	{
		try(Connection conn = DBConnect.getConnection())
		{
			String query="insert into recipe_ingredient(recipeId,ingredientId,quantity,unit) values(?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, rec_id);
			pstmt.setInt(2, ingr_id);
			pstmt.setInt(3, ingr_quantity);
			pstmt.setString(4, ingr_unit);
			pstmt.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void displayAllRecipes() {
		try (Connection conn = DBConnect.getConnection()) {
	        String query = "select recipeId, recipename, recipetype from recipe order by recipeId asc";
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        ResultSet rs = pstmt.executeQuery();
	        
	        System.out.println("Recipe List");
	        System.out.println("-----------------------------------------------------------");
	        System.out.printf("%-10s %-20s %-20s%n", "Recipe ID", "Recipe Name", "Recipe Type");
	        System.out.println("-----------------------------------------------------------");

	        FileWriter write = new FileWriter("Recipe Deatils.txt", false);

	        while (rs.next()) {
	            int recipeId = rs.getInt("recipeId");
	            String recipeName = rs.getString("recipename");
	            String recipeType = rs.getString("recipetype");

	            System.out.printf("%-10d %-20s %-20s%n", recipeId, recipeName, recipeType);

	            String data = String.format("Recipe ID: %d Recipe Name: %s Recipe Type: %s\n", recipeId, recipeName, recipeType);
	            write.write(data);
	        }

	        write.close();
	        System.out.println("\nRecipe Details Provided");

	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    }
    }
	
	public void searchRecipe()
	{
		System.out.println("Enter the Recipe Name to Search:");
	    String search = sc.nextLine();

	    try (Connection conn = DBConnect.getConnection()) {
	        String searchQuery = "select recipeId, recipename, recipetype from recipe where recipename like ?";
	        PreparedStatement pstmt = conn.prepareStatement(searchQuery);
	        pstmt.setString(1, "%" + search + "%");
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (!rs.isBeforeFirst()) {
	            System.out.println("Recipe not found.");
	        } else {
	            System.out.printf("\n%-10s %-20s %-20s%n", "Recipe ID", "Recipe Name", "Recipe Type");
	            System.out.println("-----------------------------------------------------------");
	            while (rs.next()) {
	                int recipeId = rs.getInt("recipeId");
	                String recipeName = rs.getString("recipename");
	                String recipeType = rs.getString("recipetype");

	                System.out.printf("%-10d %-20s %-20s%n", recipeId, recipeName, recipeType);
	            }
	            System.out.println();
	        }
	    }
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }
	}

	
}
