package model;

public class Finance {

	private String gameName;
	private int soldNumber;
	private int rentedNumber;
	private double sellPrice;
	private double rentPrice;

	public Finance(String gameName, int soldNumber, int rentedNumber, double sellPrice, double rentPrice) {
		super();
		this.gameName = gameName;
		this.soldNumber = soldNumber;
		this.rentedNumber = rentedNumber;
		this.sellPrice = sellPrice;
		this.rentPrice = rentPrice;
	}

	public Finance() {

	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String name) {
		this.gameName = name;
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
