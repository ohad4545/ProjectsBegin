package deliveriesCompany_208113332;

import java.util.Arrays;

public class Manager {
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String managerArea;
	private Members[] managerMembers;
	private int membersArraySize;
	
	/*constructor*/
	public Manager(String userName, String password, String managerArea,String firstName,String lastName) {
		super();
		this.userName = userName;
		this.password = password;
		this.managerArea = managerArea;
		this.membersArraySize = 0;
		this.managerMembers = new Members[this.membersArraySize];
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	/*functions*/
	
	/*This function returns true if the new member it got was added to the managerMembers array*/
	/*else, it returns false*/
	public boolean addMember(Members member)
	{
		if(member == null)
		{
			System.out.println("Sorry, the member was not added");
			return false;
		}
		this.membersArraySize++;
		this.managerMembers = Arrays.copyOf(this.managerMembers,this.membersArraySize);
		this.managerMembers[this.membersArraySize - 1] = new Members(member.getMemberId(), member.getFirstName(), member.getLastName(), member.getLivingArea());
		return true;
	}
	
	/*This function gets a member id and returns "true" if the member was removed from the manager members array successfully*/
	/*else,it returns "false", for example: the member id we get does not exist in the specific manager member's array*/
	public boolean removeMember(String memberId)
	{
		int memberNumber = this.indexMemberId(memberId);
		if(memberId == null || this.indexMemberId(memberId) == -1)
		{
			return false;
		}
		while(memberNumber < this.managerMembers.length - 1)
		{
			/*changing the details of the current member to the details of the next one, and so on*/
			this.managerMembers[memberNumber].setToNextMemberId(this.managerMembers[memberNumber + 1].getMemberId(),this);
			this.managerMembers[memberNumber].setFirstName(this.managerMembers[memberNumber + 1].getFirstName());
			this.managerMembers[memberNumber].setLastName(this.managerMembers[memberNumber + 1].getLastName());
			this.managerMembers[memberNumber].setLivingArea(this.managerMembers[memberNumber + 1].getLivingArea());
			this.managerMembers[memberNumber].setDeliveries(this.managerMembers[memberNumber + 1].getDeliveries());
			this.managerMembers[memberNumber].setDeliveriesArraySize(this.managerMembers[memberNumber + 1].getDeliveriesArraySize());
			memberNumber++;
		}
		this.membersArraySize--;
		managerMembers = Arrays.copyOf(managerMembers,this.membersArraySize);
		return true;
	}
	
	/*This function prints the members of the manager*/
	public void printManagerMembers()
	{
		for(int memberNumber = 1;memberNumber <= this.managerMembers.length;memberNumber++)
		{
			System.out.println(this.managerMembers[memberNumber - 1]);
		}
	}
	
	/*This function returns the index of the member id, if the member id exists, in the specific manager members array*/
	/*else,it returns -1*/
	public int indexMemberId(String memberId)
	{
		for(int memberNum = 1;memberNum <=this.managerMembers.length;memberNum++)
		{
			if(memberId.equals(this.managerMembers[memberNum - 1].getMemberId()))
			{
				return memberNum -1;
			}	
		}
		return -1;
	}
	
	/*Getters and Setters*/
	public String getUserName() {
		return userName;
	}
	public boolean setUserName(String userName) {
		if(userName == null)
		{
			return false;
		}
		this.userName = userName;
		return true;
	}
	public String getPassword() {
		return password;
	}
	public boolean setPassword(String password) {
		if(password == null)
		{
			return false;
		}
		this.password = password;
		return true;
	}
	public String getManagerArea() {
		return managerArea;
	}
	public boolean setManagerArea(String managerArea) {
		if(managerArea == null || !(Service.isValidArea(managerArea)))
		{
			return false;
		}
		this.managerArea = managerArea;
		this.managerArea.toLowerCase();
		return true;
	}
	public Members[] getManagerMembers() {
		return managerMembers;
	}
	public boolean setManagerMembers(Members[] managerMembers) {
		if(managerMembers == null)
			return false;
		this.managerMembers = managerMembers;
		return true;
	}
	
	public int getMembersArraySize() {
		return this.membersArraySize;
	}

	public void setMembersArraySize(int membersArraySize) {
		this.membersArraySize = membersArraySize;
	}

	public String getFirstName() {
		return firstName;
	}

	public boolean setFirstName(String firstName) {
		if(firstName == null)
		{
			return false;
		}
		this.firstName = firstName;
		return true;
	}

	public String getLastName() {
		return lastName;
	}

	public boolean setLastName(String lastName) {
		if(lastName == null)
		{
			return false;
		}
		this.lastName = lastName;
		return true;
	}

	/*toString function*/
	@Override
	public String toString() {
		return "Manager [userName=" + userName + ", password=" + password + ", Area=" + managerArea
				+ ", firstName=" + firstName + ", lastName=" + lastName + 
				", Members=" + Arrays.toString(managerMembers) + "]";
	}

}
