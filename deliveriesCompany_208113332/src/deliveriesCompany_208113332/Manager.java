package deliveriesCompany_208113332;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Manager {
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String managerArea;
	private HashMap<String,Members> managerMembers;
	
	/*constructor*/
	public Manager(String userName, String password, String managerArea,String firstName,String lastName) {
		super();
		this.userName = userName;
		this.password = password;
		this.managerArea = managerArea;
		this.managerMembers = new HashMap<String,Members>();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/*functions*/
	
	/*This function returns true if the new member it got was added to the managerMembers HashMap*/
	/*else, it returns false*/
	public boolean addMember(Members member)
	{
		if(member == null)
		{
			System.out.println("Sorry, the member was not added");
			return false;
		}
		this.managerMembers.put(member.getMemberId(), member);
		return true;
	}
	
	/*This function gets a member id and returns "true" if the member was removed from the manager members array successfully*/
	/*else,it returns "false", for example: the member id we get does not exist in the specific manager member's array*/
	public boolean removeMember(String memberId)
	{
		Members tmpMember;
		
		if(memberId == null || !(this.managerMembers.containsKey(memberId)))
		{
			return false;
		}
		tmpMember = this.managerMembers.remove(memberId); 
		this.removeMemberFromMembersAndDeliveriesMaps(tmpMember);
		if(DataBase.ourLastDeliveries.containsKey(tmpMember))
			DataBase.ourLastDeliveries.remove(tmpMember);
		
		return true;
	}
	
	/*This function removes the tmpMember from MembersAndDeliveries1,2 TreeMaps (if the member is found in the TreeMaps)*/
	 public void removeMemberFromMembersAndDeliveriesMaps(Members tmpMember)
	 {
		 Integer currentCountTree;
		 ArrayList<Integer> countTreeList = new ArrayList<Integer>();
		 if(tmpMember == null)
			 return;
		 
		 /*finding the whole countTree values that their values are the tmpMember values*/
		 for(Entry<Integer,Members> entry: DataBase.ourMembersAndDeliveries1.entrySet())
		 {
			 if(entry.getValue().equals(tmpMember))
			 {
				 countTreeList.add(entry.getKey());
			 }
		 }
		 
		 /*removing the whole countTree values that are the tmpMember value and all of its deliveries*/
		 Iterator<Integer> itr = countTreeList.iterator();
		 while(itr.hasNext())
		 {
			 currentCountTree = itr.next();
			 DataBase.ourMembersAndDeliveries1.remove(currentCountTree);
			 DataBase.ourMembersAndDeliveries2.remove(currentCountTree);
		 } 	
	 }
	 
	/*This function prints the members of the manager*/
	public void printManagerMembers()
	{
		for(Entry<String,Members> entry:this.managerMembers.entrySet())
		{
			System.out.println(entry.getValue());
		}
	}
		
	/*Getters and Setters*/
	public String getUserName() {
		return userName;
	}
	public boolean setUserName(String userName) {
		if(userName != null)
		{
			this.userName = userName;
			return true;
		}
		return false;
	}
	public String getPassword() {
		return password;
	}
	public boolean setPassword(String password) {
		if(password != null)
		{
			this.password = password;
			return true;
		}
		return false;
	}
	public String getManagerArea() {
		return managerArea;
	}
	public boolean setManagerArea(String managerArea) {
		if(managerArea != null && Service.isValidArea(managerArea))
		{
			this.managerArea = managerArea;
			this.managerArea.toLowerCase();
			return true;
		}	
		return false;
	}
	public HashMap<String,Members> getManagerMembers() {
		return managerMembers;
	}
	public boolean setManagerMembers(HashMap<String,Members> managerMembers) {
		if(managerMembers != null)
		{
			this.managerMembers = managerMembers;
			return true;
		}
		return false;
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
		if(lastName != null)
		{
			this.lastName = lastName;
			return true;
		}
		return false;
	}
	
	/*toString function*/
	@Override
	public String toString() {
		return "Manager [userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", managerArea=" + managerArea + ", managerMembers=" + managerMembers + "]";
	}
}
