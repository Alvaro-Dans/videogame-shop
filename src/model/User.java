package model;

public class User {


	private String name;
	private int age;
	private String gender;
	private long points;
	private String role;

	public User( String name, int age, String gender, long points, String role) {
		super();
		

		this.name = name;
		this.age = age;
		this.gender = gender;
		this.points = points;
		this.role = role;
	}

	public User() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	
	
	

}
