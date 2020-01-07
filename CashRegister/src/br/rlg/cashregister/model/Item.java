package br.rlg.cashregister.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {

	private Product product;
	private double quantity;
	
	public Item(Product product, double quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		
		return product;
	}
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getQuantity() {
		
		return quantity;
	}
	
	public double getItemPrice() {
	    
		BigDecimal bd = BigDecimal.valueOf(product.getPrice() * quantity);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    return bd.doubleValue();		
	}

	@Override
	public String toString() {
		
		if(product.getUnitType().equals(UnitType.EACH) || product.getUnitType().equals(UnitType.LT)) {
			if(quantity < 2) {
				return product.getDescription() + "\t\t\t" + this.getItemPrice();
			} else {
				return "(" + new Double(quantity).intValue() + ")" + product.getDescription() + "\n\t" + new Double(quantity).intValue() + "\t\t@\t" + product.getPrice() + "\t\t" + this.getItemPrice();
			}
		} else {
			return product.getDescription() + "\n\t" + quantity + " " + product.getUnitType() + "\t\t@\t" + product.getPrice() + "\\" + product.getUnitType() + "\t\t" + this.getItemPrice();
		}
	}
}
