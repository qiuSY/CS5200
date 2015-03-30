package Movie;

import java.util.Date;

public class Actor {

	private int id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	
	
	//constructor without any argument
	
	
	public Actor() {
		super();
	}




//constructor with all arguments
	public Actor(int id, String firstName, String lastName, Date dateOfBirth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getFirstName() {
		return firstName;
	}





	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}





	public String getLastName() {
		return lastName;
	}





	public void setLastName(String lastName) {
		this.lastName = lastName;
	}





	public java.sql.Date getDateOfBirth() {
		return (java.sql.Date) dateOfBirth;
	}





	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}





	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
