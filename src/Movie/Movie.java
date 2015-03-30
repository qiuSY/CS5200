package Movie;

import java.util.Date;

public class Movie {

	private int id;
	private String title;
	private String posterImage;
	private Date releaseDate;
	
	
	public Movie() {
		super();
	}



	//constructor with all arguments
	public Movie(int id, String title, String posterImage, Date releaseDate) {
		super();
		this.id = id;
		this.title = title;
		this.posterImage = posterImage;
		this.releaseDate = releaseDate;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getPosterImage() {
		return posterImage;
	}



	public void setPosterImage(String posterImage) {
		this.posterImage = posterImage;
	}



	public java.sql.Date getReleaseDate() {
		return (java.sql.Date) releaseDate;
	}



	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
