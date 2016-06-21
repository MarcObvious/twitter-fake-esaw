package DAO;

import java.sql.*;
import java.util.Map;
import java.util.Map.Entry;

public class DAO {	

	private Connection connection;
	private Statement statement;

	public DAO() throws Exception {
		String user = "mysql";
		String password = "prac";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ts1",user,password);
		statement = connection.createStatement();
	}

	//Selects
	public ResultSet executeSQL(String query) throws SQLException{
		return statement.executeQuery(query);
	}
	//Inserts i Updates
	public int executeUpdate(String query) throws SQLException{
		return statement.executeUpdate(query);
	}

	// Retorna true si existeix a la BD o hi ha error
	public boolean exists(String table,String[] elements)  throws SQLException{
		String query = "select count(*) as count from " + table;
			
		for (int i=0; i<elements.length; i+=2){
				query += i==0 ? " where " : " and ";
				query += elements[i] + "=\"" + elements[i+1] + "\"";
        }
		
		query += ";";
		System.out.println(query);
		ResultSet result = statement.executeQuery(query);
		result.next();

		if (result.getInt("count") == 0)
			return false;
		return true;
	}


	//TODO: code for updates for Assignments 2, 3 and 4.

	public ResultSet readDataFromTable(String table) throws SQLException{
		String query = "SELECT * FROM "+table;
		return statement.executeQuery(query);
	}

	public void disconnectBD() throws SQLException{
		statement.close();
		connection.close();
	}
}