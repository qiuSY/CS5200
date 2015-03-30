package MovieDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Movie.User;


public class UserManager {
	
	DataSource ds;
	
	public UserManager()
	{
		try{
		Context ctx = (Context) new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Movie");
		System.out.println(ds);
		}catch(NamingException e){
			e.printStackTrace();
		}
	}

	
	public void createUser(User newUser){
		
		String sql="insert into User(username,password,firstName,lastName,email,dateOfBirth)values (?,?,?,?,?,?)";

		Connection connection=null;
		
		try {
			connection = ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setString(1,newUser.getUsername());
			statement.setString(2,newUser.getPassword());
			statement.setString(3,newUser.getFirstName());
			statement.setString(4,newUser.getLastName());
			statement.setString(5,newUser.getEmail());
			statement.setDate(6,(Date) newUser.getDateOfBirth());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		//at least guarantee this statement is executed
		}
		
	}
	
	public List<User> readAllUsers(){
		List<User> User= new ArrayList<User>();
		String sql="select * from User";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			ResultSet results=statement.executeQuery();
		while(results.next()){
			User user = new User();
			user.setId(results.getInt("id"));
			user.setUsername(results.getString("username"));
			user.setPassword(results.getString("password"));
			user.setFirstName(results.getString("firstName"));
			user.setLastName(results.getString("lastName"));
			user.setEmail(results.getString("email"));
			user.setDateOfBirth(results.getDate("dateOfBirth"));
			User.add(user);
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		    if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return User;
	}
	
	public User readUser(String username)
	{
		User user =new User();
		String sql = "select id,password,firstName,lastName,email,dateOfBirth from User where username = ?";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				user.setId(result.getInt("id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setFirstName(result.getString("firstName"));
				user.setLastName(result.getString("lastName"));
				user.setEmail(result.getString("email"));
				user.setDateOfBirth(result.getDate("dateOfBirth"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(connection!= null) 
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return user;	
	}
	
	

	public void update(String username, User user)
	{
		String sql = "update User set id=?, password=?,firstName=?, lastName=?,email=?,dateOfBirth=? where Username=?";
		Connection connection = null;		
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getEmail());
			statement.setDate(6, (Date) user.getDateOfBirth());
			statement.setString(7, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void deleteUser(String username)
	{
		String sql = "delete from User where username=?";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
	

