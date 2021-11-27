package deliveriesCompany_208113332;

public class Delivery implements DeliveryMemberMethods{
	protected String deliveryCode;
	protected double price;
	protected Date dateInvited;
	protected boolean isArrivedToMember;

	/*constructors*/
	public Delivery(String deliveryCode)
	{
		this.deliveryCode = deliveryCode;
	}
	public Delivery(String deliveryCode,double price, Date dateInvited) {
		this(deliveryCode);
		this.price = price;
		this.dateInvited = dateInvited;
		this.isArrivedToMember = false;
		
	}
	
	/*functions*/
	
	/*This function checks if the deliveryCode is valid*/
	/*G = general delivery, E = express delivery, B = business delivery*/
	public boolean isValidDeliveryCode(String deliveryCode)
	{
		if(deliveryCode == null)
		{
			return false;
		}
		if(!(deliveryCode.startsWith("G")) && !(deliveryCode.startsWith("E")) && !(deliveryCode.startsWith("B")))
		{
			return false;
		}
		return true;
	}
	
	/*This function returns the first index of the delivery that has the deliveryCode the function gets*/
	/*if the deliveryCode is not found the function returns -1*/
	@Override
	public int findIndexOf(String deliveryCode, Manager[] managers) {
		if(managers == null)
		{
			return -1;
		}	
		for(int managerNum = 1;managerNum <= managers.length;managerNum++)
		{
			if(managers[managerNum - 1] == null || managers[managerNum - 1].getManagerMembers() == null)
			{
				return -1;
			}
			for(int memberNum = 1;memberNum <= managers[managerNum - 1].getManagerMembers().length;memberNum++)
			{
				if(managers[managerNum - 1].getManagerMembers()[memberNum - 1] == null)
				{
					return -1;
				}
				for(int deliveryNum = 1;deliveryNum <= managers[managerNum - 1].getManagerMembers()[memberNum - 1].getDeliveries().length;deliveryNum++)
				{
					if(managers[managerNum - 1].getManagerMembers()[memberNum - 1].getDeliveries() == null)
					{
						return -1;
					}
					if(managers[managerNum - 1].getManagerMembers()[memberNum - 1].getDeliveries()[deliveryNum - 1] == null)
					{
						return -1;
					}
					if(deliveryCode.equals(managers[managerNum - 1].getManagerMembers()[memberNum - 1].getDeliveries()[deliveryNum - 1].getDeliveryCode()))
					{
						return deliveryNum - 1;
					}
				}
			}
		}
		return -1;
	}
	
	/*Getters and Setters*/
	public String getDeliveryCode() {
		return deliveryCode;
	}

	public boolean setDeliveryCode(String deliveryCode,Manager[] managers) {
		if(managers == null)
		{
			System.out.println("Sorry, you can not set deliveryCode because managers Array is null");
			return false;
		}
		if(!this.isValidDeliveryCode(deliveryCode))
		{
			System.out.println("Invalid delivery code");
			return false;
		}
		if(this.findIndexOf(deliveryCode, managers) != -1)
		{
			System.out.println("Delivery code is already exist");
			return false;
		}
		this.deliveryCode = deliveryCode;
		return true;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getDateInvited() {
		return dateInvited;
	}

	public boolean setDateInvited(Date dateInvited) {
		if(dateInvited == null)
		{
			return false;
		}
		this.dateInvited = dateInvited;
		return true;
	}
	public boolean getIsArrivedToMember() {
		return isArrivedToMember;
	}

	public void setIsArrivedToMember(boolean isArrivedToMember) {
		this.isArrivedToMember = isArrivedToMember;
	}

	/*toString function*/
	@Override
	public String toString() {
		return "Delivery [deliveryCode=" + deliveryCode + ", price=" + price
				+ ", dateInvited=" + dateInvited + ", isArrivedToMember=" + isArrivedToMember + "]";
	}
	

	
	
	
	
}
