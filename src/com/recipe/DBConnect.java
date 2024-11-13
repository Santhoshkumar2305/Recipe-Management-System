package com.recipe;

import java.sql.*;

public class DBConnect {
	public static final String URL="jdbc:mysql://localhost:3306/recipe_db";
	public static final String USER="root";
	public static final String Password="Santhosh@12345";
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(URL,USER,Password);
	}
}