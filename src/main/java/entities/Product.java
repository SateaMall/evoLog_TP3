package entities;

import java.time.LocalDate;

public class Product {
    private int id;
    private String name;
    private double price;
    private LocalDate expirationDate;

    // Constructor
    public Product(int id, String name, double price, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return String.format("Product ID: %d | Name: %s | Price: %.2f | Expiration Date: %s",
                id, name, price, expirationDate.toString());
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
    
    
}
