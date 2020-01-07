package br.rlg.cashregister;

import java.util.Map;

import br.rlg.cashregister.campaign.CouponsCampaign;
import br.rlg.cashregister.campaign.DiscountsCampaign;
import br.rlg.cashregister.model.Discount;
import br.rlg.cashregister.model.Item;
import br.rlg.cashregister.model.Product;
import br.rlg.cashregister.model.UnitType;

public class CashRegisterMain {

	public static void main(String[] args) {

		Product cheerios	= new Product(1, "Boxes of Cheerios", 6.99, UnitType.EACH);
		Product apple		= new Product(2, "Fugi Apple       ", 2.49, UnitType.LB);
		Product coke		= new Product(3, "Coca-Cola        ", 5, UnitType.LT);
		
		CouponsCampaign couponsCampaign = new CouponsCampaign(15, 5);
		
		DiscountsCampaign discountCampaign = new DiscountsCampaign();
		discountCampaign.addCampaign(new Discount(1, 2, 25));
		discountCampaign.addCampaign(new Discount(3, 2, 50));
		
		CashRegister cashRegister = new CashRegister();
		
		cashRegister.setCouponsCampaign(couponsCampaign);
		cashRegister.setDiscountsCampaign(discountCampaign);
		
		cashRegister.addItems(	new Item(cheerios, 2),
								new Item(apple, 0.5),
								new Item(apple, 0.12),
								new Item(coke, 2)
							);
		
		for(int i = 1 ; i <= cashRegister.getAllItems().size() ; i++) {
			
			Item item = cashRegister.getAllItems().get(i - 1);
			
			System.out.println(i + ".\t" + item.toString());
		}
		
		System.out.println("\nSub-Total\t\t\t\t\t" + cashRegister.getSubTotalAmount());

		if(!cashRegister.getDiscountItems().isEmpty()) {
			System.out.println("\nDiscounts");

			Map<Product, Double> discounts = cashRegister.getDiscountItems();
			
			discounts.entrySet().stream()
								.forEach(entry -> System.out.println("\t" + entry.getKey().getDescription() + "\t" + discountCampaign.getDiscounts(entry.getKey().getId()).getDiscountPercentage() + "%\t" + cashRegister.getItemsCounter().get(entry.getKey().getId()) + "x\t-" + entry.getValue()));
			
			System.out.println("Total Discounts\t\t\t\t\t-" + cashRegister.getTotalDiscountAmount());
		}
		
		System.out.println("\nTotal\t\t\t\t\t\t" + cashRegister.getTotalAmount());
		
		int coupons = cashRegister.getCoupons();
		
		if(coupons > 0) {

			System.out.println("\nCoupons\t" + coupons + "x\t$" + couponsCampaign.getMoneyOff() + "=\t$" + (coupons * couponsCampaign.getMoneyOff()));
		}
	}

}
