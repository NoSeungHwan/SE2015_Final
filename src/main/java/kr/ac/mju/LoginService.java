package kr.ac.mju;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
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
				"root",
				"mju12345");
		String accountSQL = "grant all privileges on *.* to root@localhost "
				+ "identified by 'mju12345' with grant option";
		statement = connection.prepareStatement(accountSQL);
		statement.executeUpdate();
		System.out.println("怨꾩젙 �깮�꽦");
		
		connection.close();
		statement.close();
	}
	
	public void createDB() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306", 
				"root",
				"mju12345");
		String dbSQL = "create database sogongDB;";
		statement = connection.prepareStatement(dbSQL);
		statement.executeUpdate();
		System.out.println("�뜲�씠�꽣踰좎씠�뒪 �깮�꽦");
		
		connection.close();
		statement.close();
	}
	
	public void createTable() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = getConnection();
		String tableSQL = "CREATE TABLE user"
				+ "(ID VARCHAR(10) NOT NULL primary key,"
				+ "PASSWORD VARCHAR(10) NOT NULL,"
				+ "NAME VARCHAR(10) NOT NULL,"
				+ "GRADE VARCHAR(10)NOT NULL);";
		statement = connection.prepareStatement(tableSQL);
		statement.executeUpdate();
		System.out.println("�뀒�씠釉� �깮�꽦");
		
		
		connection = getConnection();
		String insertSQL = "insert into user (id, password, name, grade) values (?, ?, ?, ?);";
		statement = connection.prepareStatement(insertSQL);
		statement.setString(1, "60112342");
		statement.setString(2, "12345");
		statement.setString(3, "name0");
		statement.setString(4, "�븰�깮");
		statement.executeUpdate();
		statement.setString(1, "10128");
		statement.setString(2, "12345");
		statement.setString(3, "name1");
		statement.setString(4, "援먯닔");
		statement.executeUpdate();
		statement.setString(1, "id2");
		statement.setString(2, "pw2");
		statement.setString(3, "name2");
		statement.setString(4, "0");
		statement.executeUpdate();
		statement.setString(1, "id3");
		statement.setString(2, "pw3");
		statement.setString(3, "name3");
		statement.setString(4, "0");
		statement.executeUpdate();
		statement.setString(1, "id4");
		statement.setString(2, "pw4");
		statement.setString(3, "name4");
		statement.setString(4, "0");
		statement.executeUpdate();
		
		System.out.println("�뜲�씠�꽣 �엯�젰");
		
		connection.close();
		statement.close();
	}
	
	
	public void selectData() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		connection = getConnection();
		String selectSQL = "select * from user;";
		statement = connection.prepareStatement(selectSQL);
		resultSet = statement.executeQuery();
		System.out.println("�뜲�씠�꽣 異쒕젰");
		
		while(resultSet.next()) {
			String id = resultSet.getString("id");
			String password = resultSet.getString("password");
			String name = resultSet.getString("name");
			System.out.println(id+" "+password+" "+name);
		}
		
		closeConnection(connection, statement, resultSet);
	}
	

	
	
	
	public User login(String userID, String userPassword) 
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// dao �샇異�
		//Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		String sql = "select * from user";
		User user = null;
		
		connection = getConnection();
		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while(resultSet.next()) {
			String id = resultSet.getString("id");
			String password = resultSet.getString("password");
			
			if(id.equals(userID) && password.equals(userPassword)) {
				user = new User();
				user.setID(id);
				user.setName(resultSet.getString("name"));
				user.setPassword(password);
				closeConnection(connection, statement, resultSet);
				return user;
			}
		}
			
		closeConnection(connection, statement, resultSet);
		return null;
	}

}
