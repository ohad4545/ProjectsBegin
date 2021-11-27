//Ohad Cohen 208113332
/*This program represents a managing deliveries system */
package deliveriesCompany_208113332;

import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		final int NUMBEROFMANAGERS = 3;
		Manager[] managers = new Manager[NUMBEROFMANAGERS];
		final char apostrophes = '"';
		char user_choice = '0';
		String userName,password,memberId,firstName,lastName,livingArea,deliveryCode,companyName;
		Members tmpMember;
		Delivery tmpDelivery;
		boolean flagLogIn = false,isRemoveMember,isValidDeliveryCode,isValidLivingArea,isValidMemberDetails;
		double price,afterDiscountPrice,afterExpressAdditionPrice;
		Date dateInvited,deadlineArriveDate;
		System.out.println("Welcome to " + apostrophes + "Deliveries For You" + apostrophes + " deliveries managing system");
		Scanner input = new Scanner(System.in);
		
		/*Building the managers array*/
		/*managers[0] is the manager of the north deliveries*/
		/*manager[1] is the manager of the center deliveries*/
		/*manager[2] is the manager of the south deliveries*/
		
		                           /*userNames*//*Passwords*/
		managers[0] = new Manager("adminNorth","adminNorth","north","Avi","Cohen");
		managers[1] = new Manager("adminCenter","adminCenter","center","Moshe","Levi");
		managers[2] = new Manager("adminSouth","adminSouth","south","George","Jackson");
		
		/*Building the managers members arrays*/
		tmpMember = new Members("123456789", "Ohad", "Cohen", "north");
		managers[0].addMember(tmpMember);
		tmpMember = new Members("987654321", "Boaz", "Maoda", "north");
		managers[0].addMember(tmpMember);
		tmpMember = new Members("159357456", "Lior", "Sushard", "center");
		managers[1].addMember(tmpMember);
		tmpMember = new Members("852146927", "Samuel.L", "Jackson", "center");
		managers[1].addMember(tmpMember);
		tmpMember = new Members("004852147", "Neli", "Tagar", "south");
		managers[2].addMember(tmpMember);
		tmpMember = new Members("194682375", "Kobi", "Mahat", "south");
		managers[2].addMember(tmpMember);
		
		/*Building the deliveries for each member*/
		
		/*manager of the north members deliveries*/
		/*Id:123456789*/
		dateInvited = new Date(15,5,2020);
		tmpDelivery = new Delivery("G1",15.33,dateInvited);
		managers[0].getManagerMembers()[0].addDelivery(tmpDelivery);
		dateInvited = new Date(17,10,2021);
		deadlineArriveDate = new Date(18,11,2021);
		tmpDelivery = new Express("E1",100.45,dateInvited,deadlineArriveDate,120);
		managers[0].getManagerMembers()[0].addDelivery(tmpDelivery);
		
		/*Id:987654321*/
		dateInvited = new Date(4,1,2021);
		tmpDelivery = new Delivery("G2",38.12,dateInvited);
		managers[0].getManagerMembers()[1].addDelivery(tmpDelivery);
		dateInvited = new Date(17,10,2021);
		tmpDelivery = new Delivery("G3",370,dateInvited);
		managers[0].getManagerMembers()[1].addDelivery(tmpDelivery);
		
		/*manager of the center members deliveries*/
		/*Id: 159357456*/
		dateInvited = new Date(31,3,2019);
		tmpDelivery = new Delivery("G4",55.6,dateInvited);
		managers[1].getManagerMembers()[0].addDelivery(tmpDelivery);
		dateInvited = new Date(1,1,2021);
		tmpDelivery = new Business("B1",1000.78,dateInvited,"APU software systems",890.6);
		managers[1].getManagerMembers()[0].addDelivery(tmpDelivery);
		dateInvited = new Date(15,9,2021);
		
		/*Id:852146927*/
		tmpDelivery = new Delivery("G5",44.8,dateInvited);
		managers[1].getManagerMembers()[1].addDelivery(tmpDelivery);
		dateInvited = new Date(20,10,2021);
		deadlineArriveDate = new Date(29,11,2021);
		tmpDelivery = new Express("E2",350,dateInvited,deadlineArriveDate,400);
		managers[1].getManagerMembers()[1].addDelivery(tmpDelivery);
		
		/*manager of the south members deliveries*/
		/*Id:004852147*/
		dateInvited = new Date(31,3,2019);
		tmpDelivery = new Delivery("G6",55.6,dateInvited);
		managers[2].getManagerMembers()[0].addDelivery(tmpDelivery);
		dateInvited = new Date(1,1,2021);
		tmpDelivery = new Business("B2",50.4,dateInvited,"Nice lambs",45);
		managers[2].getManagerMembers()[0].addDelivery(tmpDelivery);
		
		/*Id:194682375*/
		dateInvited = new Date(15,9,2021);
		deadlineArriveDate = new Date(15,9,2021);
		tmpDelivery = new Express("E3",44.8,dateInvited,deadlineArriveDate,60);
		managers[2].getManagerMembers()[1].addDelivery(tmpDelivery);
		dateInvited = new Date(20,10,2021);
		tmpDelivery = new Delivery("G7",350,dateInvited);
		managers[2].getManagerMembers()[1].addDelivery(tmpDelivery);
		
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
		
		System.out.println("Manager's list:");
		for(int managerNumber = 1;managerNumber <= managers.length;managerNumber++)
		{
			System.out.println(managers[managerNumber - 1]);
		}
		
		/* main menu*/
		System.out.println("Welcome " + managers[Service.indexOfManager(managers, userName)].getFirstName() +" " + managers[Service.indexOfManager(managers, userName)].getLastName());
		while(user_choice !='4')
		{
			System.out.println("Please choose option(1-4): ");
			System.out.println("1 - Add member to my member's list");
			System.out.println("2 - Remove member from my member's list");
			System.out.println("3 - Add new delivery for customer");
			System.out.println("4 - Exit");
			user_choice = input.next().charAt(0);
			input.nextLine();
			/*Option 1*/
			
			/*Getting the member details*/
			if(user_choice == '1')
			{
				do
				{
					do
					{
						isValidMemberDetails = true;
						System.out.println("Please enter the member id:");
						memberId = input.nextLine();
						System.out.println("Please enter the member's first name : ");
						firstName = input.nextLine();
						System.out.println("Please enter the member's last name: ");
						lastName = input.nextLine();
						System.out.println("Please enter the member's living area(living area can be north,center or south): ");
						livingArea = input.nextLine();
						livingArea = livingArea.toLowerCase();
						isValidLivingArea = true;
						tmpMember = new Members(memberId,firstName,lastName,livingArea);
						if(!(livingArea.equals(managers[Service.indexOfManager(managers, userName)].getManagerArea())))
						{
							System.out.println("Sorry you can not add this member, the admin of the " + managers[Service.indexOfManager(managers, userName)].getManagerArea()
							+ " can add only member from the " + managers[Service.indexOfManager(managers, userName)].getManagerArea());
							isValidLivingArea = false;
						}
						if(!(tmpMember.setMemberId(memberId,managers)) ||  !(tmpMember.setLivingArea(livingArea)) || !(isValidLivingArea))
						{
							System.out.println("You will be asked to retype the member's details");
							isValidMemberDetails = false;
						}		
					}while(!(isValidMemberDetails));
				/*Adding the member*/
				}while(!(managers[Service.indexOfManager(managers,userName)].addMember(tmpMember)));
				System.out.println("Member was added successfully");
			}
			
			
			/*Option 2*/
			if(user_choice == '2')
			{
				isRemoveMember = false;
				if(managers[Service.indexOfManager(managers, userName)].getManagerMembers() == null || managers[Service.indexOfManager(managers, userName)].getManagerMembers().length == 0)
				{
					System.out.println("You can not remove a member if your members list is empty");
				}
				else
				{
					System.out.println("Please enter the code of the member you would like to remove");
					memberId = input.nextLine();
					
					/*Removing the member and member's code input check*/
					do
					{
						if(!(managers[Service.indexOfManager(managers,userName)].removeMember(memberId)))
						{
							System.out.println("This member id does not exist is your members list, so you can not remove it, please retype the member's id you would like to remove");
							memberId = input.nextLine();
						}
						else
						{
							isRemoveMember = true;
						}
				}while(!isRemoveMember);	
				System.out.println("Member was removed successfully");
				}
			}
			/*Option 3*/
			if(user_choice == '3')
			{
				if(managers[Service.indexOfManager(managers, userName)].getManagerMembers() == null || managers[Service.indexOfManager(managers, userName)].getManagerMembers().length == 0)
				{
					System.out.println("You can not add a delivery to a member if your members list is empty");
				}
				else
				{
					/*Getting the member code and member code input check*/
					do 
					{
						System.out.println("Please enter the member id of the member you want to add a delivery");
						memberId = input.nextLine();
						if(managers[Service.indexOfManager(managers,userName)].indexMemberId(memberId) == -1)
						{
							System.out.println("The member id is not exist in your member's list");
						}
					}while(managers[Service.indexOfManager(managers,userName)].indexMemberId(memberId) == -1);
					
					/*Getting the delivery code and delivery code input check*/
					do
					{
						isValidDeliveryCode = false;
						System.out.println("please enter the delivery code(delivery code can startwith: G(general delivery), E(express delivery) or B(business delivery)");
						deliveryCode = input.nextLine();
						tmpDelivery = new Delivery(deliveryCode);
						if(tmpDelivery.setDeliveryCode(deliveryCode, managers))
						{
							isValidDeliveryCode = true;
						}
					}while(!isValidDeliveryCode);
					
					do 
					{
						System.out.println("Please enter the delivery's price");
						price = input.nextDouble();
					
						System.out.println("Date the member invited the delivery");
						dateInvited = Service.createDate(dateInvited);
						
						/*what kind of delivery is tmpDelivery(G,E or B)*/
						if(tmpDelivery.getDeliveryCode().startsWith("G"))
						{
							tmpDelivery = new Delivery(deliveryCode,price,dateInvited);
						}
						
						if(tmpDelivery.getDeliveryCode().startsWith("E"))
						{
							System.out.println("Please enter the deadline date the member should get his delivery");
								
								deadlineArriveDate =Service.createDate(deadlineArriveDate);
								System.out.println("Please enter the after express addition price: ");
								afterExpressAdditionPrice = input.nextDouble();
							tmpDelivery = new Express(deliveryCode,price,dateInvited,deadlineArriveDate,afterExpressAdditionPrice);
						}
						if(tmpDelivery.getDeliveryCode().startsWith("B"))
						{
							input.nextLine();
							System.out.println("Please enter the company name that invited the delivery");
							companyName = input.nextLine();
							System.out.println("Please enter the after discount price of the delivery");
							afterDiscountPrice = input.nextDouble();
							tmpDelivery = new Business(deliveryCode,price,dateInvited,companyName,afterDiscountPrice);
						}
						
					/*adding the delivery*/
				  }	while(!(managers[Service.indexOfManager(managers, userName)].getManagerMembers()[managers[Service.indexOfManager(managers,userName)].indexMemberId(memberId)].addDelivery(tmpDelivery)));
					System.out.println("Delivery was added successfully");
				}
			}
				
			if(user_choice !='4')
			{
				System.out.println("Current manager's members list:");
				managers[Service.indexOfManager(managers,userName)].printManagerMembers();
			}
		}
		
		/*Option 4*/
		System.out.println("Thanks for using " + apostrophes + "Deliveries For You" + apostrophes + " deliveries managing system");
	}
}
