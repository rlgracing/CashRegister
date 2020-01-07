package br.rlg.cashregister.campaign;

public class CouponsCampaign {
	
	private int threshould;
	private int moneyOff;
	
	public CouponsCampaign(int threshould, int moneyOff) {
		this.threshould = threshould;
		this.moneyOff = moneyOff;
	}

	public void setThreshould(int threshould) {
		this.threshould = threshould;
	}

	public int getThreshould() {
		return threshould;
	}

	public void setMoneyOff(int moneyOff) {
		this.moneyOff = moneyOff;
	}

	public int getMoneyOff() {
		return moneyOff;
	}
	
	public int getCoupons(double amount) {
		int qtyCoupons = 0;
		
		if(threshould > 0 && amount > 0 && amount >= threshould) {
			qtyCoupons = new Double(amount / threshould).intValue();
		}
		
		return qtyCoupons;
	}
}
