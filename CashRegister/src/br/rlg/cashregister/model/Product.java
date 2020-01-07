package br.rlg.cashregister.model;

import java.util.Objects;

public class Product {
	
	private int	id;
	private String description;
	private double price;
	private UnitType unitType;

	public Product(int id, String description, double price, UnitType unitType) {
		
		this.id = id;
		this.description = description;
		this.price = price;
		this.unitType = unitType;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}
	
	public UnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, price, unitType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		return Objects.equals(description, other.description) && id == other.id
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && unitType == other.unitType;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", description=" + description + ", price=" + price + ", unitType=" + unitType
				+ "]";
	}

}
