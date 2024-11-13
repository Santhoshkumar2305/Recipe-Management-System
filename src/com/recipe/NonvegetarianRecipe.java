package com.recipe;

import java.sql.*;
import java.util.Scanner;

public class NonvegetarianRecipe extends Recipe{
	public void addRecipeWithIngredients()
	{
		Scanner sc = new Scanner(System.in);
		Ingredients ingredObj = new Ingredients();
		try(Connection conn = DBConnect.getConnection())
		{
			System.out.println("Enter the recipe name(Non-Vegetarian): ");
			String rec_name=sc.nextLine();
			System.out.println("Enter the instructions: ");
			String rec_instruction=sc.nextLine();
			
			String query="insert into recipe(recipename,instructions,recipetype) values(?,?,'Non-Vegetarian')";
			PreparedStatement pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, rec_name);
			pstmt.setString(2, rec_instruction);
			pstmt.executeUpdate();
			
			ResultSet rs=pstmt.getGeneratedKeys();
			int rec_id=0;
			if(rs.next())
			{
				rec_id=rs.getInt(1);
			}
			System.out.println("Enter the no of ingredients: ");
			int noOfIngredient=sc.nextInt();
			sc.nextLine();
			for(int i=0;i<noOfIngredient;i++)
			{
				System.out.println("Enter the Ingredientname:");
				String ingr_name=sc.next();
				System.out.println("Enter the Quantity:");
				int ingr_quantity=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the Unit(Grams/Cups/Spoons/Litre,etc):");
				String ingr_unit=sc.next();
				
				int ingr_id=ingredObj.getIngredientIdOrInsert(conn,ingr_name);
				
				insertRecipeIngredient(rec_id,ingr_id,ingr_quantity,ingr_unit);
				
			}
			
			System.out.println("Non-Vegetarian Recipe Added Successfully");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
