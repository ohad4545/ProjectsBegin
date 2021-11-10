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
	public Manager(String userName, String password, String managerArea) {
		super();
		this.userName = userName;
		this.password = password;
		this.managerArea = managerArea;
		this.membersArraySize = 0;
		this.managerMembers = new Members[membersArraySize];
	}
	
	/*functions*/
	
	/*This function gets a new member details and adds it to the managerMembers array*/
	public void addMember(Members member)
	{
		this.membersArraySize++;
		this.managerMembers = Arrays.copyOf(this.managerMembers,membersArraySize);
		this.managerMembers[this.membersArraySize - 1] = new Members(member.getMemberCode(), member.getFirstName(), member.getLastName(), member.getLivingArea());
		
	}
	
	/*This function gets a member code and returns "true" if the member was removed from the manager members array successfully*/
	/*else,it returns "false", for example: the member code we get does not exist in the specific manager member's array*/
	public boolean removeMember(String memberCode)
	{
		for(int memberNumber = 1;memberNumber <= this.managerMembers.length;memberNumber++)
		{
			if(memberCode.equals(this.managerMembers[memberNumber - 1].getMemberCode()))
			{
				int tmpMemberNumber = memberNumber;
				while(tmpMemberNumber < this.managerMembers.length)
				{
					/*changing the details of the current member to the details of the next one, and so on*/
					this.managerMembers[tmpMemberNumber - 1].setMemberCodeByRemove(this.managerMembers[tmpMemberNumber].getMemberCode(),this);
					this.managerMembers[tmpMemberNumber - 1].setFirstName(this.managerMembers[tmpMemberNumber].getFirstName());
					this.managerMembers[tmpMemberNumber - 1].setLastName(this.managerMembers[tmpMemberNumber].getLastName());
					this.managerMembers[tmpMemberNumber - 1].setLivingArea(this.managerMembers[tmpMemberNumber].getLivingArea());
					this.managerMembers[tmpMemberNumber - 1].setDeliveries(this.managerMembers[tmpMemberNumber].getDeliveries());
					tmpMemberNumber++;
				}
				this.membersArraySize--;
				managerMembers = Arrays.copyOf(managerMembers,membersArraySize);
				return true;
			}
		}
		return false;
	}
	/*This function prints the members of the manager*/
	public void printManagerMembers()
	{
		for(int memberNumber = 1;memberNumber <= this.managerMembers.length;memberNumber++)
		{
			System.out.println(this.managerMembers[memberNumber - 1]);
		}
	}
	/*This function returns the index of the member code, if the member code exists, in the specific manager members array*/
	/*else,it returns -1*/
	public int indexMemberCode(String memberCode)
	{
		for(int memberNum = 1;memberNum <=this.managerMembers.length;memberNum++)
		{
			if(memberCode.equals(this.managerMembers[memberNum - 1].getMemberCode()))
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
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getManagerArea() {
		return managerArea;
	}
	public boolean setManagerArea(String managerArea) {
		if(!(managerArea.equals("north")) && !(managerArea.equals("center")) && !(managerArea.equals("north")))
		{
			return false;
		}
		this.managerArea = managerArea;
		return true;
	}
	public Members[] getManagerMembers() {
		return managerMembers;
	}
	public void setManagerMembers(Members[] managerMembers) {
		this.managerMembers = managerMembers;
	}
	
	/*toString function*/
	@Override
	public String toString() {
		return "Manager [userName=" + userName + ", password=" + password + ", managerArea=" + managerArea
				+ ", managerMembers=" + Arrays.toString(managerMembers) + "]";
	}

}
