package model;

public class Finance {

	private String name;
	private int soldNumber;
	private int rentedNumber;
	private double sellPrice;
	private double rentPrice;

	public Finance(String name, int soldNumber, int rentedNumber, double sellPrice, double rentPrice) {
		super();
		this.name = name;
		this.soldNumber = soldNumber;
		this.rentedNumber = rentedNumber;
		this.sellPrice = sellPrice;
		this.rentPrice = rentPrice;
	}

	public Finance() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSoldNumber() {
		return soldNumber;
	}

	public void setSoldNumber(int soldNumber) {
		this.soldNumber = soldNumber;
	}

	public int getRentedNumber() {
		return rentedNumber;
	}

	public void setRentedNumber(int rentedNumber) {
		this.rentedNumber = rentedNumber;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public double getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}

}
