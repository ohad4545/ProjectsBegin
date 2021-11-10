package deliveriesCompany_208113332;

public class Delivery {
	protected String deliveryCode;
	protected String items;
	protected double price;
	protected String dateInvited;
	protected String dateArrived;
	/*constructor*/
	public Delivery(String deliveryCode) {
		super();
		this.deliveryCode = deliveryCode;
	}
	
	/*functions*/
	
	/*This function checks if the deliveryCode is valid*/
	/*G = general delivery, E = express delivery, B = business delivery*/
	public boolean isValidDeliveryCode(String deliveryCode)
	{
		if(!(deliveryCode.startsWith("G")) && !(deliveryCode.startsWith("E")) && !(deliveryCode.startsWith("B")))
		{
			return false;
		}
		return true;
	}
	
	/*Getters and Setters*/
	public String getDeliveryCode() {
		return deliveryCode;
	}

	public boolean setDeliveryCode(String deliveryCode,Manager[] managers) {
		if(!this.isValidDeliveryCode(deliveryCode))
		{
			return false;
		}
		
		/*checking if the code is already exist in the whole deliveries array or not*/
		for(int managerNum = 1;managerNum <= managers.length;managerNum++)
		{
			for(int memberNum = 1;memberNum <= managers[managerNum - 1].getManagerMembers().length;memberNum++)
			{
				for(int deliveryNum = 1;deliveryNum <= managers[managerNum - 1].getManagerMembers()[memberNum - 1].getDeliveries().length;deliveryNum++)
				{
					if(deliveryCode.equals(managers[managerNum - 1].getManagerMembers()[memberNum - 1].getDeliveries()[deliveryNum - 1].getDeliveryCode()))
					{
						return false;
					}
				}
			}
		}
		this.deliveryCode = deliveryCode;
		return true;
	}

	/*toString function*/
	@Override
	public String toString() {
		return "Delivery [deliveryCode=" + deliveryCode + "]";
	}
	
}
