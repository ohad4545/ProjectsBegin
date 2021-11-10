package deliveriesCompany_208113332;

public class Express extends Delivery {
	private String deadlineArriveDate;
	/*constructor*/
	public Express(String deliveryCode) {
		super(deliveryCode);
	}
	
	/*toString function*/
	@Override
	public String toString() {
		return super.toString() + " Express";
	}
}
