//Ohad Cohen 208113332
/*This program represents a managing deliveries system */
package deliveriesCompany_208113332;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int NUMBEROFMANAGERS = 3;
		Manager[] managers = new Manager[NUMBEROFMANAGERS];
		char apostrophes = '"';
		String userName,password,memberCode,firstName,lastName,livingArea,deliveryCode;
		boolean flagLogIn = false;
		char user_choice = '0';
		Members tmpMember;
		Delivery tmpDelivery;
		boolean isRemoveMember,isValidDeliveryCode,isValidLivingArea;
		System.out.println("Welcome to " + apostrophes + "Deliveries For You" + apostrophes + " deliveries managing system");
		Scanner input = new Scanner(System.in);
		
		/*Building the managers array*/
		/*managers[0] is the manager of the north deliveries*/
		/*manager[1] is the manager of the center deliveries*/
		/*manager[2] is the manager of the south deliveries*/
		managers[0] = new Manager("adminNorth","adminNorth","north");
		managers[1] = new Manager("adminCenter","adminCenter","center");
		managers[2] = new Manager("adminSouth","adminSouth","south");
		
		/*Getting the userName and password*/
		do
		{
			System.out.println("Please insert your username: ");
			userName = input.nextLine();
			System.out.println("Please insert your password: ");
			password = input.nextLine();
			
			/*userName and password input check*/
			for(int managerNum = 1;managerNum<=managers.length;managerNum++)
			{
				if(userName.equals(managers[managerNum -1].getUserName()))
				{
					if(password.equals(managers[managerNum -1].getPassword()))
					{
						flagLogIn = true;
					}
				}	
			}
			if(!flagLogIn)
			{
				System.out.println("one of your details, username or password, are invalid ");
			}
		}while(flagLogIn == false);
		
		/* main menu*/
		System.out.println("Welcome " + userName);
		while(user_choice !='4')
		{
			do
			{
				System.out.println("Please choose option(1-4): ");
				System.out.println("1 - Add member to my member's list");
				System.out.println("2 - Remove member from my member's list");
				System.out.println("3 - Add new delivery for customer");
				System.out.println("4 - Exit");
				user_choice = input.next().charAt(0);
				if(user_choice < '1' || user_choice > '4')
				{
					System.out.println("Invalid choice");
				}
			}while(user_choice < '1' || user_choice > '4');
			
			input.nextLine();
			/*Option 1*/
			
			/*Getting the member details*/
			if(user_choice == '1')
			{
				do
				{
					System.out.println("Please enter the member code:");
					memberCode = input.nextLine();
					System.out.println("Please enter the member's first name : ");
					firstName = input.nextLine();
					System.out.println("Please enter the member's last name: ");
					lastName = input.nextLine();
					System.out.println("Please enter the member's living area(living area can be north,center or south): ");
					livingArea = input.nextLine();
					isValidLivingArea = true;
					tmpMember = new Members(memberCode,firstName,lastName,livingArea);
					
					/*member's details input check*/
					if(!(tmpMember.setMemberCode(memberCode,managers)))
					{
						System.out.println("memberCode's already exists");
					}
					if(!(tmpMember.setFirstName(firstName)))
					{
						System.out.println("Firstname can not include numbers");
					}
					if(!(tmpMember.setLastName(lastName)))
					{
						System.out.println("Lastname can not include numbers");
					}
					if(!(tmpMember.setLivingArea(livingArea)))
					{
						System.out.println("Invalid member's living area");
					}
					if(!(livingArea.equals(managers[Service.indexOfManager(managers, userName)].getManagerArea())))
					{
						System.out.println("The admin of the " + managers[Service.indexOfManager(managers, userName)].getManagerArea()
						+ " can add only member from the " + managers[Service.indexOfManager(managers, userName)].getManagerArea());
						isValidLivingArea = false;
					}
					if(!(tmpMember.setMemberCode(memberCode,managers)) || !(tmpMember.setFirstName(firstName)) || !(tmpMember.setLastName(lastName)) || !(tmpMember.setLivingArea(livingArea)) || !(isValidLivingArea))
						System.out.println("You will be asked to retype the member's details");
				}while(!(tmpMember.setMemberCode(memberCode,managers)) || !(tmpMember.setFirstName(firstName)) || !(tmpMember.setLastName(lastName)) || !(tmpMember.setLivingArea(livingArea)) || !(isValidLivingArea));
				
				/*Adding the member*/
				managers[Service.indexOfManager(managers,userName)].addMember(tmpMember);
				System.out.println("Member was added successfully");
				System.out.println("Your current member's list:");
				managers[Service.indexOfManager(managers,userName)].printManagerMembers();
			}
			
			
			/*Option 2*/
			if(user_choice == '2')
			{
				System.out.println("Please enter the code of the member you would like to remove");
				memberCode = input.nextLine();
				
				/*Removing the member and member's code input check*/
				isRemoveMember = false;
				do
				{
					if(!(managers[Service.indexOfManager(managers,userName)].removeMember(memberCode)))
					{
						System.out.println("This member code does not exist is your list, so you can not remove it, please retype the member's code you would like to remove");
						memberCode = input.nextLine();
					}
					else
					{
						isRemoveMember = true;
					}
				}while(!isRemoveMember);
				System.out.println("Member was removed successfully");
				System.out.println("Your current member's list:");
				managers[Service.indexOfManager(managers,userName)].printManagerMembers();
			}
			
			/*Option 3*/
			if(user_choice == '3')
			{
				/*Getting the member code and member code input check*/
				do 
				{
					System.out.println("Please enter the member code of the member you want to add a delivery");
					memberCode = input.nextLine();
					if(managers[Service.indexOfManager(managers,userName)].indexMemberCode(memberCode) == -1)
					{
						System.out.println("The member is not exist in your member's list");
					}
				}while(managers[Service.indexOfManager(managers,userName)].indexMemberCode(memberCode) == -1);
				
				/*Getting the delivery code and delivery code input check*/
				do
				{
					isValidDeliveryCode = false;
					System.out.println("please enter the delivery code(delivery code can startwith: G(general delivery), E(express delivery) or B(business delivery)");
					deliveryCode = input.nextLine();
					tmpDelivery = new Delivery(deliveryCode);
					if(!(tmpDelivery.setDeliveryCode(deliveryCode, managers)))
					{
						System.out.println("This delivery code is invalid or already exists");
					}
					else
					{
						isValidDeliveryCode = true;
					}
				}while(!isValidDeliveryCode);
				
				/*what kind of delivery is tmpDelivery(G,E or B)*/
				if(tmpDelivery.getDeliveryCode().startsWith("E"))
				{
					tmpDelivery = new Express(tmpDelivery.getDeliveryCode());
				}
				if(tmpDelivery.getDeliveryCode().startsWith("B"))
				{
					tmpDelivery = new Business(tmpDelivery.getDeliveryCode());
				}
				
				/*adding the delivery*/
				managers[Service.indexOfManager(managers, userName)].getManagerMembers()[managers[Service.indexOfManager(managers,userName)].indexMemberCode(memberCode)].addDelivery(tmpDelivery);
				System.out.println("The delivery was added successfully");
				System.out.println("Your current member's list: ");
				managers[Service.indexOfManager(managers,userName)].printManagerMembers();
			}	
		}
		
		/*Option 4*/
		System.out.println("Thanks for using " + apostrophes + "Deliveries For You" + apostrophes + " deliveries managing system");
	}
}
