package deliveriesCompany_208113332;

public class Business extends Delivery{
	private String companyName;
	private double discountPrecentage;
	/*constructor*/
	public Business(String deliveryCode) {
		super(deliveryCode);	
	}
	
	/*toString functions*/
	@Override
	public String toString() {
		return super.toString() + " Business";
	}	
}
