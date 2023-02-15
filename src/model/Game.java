package model;

public class Game {

	private String name;
	private int sellStock;
	private int rentStock;
	private double sellPrice;
	private double rentPrice;

	public Game(String name, int sellStock, int rentStock, double sellPrice, double rentPrice) {
		super();
		this.name = name;
		this.sellPrice = sellPrice;
		this.rentPrice = rentPrice;
		this.sellStock = sellStock;
		this.rentStock = rentStock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getSellStock() {
		return sellStock;
	}

	public void setSellStock(int sellStock) {
		this.sellStock = sellStock;
	}

	public int getRentStock() {
		return rentStock;
	}

	public void setRentStock(int rentStock) {
		this.rentStock = rentStock;
	}

}
