package br.rlg.cashregister.model;

public class Discount {

	private int productID;
	private int quantity;
	private int discountPercentage;
	
	public Discount(int productID, int quantity, int discountPercentage) {
		this.productID = productID;
		this.quantity = quantity;
		this.discountPercentage = discountPercentage;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getProductID() {
		return productID;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}
}
