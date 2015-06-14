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
	private final static String ID = "sogong2015";
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
				"tiger");
		String accountSQL = "grant all privileges on *.* to sogong2015@localhost "
				+ "identified by 'mju12345' with grant option";
		statement = connection.prepareStatement(accountSQL);
		statement.executeUpdate();
		System.out.println("계정 생성");
		
		connection.close();
		statement.close();
	}
	
	public void createDB() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306", 
				"sogong2015",
				"mju12345");
		String dbSQL = "create database sogongDB;";
		statement = connection.prepareStatement(dbSQL);
		statement.executeUpdate();
		System.out.println("데이터베이스 생성");
		
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
				+ "NAME VARCHAR(10) NOT NULL);";
		statement = connection.prepareStatement(tableSQL);
		statement.executeUpdate();
		System.out.println("테이블 생성");
		
		connection.close();
		statement.close();
	}
	
	public void insertData() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = getConnection();
		String insertSQL = "insert into user (id, password, name) values (?, ?, ?);";
		statement = connection.prepareStatement(insertSQL);
		statement.setString(1, "id0");
		statement.setString(2, "pw0");
		statement.setString(3, "name0");
		statement.executeUpdate();
		statement.setString(1, "id1");
		statement.setString(2, "pw1");
		statement.setString(3, "name1");
		statement.executeUpdate();
		statement.setString(1, "id2");
		statement.setString(2, "pw2");
		statement.setString(3, "name2");
		statement.executeUpdate();
		statement.setString(1, "id3");
		statement.setString(2, "pw3");
		statement.setString(3, "name3");
		statement.executeUpdate();
		statement.setString(1, "id4");
		statement.setString(2, "pw4");
		statement.setString(3, "name4");
		statement.executeUpdate();
		
		System.out.println("데이터 입력");
		
		connection.close();
		statement.close();
	}
	
	public void updateData() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = getConnection();
		String updataSQL = "update user set password='pw5', id='id5', name='name5' where id='id0';";
		statement = connection.prepareStatement(updataSQL);
		statement.executeUpdate();
		
		System.out.println("데이터 업데이트");
		
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
		System.out.println("데이터 출력");
		
		while(resultSet.next()) {
			String id = resultSet.getString("id");
			String password = resultSet.getString("password");
			String name = resultSet.getString("name");
			System.out.println(id+" "+password+" "+name);
		}
		
		closeConnection(connection, statement, resultSet);
	}
	
	public void deleteData() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = getConnection();
		String deleteSQL = "delete from user where id='id5';";
		statement = connection.prepareStatement(deleteSQL);
		statement.executeUpdate();
		System.out.println("id5 데이터 삭제");
		
		connection.close();
		statement.close();
	}
	
	public void dropTable() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = getConnection();
		String dropTableSQL = "drop table user;";
		statement = connection.prepareStatement(dropTableSQL);
		statement.executeUpdate();
		System.out.println("테이블 삭제");
		
		connection.close();
		statement.close();
	}
	
	public void dropDB() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = getConnection();
		String dropDBSQL = "drop database sogongDB;";
		statement = connection.prepareStatement(dropDBSQL);
		statement.executeUpdate();
		System.out.println("데이터베이스 삭제");
		
		connection.close();
		statement.close();
	}
	
	public void dropACcount() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306", 
				"root",
				"tiger");
		String dropAccount = "drop user sogong2015@localhost;";
		statement = connection.prepareStatement(dropAccount);
		statement.executeUpdate();
		System.out.println("계정삭제");
		
		connection.close();
		statement.close();
	}
	
	public User login(String userID, String userPassword) 
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// dao 호출
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
