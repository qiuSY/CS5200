package Movie;

import java.util.Date;

public class Comment {
		private int id;
		private String comment;
		private Date date;
		private int Userid;
		private int Movieid;

	public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public java.sql.Date getDate() {
			return (java.sql.Date) date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public int getUserid() {
			return Userid;
		}

		public void setUserid(int userid) {
			Userid = userid;
		}

		public int getMovieid() {
			return Movieid;
		}

		public void setMovieid(int movieid) {
			Movieid = movieid;
		}

	//constructor without any arguments
	public Comment() {
	super();
}
	//constructor with all arguments
	public Comment(int id, String comment, Date date, int userid, int movieid) {
		super();
		this.id = id;
		this.comment = comment;
		this.date = date;
		Userid = userid;
		Movieid = movieid;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}



}
