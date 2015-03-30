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

import Movie.Comment;

public class CommentManager {

	
	DataSource ds;
	
	public CommentManager()
	{
		try{
		Context ctx = (Context) new InitialContext();
		ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Movie");
		System.out.println(ds);
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	
	//////create a new comment
	
	public void createComment(Comment newComment){
		
		String sql="insert into Comment(id,comment,date,Movieid,UserId)values (?,?,?,?,?)";

		Connection connection = null;
		
		try {
			connection = ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1,newComment.getId());
			statement.setString(2,newComment.getComment());
			statement.setDate(3,newComment.getDate());		
			statement.setInt(4,newComment.getMovieid());
			statement.setInt(5,newComment.getUserid());

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

	////////list all comment
	
	public List<Comment> readAllComments(){
		List<Comment> Comment= new ArrayList<Comment>();
		String sql="select * from Comment";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement=connection.prepareStatement(sql);
		ResultSet results=statement.executeQuery();
		while(results.next()){
			Comment comment=new Comment();
			comment.setId(results.getInt("id"));
			comment.setComment(results.getString("comment"));
			comment.setDate(results.getDate("date"));
			comment.setMovieid(results.getInt("Movieid"));
			comment.setUserid(results.getInt("Userid"));
			Comment.add(comment);
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
		
		return Comment;
	}
	
	//modify CommentId into int type
	//list all comments for specific username
	public List<Comment> readAllCommentsForUsername(String username){
		{
	}
		List<Comment> comm = new ArrayList<Comment>();

		String sql = "select Comment.id,Comment.comment,Comment.date,Comment.Movieid,"
				+ "Comment.UserId from Comment,User where User.id = Comment.Userid "
				+ "AND User.username=?";

		System.out.println("====read all Comment For specific username====");
		
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			results = statement.executeQuery();
			while(results.next()) {
				Comment comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setMovieid(results.getInt("Movieid"));
				comment.setUserid(results.getInt("Userid"));
				comm.add(comment);
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
			return comm;
	}
	
	
	//list all comments for specific movie
	//modify movieid to type int
	public List<Comment> readAllCommentsForMovie(int movieId){
	{
		List<Comment> comm = new ArrayList<Comment>();

		
		String sql = "select Comment.id,Comment.comment,Comment.date,Comment.Movieid,"
				+ "Comment.UserId from Comment,Movie where Movie.id = Comment.Movieid "
				+ "AND Movie.id=?";

		System.out.println("====read all Comment For specific Movieid====");
		
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, movieId);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Comment comment =new Comment();
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setMovieid(results.getInt("Movieid"));
				comment.setUserid(results.getInt("Userid"));
				comm.add(comment);
			}
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
			}
			return comm;
	}
	}
	
		//list all comments for specific commentid
		//modify movieId to type int
		public Comment readAllCommentsForId(int commentId)
		{
			Comment comm =new Comment();

			
			String sql = "select * from Comment where id=?";

			System.out.println("====read all Comment For specific commentid====");
			
			Connection connection = null;
			try {
				connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, commentId);
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					comm.setId(results.getInt("id"));
					comm.setComment(results.getString("comment"));
					comm.setDate(results.getDate("date"));
					comm.setMovieid(results.getInt("Movieid"));
					comm.setUserid(results.getInt("Userid"));
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
				return comm;
		}
	
	
	
	
	

	public void updateComment(int CommentId, String newComment)
	{
		String sql = "update Comment set comment=? where id=?";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment);
			statement.setInt(2, CommentId);
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
	
	public void deleteComment(int CommentId)
	{
		String sql = "delete from Comment where id=?";
		Connection connection = null;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, CommentId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}  if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}	
