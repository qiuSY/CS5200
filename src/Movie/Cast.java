package Movie;

public class Cast {

	private int id;
	private String characterName;
	private int Movieid;
	private int Actorid;
	
	
	//constructor with all arguments
	public Cast(int id, String characterName, int movieid, int actorid) {
		super();
		this.id = id;
		this.characterName = characterName;
		Movieid = movieid;
		Actorid = actorid;
	}

//constructor without any argument
	public Cast() {
	super();
}	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}




	public String getCharacterName() {
		return characterName;
	}




	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}




	public int getMovieid() {
		return Movieid;
	}




	public void setMovieid(int movieid) {
		Movieid = movieid;
	}




	public int getActorid() {
		return Actorid;
	}




	public void setActorid(int actorid) {
		Actorid = actorid;
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
