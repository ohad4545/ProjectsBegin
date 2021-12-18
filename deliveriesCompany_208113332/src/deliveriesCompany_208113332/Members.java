package deliveriesCompany_208113332;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Members implements DeliveryMemberMethods,Comparable<Members>{
	private String memberId;
	private String firstName;
	private String lastName;
	private String livingArea;/*the values can be: north , center, or south*/
	private ArrayList<Delivery> deliveries;

	
	/*constructors*/
	public Members(String memberId)
	{
		this.memberId = memberId;
	}
	public Members(String memberId, String firstName, String lastName, String livingArea) {
		super();
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.livingArea = livingArea;
		this.deliveries = new ArrayList<Delivery>();
	}
	
	/*functions*/
	/*This function returns the manager which has a member with the id the function got*/
	/*if the id is not found the function returns null*/
	@Override
	public Manager whichManagerStrExists(String str, ArrayList<Manager> managers) {
		if(str == null || managers == null)
		{
			return null;
		}
		Iterator<Manager> itr= managers.iterator();
		Manager tmpManager;
		while(itr.hasNext())
		{
			tmpManager = itr.next();
			if(tmpManager.getManagerMembers().containsKey(memberId))
			{
				return tmpManager;
			}
		}
		return null;
	}
	/*This function does the compareTo method according to member's lastNames*/
	/*According to the ABC*/
	@Override
	public int compareTo(Members m) {
		if(m == null || m.getLastName() == null ||this.getLastName() == null)
			return 1;
		if(this.getLastName().toLowerCase().compareTo(m.getLastName().toLowerCase()) == 0)
		{
			return this.getMemberId().compareTo(m.getMemberId());
		}
		return this.getLastName().toLowerCase().compareTo(m.getLastName().toLowerCase());
	}
	
	/*This function returns true if added a new delivery to the member's deliveries array*/
	/*else, it returns false*/
	public boolean addDelivery(Delivery delivery)
	{
		if(delivery == null)
		{
			System.out.println("Sorry,delivery can not be added to this member deliveries list");
			return false;
		}
		deliveries.add(delivery);
		return true;
	}
	
	/*This function returns true if the String it got contains only digits,else it returns false*/
	public boolean isOnlyDigits(String str)
	{
		if(str == null)
			return false;
		for(int index = 0;index < str.length();index++)
		{
			if(str.charAt(index) < '0'|| str.charAt(index) > '9')
				return false;
		}
		return true;
	}
	
	/*This function returns true if the memberId parameter is valid.else it returns false */
	public boolean isValidMemberId(String memberId,ArrayList<Manager> managers)
	{
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
		return true;
	}
	
	/*This function print the member details without his deliverie's ArrayList*/
	public String printMemberWithoutDeliveries()
	{
		return "Members [memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName + ", livingArea="
				+ livingArea + "]";
	}
	
	/*Getters and Setters*/
	
	public String getMemberId() {
		return memberId;
	}
	
	public boolean setMemberId(String memberId,ArrayList<Manager> managers,Manager manager)
	{
		String answer;
		Members tmpMember;
		Scanner input = new Scanner(System.in);
		if(!(this.isValidMemberId(memberId, managers)))
			return false;
		if(this.whichManagerStrExists(memberId, managers) == null)
		{
			this.memberId = memberId;
			return true;
		}
		if(this.whichManagerStrExists(memberId, managers).equals(manager))
		{
			System.out.println("This member's already exists in your members list");
			System.out.println("if you add this member you delete the current member details you already have");
			System.out.println("Are you sure you want to add this member?(type yes/no)");
			do
			{
				answer = input.next();
				if(!answer.toLowerCase().equals("yes") && !answer.toLowerCase().equals("no"))
				{
					System.out.println("Invalid answer please type yes/no");
				}
			}while(!answer.toLowerCase().equals("yes") && !answer.toLowerCase().equals("no"));
			
			if(answer.toLowerCase().equals("no"))
			{
				return false;
			}
			
			/*I did the next line condition because If the new Member I added overrides an old member in the members HashMap, it means I also need to update that in the lastDeliveries and ourMembersAndDeiveries TreeMaps */
			if(answer.toLowerCase().equals("yes"))
			{
				tmpMember = manager.getManagerMembers().get(memberId);
				DataBase.ourLastDeliveries.remove(tmpMember);
				manager.removeMemberFromMembersAndDeliveriesMaps(tmpMember);
			}
		}
		if(this.whichManagerStrExists(memberId, managers) != null && !this.whichManagerStrExists(memberId, managers).equals(manager))
		{
			System.out.println("Sorry, you can not add this member because it exists in other manager member's list");
			return false;
		}
		
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
	public ArrayList<Delivery> getDeliveries() {
		return deliveries;
	}
	public void setDeliveries(ArrayList<Delivery> deliveries) {
		this.deliveries = deliveries;
	}
	
	/*equals function*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Members other = (Members) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		return true;
	}

	/*toString function*/
	@Override
	public String toString() {
		return "Members [memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName + ", livingArea="
				+ livingArea + ", deliveries=" + deliveries + "]";
	}
	
}
