package MovieDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Movie.Actor;

public class ActorManager {
	DataSource ds;
	
	public ActorManager()
	{
		try{
		Context ctx = (Context) new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Movie");
		System.out.println(ds);
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	public void createActor(Actor newActor){
		
		String sql="insert into Actor(id,firstName,lastName,dateOfBirth)values (?,?,?,?)";

		Connection connection = null;		
		try {
			connection = ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1,newActor.getId());
			statement.setString(2,newActor.getFirstName());
			statement.setString(3,newActor.getLastName());
			statement.setDate(4,newActor.getDateOfBirth());			
			statement.execute();
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

	
	public List<Actor> readAllActors(){
		List<Actor> Actor= new ArrayList<Actor>();
		String sql="select * from Actor";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
		ResultSet results=statement.executeQuery();
		while(results.next()){
			Actor actor = new Actor();
			actor.setId(results.getInt("id"));
			actor.setFirstName(results.getString("firstName"));
			actor.setLastName(results.getString("lastName"));
			actor.setDateOfBirth(results.getDate("dateOfBirth"));
			Actor.add(actor);
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
		return Actor;
	}
	
	//modify ActorId into int type
	public Actor readActor(int actorId)
	{
		Actor actor =new Actor();
		
		String sql = "select id,firstName,lastName,dateOfBirth from Actor where id = ?";
		Connection connection = null;

		System.out.println("====read specific Actor====");
		ResultSet results = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, actorId);
			results = statement.executeQuery();
			while(results.next()) {
				actor.setId(results.getInt("id"));
				actor.setFirstName(results.getString("firstName"));
				actor.setLastName(results.getString("lastName"));
				actor.setDateOfBirth(results.getDate("dateOfBirth"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return actor;	
	}
	
	

	public void updateActor(int actorId, Actor actor)
	{
		String sql = "update Actor set firstName=?,lastName=?, dateOfBirth=? where id=?";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setDate(3, actor.getDateOfBirth());
			statement.setInt(4,actorId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void deleteActor(int actorId)
	{
		String sql = "delete from Actor where id=?";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, actorId);
			statement.execute();
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
	
