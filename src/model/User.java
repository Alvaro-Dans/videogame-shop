package model;

public class User {

	private String name;
	private int age;
	private String sex;
	private long points;

	public User(String name, int age, String sex, long points) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.points = points;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}

}
