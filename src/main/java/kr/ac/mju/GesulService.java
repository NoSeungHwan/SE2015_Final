package kr.ac.mju;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.stereotype.Service;

public class GesulService {
	private final static String URL = "jdbc:mysql://localhost:3306/sogongDB";
	private final static String ID = "root";
	private final static String PASSWORD = "mju12345";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, ID, PASSWORD);
	}
	
	private void closeConnection(Connection connection, 
			Statement statement, ResultSet resultSet) throws SQLException {
		if(connection == null) {
			return;
		}
		connection.close();
		statement.close();
		resultSet.close();
	}

	public void createAccount() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306", 
				"a",
				"12345");
		String accountSQL = "grant all privileges on *.* to root@localhost "
				+ "identified by 'mju12345' with grant option";
		statement = connection.prepareStatement(accountSQL);
		statement.executeUpdate();
		System.out.println("怨꾩젙 �깮�꽦");
		
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306", 
				"root",
				"mju12345");
		String dbSQL = "create database sogongDB;";
		statement = connection.prepareStatement(dbSQL);
		statement.executeUpdate();
		System.out.println("�뜲�씠�꽣踰좎씠�뒪 �깮�꽦");
		
		connection = getConnection();
		String tableSQL = "CREATE TABLE course"
				+ "(C_ID VARCHAR(10) NOT NULL primary key,"
				+ "C_NAME VARCHAR(10) NOT NULL,"
				+ "C_YEAR VARCHAR(10) NOT NULL,"
				+ "C_RNAK VARCHAR(10) NOT NULL,"
				+ "C_MANY VARCHAR(10) NOT NULL,"
				+ "C_GRADE VARCHAR(10)NOT NULL);";
		statement = connection.prepareStatement(tableSQL);
		statement.executeUpdate();
		System.out.println("�뀒�씠釉� �깮�꽦");
		
		connection = getConnection();
		String insertSQL = "insert into course (c_id, c_name, c_year, c_rank, c_many, c_grade) values (?, ?, ?, ?, ?, ?);";
		statement = connection.prepareStatement(insertSQL);
		statement.setString(1, "1");
		statement.setString(2, "�냼怨�");
		statement.setString(3, "2015");
		statement.setString(4, "3");
		statement.setString(5, "0");
		statement.setString(6, "3");
		statement.executeUpdate();
		statement.setString(1, "2");
		statement.setString(2, "�옄諛�");
		statement.setString(3, "2015");
		statement.setString(4, "2");
		statement.setString(5, "0");
		statement.setString(6, "3");
		statement.executeUpdate();
		
		connection.close();
		statement.close();
	}
	
	public void selectData() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		connection = getConnection();
		String selectSQL = "select * from course;";
		statement = connection.prepareStatement(selectSQL);
		resultSet = statement.executeQuery();
		System.out.println("�뜲�씠�꽣 異쒕젰");
		while(resultSet.next()) {
			String c_id = resultSet.getString("c_id");
			String c_name = resultSet.getString("c_name");
			String c_year = resultSet.getString("c_year");
			String c_rank = resultSet.getString("c_rank");
			String c_many = resultSet.getString("c_many");
			String c_grade = resultSet.getString("c_grade");
			System.out.println(c_id+" "+c_name+" "+c_year+" "+c_rank+" "+c_many+" "+c_grade );
		}
		
		closeConnection(connection, statement, resultSet);
	}
	
	
	

}
