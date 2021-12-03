package deliveriesCompany_208113332;

public class Business extends Delivery{
	private String companyName;
	private double afterDiscountPrice;
	
	/*constructor*/
	public Business(String deliveryCode,double price,Date dateInvited,String companyName,double afterDiscountPrice) {
		super(deliveryCode,price,dateInvited);	
		this.companyName = companyName;
		this.afterDiscountPrice = afterDiscountPrice;
	}
	
	/*Getters and Setters*/
	
	public String getCompanyName() {
		return companyName;
	}

	public boolean setCompanyName(String companyName) {
		if(companyName != null)
		{
			this.companyName = companyName;
			return true;
		}
		return false;
	}

	public double getAfterDiscountPrice() {
		return afterDiscountPrice;
	}

	public void setAfterDiscountPrice(double afterDiscountPrice) {
		this.afterDiscountPrice = afterDiscountPrice;
	}

	/*toString functions*/
	@Override
	public String toString() {
		return super.toString() + "Business [companyName=" + companyName + ", afterDiscountPrice=" + afterDiscountPrice + "]";
	}

	
	
}
