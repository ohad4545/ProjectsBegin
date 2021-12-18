package deliveriesCompany_208113332;

public class Express extends Delivery {
	private Date deadlineArriveDate;
	private double afterExpressAdditionPrice;
	
	/*constructor*/
	public Express(String deliveryCode,double price, Date dateInvited,Date deadlineArriveDate,double afterExpressAdditionPrice) {
		super(deliveryCode,price,dateInvited);
		this.deadlineArriveDate = deadlineArriveDate;
		this.afterExpressAdditionPrice = afterExpressAdditionPrice;
	}
	
	/*Getter and Setters*/
	public Date getDeadlineArriveDate() {
		return deadlineArriveDate;
	}

	public boolean setDeadlineArriveDate(Date deadlineArriveDate) {
		if(deadlineArriveDate != null)
		{
			this.deadlineArriveDate = deadlineArriveDate;
			return true;
		}
		return false;
	}

	public double getAfterExpressAdditionPrice() {
		return afterExpressAdditionPrice;
	}

	public void setAfterExpressAdditionPrice(double afterExpressAdditionPrice) {
		this.afterExpressAdditionPrice = afterExpressAdditionPrice;
	}

	/*toString function*/
	@Override
	public String toString() {
		return super.toString() + "Express [deadlineArriveDate=" + deadlineArriveDate + ", afterExpressAdditionPrice="
				+ afterExpressAdditionPrice + "]";
	}

	
	
	

	

}
