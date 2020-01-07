package br.rlg.cashregister;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.rlg.cashregister.campaign.CouponsCampaign;
import br.rlg.cashregister.campaign.DiscountsCampaign;
import br.rlg.cashregister.model.Discount;
import br.rlg.cashregister.model.Item;
import br.rlg.cashregister.model.Product;
import br.rlg.cashregister.model.UnitType;

public class CashRegister {
	
	private List<Item> items = new ArrayList<Item>();
	private Map<Integer, Integer> itemsCounter = new HashMap<Integer, Integer>();
	private CouponsCampaign couponsCampaign;
	private DiscountsCampaign discountsCampaign;
	private double total;
	
	public void setCouponsCampaign(CouponsCampaign couponsCampaign) {
		this.couponsCampaign = couponsCampaign;
	}

	public void setDiscountsCampaign(DiscountsCampaign discountsCampaign) {
		this.discountsCampaign = discountsCampaign;
	}

	public void addItem(Item item) {
		
		items.add(item);
		
		int productID = item.getProduct().getId();
		int count = 0;
		
		if(itemsCounter.get(productID) != null) {
			if(item.getProduct().getUnitType().equals(UnitType.EACH) || item.getProduct().getUnitType().equals(UnitType.LT))
				count = itemsCounter.get(productID) + new Double(item.getQuantity()).intValue();
			else
				count = itemsCounter.get(productID) + 1;
		} else {
			if(item.getProduct().getUnitType().equals(UnitType.EACH) || item.getProduct().getUnitType().equals(UnitType.LT))
				count = new Double(item.getQuantity()).intValue();
			else
				count = 1;
		}
		
		itemsCounter.put(item.getProduct().getId(), count);
	}

	public void addItems(Item... item) {
		
		for(Item itemx : Arrays.asList(item)) {
			
			this.addItem(itemx);
		}
	}
	
	public double getSubTotalAmount() {
		
		total =  items.stream()
						.mapToDouble(item -> item.getItemPrice())
						.sum();

		BigDecimal bd = BigDecimal.valueOf(total);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    
	    total =  bd.doubleValue();		
		
		return total;
	}
	
	public Item getItem(int productID) {
		
		return items.stream()
					.filter(item -> item.getProduct().getId() == productID)
					.findFirst()
					.get();
	}
	
	public List<Item> getAllItems() {
		
		return items;
	}
	
	public int getCoupons() {

		if(couponsCampaign != null) {
			
			return couponsCampaign.getCoupons(total);
		}
		
		return 0;
	}
	
	public Map<Product, Double> getDiscountItems() {

		Map<Product, Double> discountsMap = new HashMap<Product, Double>();
		if(itemsCounter != null) {
			
//			System.out.println(cashRegister.getItemsCounter().toString());
			
			for (Iterator<Integer> iterator = itemsCounter.keySet().iterator(); iterator.hasNext();) {
				int productID = iterator.next();
				
				Discount discounts = discountsCampaign.getDiscounts(productID);
				
				if(discounts != null && itemsCounter.get(productID) >= discounts.getQuantity()) {
					
					Item item = getItem(productID);
					
					if(item != null) {
						Product product = item.getProduct();
						
						double discountValue = (product.getPrice() * (discounts.getDiscountPercentage()/100.0) * itemsCounter.get(productID));

						BigDecimal bd = BigDecimal.valueOf(discountValue);
					    bd = bd.setScale(2, RoundingMode.HALF_UP);
					    
						discountsMap.put(product,  bd.doubleValue());
						
						//System.out.println("Discount\t"+product.getDescription() + "\t" + (product.getPrice() * (discounts.getDiscountPercentage()/100.0) * itemsCounter.get(productID)));
					}
				}
			}
		}
		
		return discountsMap;
	}

	public double getTotalDiscountAmount() {
		
		total =  getDiscountItems().entrySet()
									.stream()
									.mapToDouble(entry -> entry.getValue())
									.sum();

		BigDecimal bd = BigDecimal.valueOf(total);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
	    
	    total =  bd.doubleValue();		
		
		return total;
	}
	
	public double getTotalAmount() {
		
		return getSubTotalAmount() - getTotalDiscountAmount();
	}
	
	public Map<Integer, Integer> getItemsCounter() {
		
		return itemsCounter;
	}
}
