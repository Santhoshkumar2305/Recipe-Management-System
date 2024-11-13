package com.recipe;

import java.sql.*;
import java.sql.SQLException;

public class Ingredients {
	
	public int getIngredientIdOrInsert(Connection conn,String ingr_name) throws SQLException
	{
		
			String checkQuery="select ingredientId from ingredient where ingredientname=?";
			PreparedStatement pstmt = conn.prepareStatement(checkQuery);
			pstmt.setString(1,ingr_name);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				return rs.getInt(1);
			}
			else
			{
				String insertQuery="insert into ingredient(ingredientname) values (?)";
				PreparedStatement pstmt1 = conn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
				pstmt1.setString(1,ingr_name);
				pstmt1.executeUpdate();
				ResultSet generatedKeys = pstmt1.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1);
	            }
		    }
			return 0;
	}
}
