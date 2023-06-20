package model;

public class Game {

	private String name;
	private int sellStock;
	private int rentStock;

	public Game(String name, int sellStock, int rentStock) {
		super();
		this.name = name;
		this.sellStock = sellStock;
		this.rentStock = rentStock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
