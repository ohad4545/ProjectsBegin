package deliveriesCompany_208113332;

import java.util.ArrayList;
import java.util.Iterator;

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
	
	/*This function returns the manager that has a member with delivery that equals to the deliveryCode the function gets*/
	/*if the deliveryCode is not found ,the function returns null*/
	@Override
	public Manager whichManagerStrExists(String deliveryCode, ArrayList<Manager> managers) {
		if(managers == null || deliveryCode == null)
		{
			return null;
		}	
		Manager tmpManager;
		Iterator<Manager> itr1= managers.iterator();
		while(itr1.hasNext())
		{
			 tmpManager = itr1.next();
			for(Members m:tmpManager.getManagerMembers().values())
			{
				Iterator<Delivery> itr2= m.getDeliveries().iterator();
				while(itr2.hasNext())
				{
					if(itr2.next().getDeliveryCode().equals(deliveryCode))
					{
						return tmpManager;
					}
				}
			}
		}
		return null;	
	}
	
	/*Getters and Setters*/
	public String getDeliveryCode() {
		return deliveryCode;
	}

	public boolean setDeliveryCode(String deliveryCode,ArrayList<Manager> managers) {
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
		if(this.whichManagerStrExists(deliveryCode, managers) != null)
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
		if(dateInvited != null)
		{
			this.dateInvited = dateInvited;
			return true;
		}
		return false;
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
