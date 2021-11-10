package deliveriesCompany_208113332;

import java.util.Arrays;

public class Members {
	private String memberCode;
	private String firstName;
	private String lastName;
	private String livingArea;/*the values can be: north , center, or south*/
	private Delivery[] deliveries;
	private int deliveriesArraySize;
	
	/*constructor*/
	public Members(String memberCode, String firstName, String lastName, String livingArea) {
		super();
		this.memberCode = memberCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.livingArea = livingArea;
		deliveriesArraySize = 0;
		this.deliveries = new Delivery[deliveriesArraySize];
	}
	
	/*functions*/
	
	/*This function adds a new delivery to the member's deliveries array*/
	public void addDelivery(Delivery delivery)
	{
		this.deliveriesArraySize++;
		this.deliveries = Arrays.copyOf(this.deliveries, this.deliveriesArraySize);
		if(delivery instanceof Business)
		{
			this.deliveries[this.deliveriesArraySize - 1] = new Business(delivery.getDeliveryCode());
		}
		else if(delivery instanceof Express)
		{
			this.deliveries[this.deliveriesArraySize - 1] = new Express(delivery.getDeliveryCode());
		}
		else
		{
			this.deliveries[this.deliveriesArraySize - 1] = new Delivery(delivery.getDeliveryCode());
		}	
	}
	
	/*this function checks if the member code exists at specific manager's members array*/
	public boolean setMemberCodeByRemove(String memberCode,Manager manager)
	{
		if(manager == null)
			return false;
		this.memberCode = memberCode;
		return true;
	}
	
	/*Getters and Setters*/
	
	public String getMemberCode() {
		return memberCode;
	}
	/*this function checks if the member code exists at the whole managers members arrays*/
	public boolean setMemberCode(String memberCode,Manager[] managers) {
		if(managers == null)
			return false;
		for(int managerNumber= 1;managerNumber <= managers.length;managerNumber++)
		{
			for(int memberNum = 1;memberNum <= managers[managerNumber - 1].getManagerMembers().length;memberNum++)
			{
				if(memberCode.equals(managers[managerNumber - 1].getManagerMembers()[memberNum - 1].getMemberCode()))
				{
					return false;
				}
			}
		}
		this.memberCode = memberCode;
		return true;
	}
	public String getFirstName() {
		return firstName;
	}
	public boolean setFirstName(String firstName) {
		if(Service.isValidName(firstName))
		{
			this.firstName = firstName;
			return true;
		}
		return false;		
	}
	public String getLastName() {
		return lastName;
	}
	public boolean setLastName(String lastName) {
		if(Service.isValidName(lastName))
		{
			this.lastName = lastName;
			return true;
		}
		return false;
	}
	public String getLivingArea() {
		return livingArea;
	}
	public boolean setLivingArea(String livingArea) {
		if(!(livingArea.equals("north")) && !(livingArea.equals("center")) && !(livingArea.equals("south")))
		{
			return false;
		}
		this.livingArea = livingArea;
		return true;
	}
	public Delivery[] getDeliveries() {
		return deliveries;
	}
	public void setDeliveries(Delivery[] deliveries) {
		this.deliveries = deliveries;
	}
	
	
	public int getDeliveriesArraySize() {
		return deliveriesArraySize;
	}

	public boolean setDeliveriesArraySize(int deliveriesArraySize) {
		if(deliveriesArraySize < 0)
		{
			return false;
		}
		this.deliveriesArraySize = deliveriesArraySize;
		return true;
	}

	@Override
	public String toString() {
		return "Members [memberCode=" + memberCode + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", livingArea=" + livingArea + ", deliveries=" + Arrays.toString(deliveries) + "]";
	}


}
