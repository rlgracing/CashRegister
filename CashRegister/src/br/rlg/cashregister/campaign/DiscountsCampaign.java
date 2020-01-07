package br.rlg.cashregister.campaign;

import java.util.HashMap;
import java.util.Map;

import br.rlg.cashregister.model.Discount;

public class DiscountsCampaign {
	
	private Map<Integer, Discount> discounts = new HashMap<Integer, Discount>();
	
	public void addCampaign(Discount discounts) {
		
		this.discounts.put(discounts.getProductID(), discounts);
	}
	
	public Discount getDiscounts(int productID) {
		
		return discounts.get(productID);
	}
}
