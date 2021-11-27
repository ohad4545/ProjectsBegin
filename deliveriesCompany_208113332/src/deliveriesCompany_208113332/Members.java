package deliveriesCompany_208113332;

import java.util.Arrays;

public class Members implements DeliveryMemberMethods{
	private String memberId;
	private String firstName;
	private String lastName;
	private String livingArea;/*the values can be: north , center, or south*/
	private Delivery[] deliveries;
	private int deliveriesArraySize;
	
	/*constructor*/
	public Members(String memberId, String firstName, String lastName, String livingArea) {
		super();
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.livingArea = livingArea;
		this.deliveriesArraySize = 0;
		this.deliveries = new Delivery[this.deliveriesArraySize];
	}
	
	/*functions*/
	
	/*This function returns true if added a new delivery to the member's deliveries array*/
	/*else, it returns false*/
	public boolean addDelivery(Delivery delivery)
	{
		if(delivery == null)
		{
			System.out.println("Sorry,delivery can not be added to this member deliveries list");
			return false;
		}
		this.deliveriesArraySize++;
		this.deliveries = Arrays.copyOf(this.deliveries, this.deliveriesArraySize);
		this.deliveries[this.deliveriesArraySize - 1] = delivery;
		return true;
	}
	
	/*this function returns false if the member id or manager is null.else,it sets the this.memberId to nextMemberId and returns true*/
	public boolean setToNextMemberId(String nextMemberId,Manager manager)
	{
		if(manager == null || nextMemberId == null)
		{
			return false;
		}
			
		this.memberId = nextMemberId;
		return true;
	}
	
	/*this function returns the first member id index that has the value of memberId*/
	/*if the memberId is not founds in the managers arrays, the function returns -1*/
	@Override
	public int findIndexOf(String memberId, Manager[] managers) {
		for(int managerNumber= 1;managerNumber <= managers.length;managerNumber++)
		{
			for(int memberNum = 1;memberNum <= managers[managerNumber - 1].getManagerMembers().length;memberNum++)
			{
				if(memberId.equals(managers[managerNumber - 1].getManagerMembers()[memberNum - 1].getMemberId()))
				{
					return memberNum -1;
				}
			}
		}
		return -1;
	}
	
	/*This function returns true if the String it got contains only digits,else it returns false*/
	public boolean isOnlyDigits(String str)
	{
		for(int index = 0;index < str.length();index++)
		{
			if(str.charAt(index) < '0'|| str.charAt(index) > '9')
				return false;
		}
		return true;
	}
	
	/*Getters and Setters*/
	
	public String getMemberId() {
		return memberId;
	}
	
	public boolean setMemberId(String memberId,Manager[] managers) {
		if(managers == null)
		{
			System.out.println("manager can not be null for setting memberId");
			return false;
		}
		if(!(this.isOnlyDigits(memberId)))
		{
			System.out.println("Id must contain only digits");
			return false;
		}
		if(memberId.length() != 9)
		{
			System.out.println("memberId must be at length of 9");
			return false;
		}
		if(this.findIndexOf(memberId, managers) != -1)
		{
			System.out.println("Id is already exist in one of the managers lists");
			return false;
		}
		
		this.memberId = memberId;
		return true;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public boolean setFirstName(String firstName) {
		if(firstName != null)
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
		if(lastName!= null)
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
		if(livingArea == null) 
		{
			System.out.println("livingArea can not be null. it must be north,center or south");
			return false;
		}
		if(!(Service.isValidArea(livingArea)))
		{
			System.out.println("Invalid member's living area.must be north,center or south");
			return false;
		}
		this.livingArea = livingArea;
		this.livingArea.toLowerCase();
		return true;
	}
	public Delivery[] getDeliveries() {
		return deliveries;
	}
	public void setDeliveries(Delivery[] deliveries) {
		this.deliveries = deliveries;
	}
	
	
	public int getDeliveriesArraySize() {
		return this.deliveriesArraySize;
	}

	public void setDeliveriesArraySize(int deliveriesArraySize) {
		this.deliveriesArraySize = deliveriesArraySize;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", livingArea=" + livingArea + ", deliveries=" + Arrays.toString(deliveries) + "]";
	}

	


}
