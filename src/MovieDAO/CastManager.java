package MovieDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Movie.Cast;

public class CastManager {
	DataSource ds;
	
	public CastManager()
	{
		try{
		Context ctx = (Context) new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Movie");
		System.out.println(ds);
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	//////create a new Cast
	
	public void createCast(Cast newCast){
		
		String sql="insert into Cast (id,characterName,Movieid,Actorid) values (?,?,?,?)";
		Connection connection = null;		
		try {
			connection = ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1,newCast.getId());
			statement.setString(2,newCast.getCharacterName());
			statement.setInt(3,newCast.getMovieid());
			statement.setInt(4,newCast.getActorid());

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

	////////list all Cast
	
	public List<Cast> readAllCasts(){
		List<Cast> list= new ArrayList<Cast>();
		String sql="select * from Cast";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			ResultSet results=statement.executeQuery();
			while(results.next()){
			Cast cast=new Cast();
			cast.setId(results.getInt("id"));
			cast.setCharacterName(results.getString("characterName"));
			cast.setMovieid(results.getInt("Movieid"));
			cast.setActorid(results.getInt("Actorid"));
			list.add(cast);

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
		
		return list;
	}
	
	//modify CastId into int type
	//list all Casts for specific actorId
	public List<Cast> readAllCastsForActor(int actorId)
	{
		List<Cast> casts = new ArrayList<Cast>();
		String sql = "select Cast.id,Cast.characterName,Cast.Movieid,"
				+ "Cast.ActorId from Cast,Actor where Actor.id = Cast.Actorid "
				+ "AND Actor.id=?";

		System.out.println("====read all Cast For specific username====");
		
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, actorId);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Cast cast=new Cast();
				cast.setId(results.getInt("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setMovieid(results.getInt("Movieid"));
				cast.setActorid(results.getInt("Actorid"));
				casts.add(cast);
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
		return casts;
	}
	
	
	//list all Casts for specific movie
	//modify movieid to type int
	public List<Cast> readAllCastForMovie(int movieId)
	{
		List<Cast> casts = new ArrayList<Cast>();


		String sql = "select Cast.id,Cast.characterName,Cast.Movieid,"
				+ "Cast.Actorid from Cast,Movie where Movie.id = Cast.Movieid "
				+ "AND Movie.id=?";

		System.out.println("====read all Cast For specific Movieid====");
		Connection connection = null;
		try {
			Cast cast=new Cast();
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				cast.setId(results.getInt("id"));
				cast.setCharacterName(results.getString("characterName"));
				cast.setMovieid(results.getInt("Movieid"));
				cast.setActorid(results.getInt("Actorid"));
				casts.add(cast);
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
		return casts;
	}
	
		//list all Casts for specific Castid
		//modify movieId to type int
		public Cast readAllCastsForId(int castId)
		{

			Cast cast=new Cast();
			
			String sql = "select * from Cast where id=?";

			System.out.println("====read all Cast For specific Castid====");
			Connection connection = null;
			try {
				connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, castId);
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					cast.setId(results.getInt("id"));
					cast.setCharacterName(results.getString("characterName"));
					cast.setMovieid(results.getInt("Movieid"));
					cast.setActorid(results.getInt("Actorid"));

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
			return cast;
		}
	
	
	
	

	public void updateCast(int CastId, Cast newCast)
	{
		String sql = "update Cast set characterName=?,Movieid=?,ActorId=? where id=?";
		Connection connection = null;		
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newCast.getCharacterName());
			statement.setInt(2, newCast.getMovieid());
			statement.setInt(3, newCast.getActorid());
			statement.setInt(4, CastId);
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
	
	public void deleteCast(int CastId)
	{
		String sql = "delete from Cast where id=?";
		Connection connection = null;
		try {			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, CastId);
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
