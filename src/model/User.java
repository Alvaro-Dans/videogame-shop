package model;

public class User {

	private String name;
	private int age;
	private String gender;
	private long points;

	public User(String name, int age, String sex, long points) {
		super();
		this.name = name;
		this.age = age;
		this.gender = sex;
		this.points = points;
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

}
