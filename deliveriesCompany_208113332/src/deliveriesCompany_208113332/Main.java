//Ohad Cohen 208113332
/*This program represents a managing deliveries system */
package deliveriesCompany_208113332;


import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		final char apostrophes = '"';
		char user_choice='0';
		String userName;
		System.out.println("Welcome to " + apostrophes + "Deliveries For You" + apostrophes + " deliveries managing system");
		Scanner input = new Scanner(System.in);
		
		/*Building the managers ArrayList*/
        DataBase.createManagersList();
		
        /*Building the members HashMaps of each manager*/
        DataBase.CreateInitialManualMembers();
        
		/*Building the deliveries for each member*/
        DataBase.CreateInitialManualDeliveries();
        
        /*LogIn*/
        while(user_choice != '8')
        {
        	do
            {
            	 userName = logIn();
            }while(userName == null);
        	
    		System.out.println("Manager's list:");
    		for(Manager m:DataBase.managers)
    		{
    			System.out.println(m);
    		}
    			
    		/* main menu*/
    		user_choice ='0';
    		System.out.println("Welcome " + DataBase.ManagerByUserName(userName).getFirstName() + " " + DataBase.ManagerByUserName(userName).getLastName());
    		while(user_choice != '4' && user_choice != '8')
    		{
    			System.out.println("Please choose option(1-4): ");
    			System.out.println("1 - Add member to my member's list");
    			System.out.println("2 - Remove member from my member's list");
    			System.out.println("3 - Add new delivery for customer");
    			System.out.println("4 - Back to main menu");
    			System.out.println("5 - Watch member's last delivery");
    			System.out.println("6 - Watch deliveries according to living area");
    			System.out.println("7 - Watch the whole system member's deliveries");
    			System.out.println("8 - Exit");
    			user_choice = input.next().charAt(0);
    			input.nextLine();
    			
    			/*Option 1*/
    			if(user_choice == '1')
    			{
    				option1(userName);
    			}
    			/*Option 2*/
    			if(user_choice == '2')
    			{
    				option2(userName);
    			}
    			/*Option 3*/
    			if(user_choice == '3')
    			{
    				option3(userName);
    			}
    			/*option 4*/
    			if(user_choice == '4')
    			{
    				System.out.println("You will be back in the log in screen");
    			}
    			/*option 5*/
    			if(user_choice =='5')
    			{
    				option5();
    			}
    			/*option 6*/
    			if(user_choice == '6')
    			{
    				option6();
    			}
    			/*option 7*/
    			if(user_choice =='7')
    			{
    				option7();
    			}
    			if(user_choice !='4' && user_choice != '8')
    			{
    				System.out.println("Current manager's members list:");
    				DataBase.ManagerByUserName(userName).printManagerMembers();
    				System.out.println();
    			}
    		}
        }
       System.out.println("Thank you for using " + apostrophes + "Deliveries For You" + apostrophes + " deliveries managing system"); 
}
	
/*This function returns userName that logged in, if the one of the details are wrong, the function returns null*/
public static String logIn()
{
	String userName,password;
	Manager tmpManager;
	Scanner input = new Scanner(System.in);
	/*Getting the userName and password*/
	
	System.out.println("Please insert your username: ");
	userName = input.nextLine();
	System.out.println("Please insert your password: ");
	password = input.nextLine();
		
	/*userName and password input check*/
	Iterator<Manager> itr1 = DataBase.managers.iterator();
	while(itr1.hasNext())
	{
		tmpManager = itr1.next();
		if(tmpManager.getUserName().equals(userName))
		{
			if(tmpManager.getPassword().equals(password))
			{
				return tmpManager.getUserName();
			}
		}
	}
	System.out.println("one of your details, username or password, are invalid ");
	return null;
}

/*This function do the features of option 1 in the main menu*/
public static void option1(String userName)
{
	boolean isValidMemberDetails,isValidLivingArea;
	String memberId,firstName,lastName,livingArea;
	Members tmpMember;
	Scanner input = new Scanner(System.in);
	if(userName == null)
		return;
	do
	{
		do
		{
			/*Getting the member details*/
			
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
			
			/*memberId and living area's input check*/
			if(!(livingArea.equals(DataBase.ManagerByUserName(userName).getManagerArea())))
			{
				System.out.println("Sorry you can not add this member, the admin of the " + DataBase.ManagerByUserName(userName).getManagerArea()
				+ " can add only member from the " + DataBase.ManagerByUserName(userName).getManagerArea());
				isValidLivingArea = false;
			}
			if(!(isValidLivingArea) || !(tmpMember.setMemberId(memberId, DataBase.managers, DataBase.ManagerByUserName(userName))))
			{
				System.out.println("You will be asked to retype the member's details");
				isValidMemberDetails = false;
			}		
		}while(!(isValidMemberDetails));
	
	/*Adding the member*/
	}while(!DataBase.ManagerByUserName(userName).addMember(tmpMember));
	System.out.println("Member was added successfully");
}

/*This function do the features of option 2 in the main menu*/
public static void option2(String userName)
{
	boolean isRemoveMember;
	String memberId;
	Scanner input = new Scanner(System.in);
	isRemoveMember = false;
	if(userName == null)
		return;
	if(DataBase.ManagerByUserName(userName).getManagerMembers() == null ||DataBase.ManagerByUserName(userName).getManagerMembers().size() == 0)
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
			if(!(DataBase.ManagerByUserName(userName).removeMember(memberId)))
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

/*This function do the features of option 3 in the main menu*/
public static void option3(String userName)
{
	String memberId,deliveryCode,companyName;
	Delivery tmpDelivery;
	Members tmpMember;
	boolean isValidDeliveryCode;
	Date dateInvited=null,deadlineArriveDate=null;
	double price,afterDiscountPrice,afterExpressAdditionPrice;
	Scanner input = new Scanner(System.in);
	if(userName == null)
		return;
	if(DataBase.ManagerByUserName(userName).getManagerMembers() == null || DataBase.ManagerByUserName(userName).getManagerMembers().size() == 0)
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
			if(!DataBase.ManagerByUserName(userName).getManagerMembers().containsKey(memberId))
			{
				System.out.println("The member id is not exist in your member's list");
			}
		}while(!DataBase.ManagerByUserName(userName).getManagerMembers().containsKey(memberId));
		
		/*Getting the delivery code and delivery code input check*/
		do
		{
			isValidDeliveryCode = false;
			System.out.println("please enter the delivery code(delivery code can startwith: G(general delivery), E(express delivery) or B(business delivery)");
			deliveryCode = input.nextLine();
			tmpDelivery = new Delivery(deliveryCode);
			if(tmpDelivery.setDeliveryCode(deliveryCode, DataBase.managers))
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
	  }	while(!DataBase.ManagerByUserName(userName).getManagerMembers().get(memberId).addDelivery(tmpDelivery));
		
		/*Adding also to lastDeliveries TreeMap and to MembersAndDeliveries1,2 TreeMap's*/
		tmpMember = DataBase.ManagerByUserName(userName).getManagerMembers().get(memberId);
		
		DataBase.ourLastDeliveries.put(tmpMember, tmpDelivery);
		DataBase.ourMembersAndDeliveries1.put(DataBase.countTree, tmpMember);
		DataBase.ourMembersAndDeliveries2.put(DataBase.countTree, tmpDelivery);
		DataBase.countTree += 5;
		System.out.println("Delivery was added successfully");
	}
}

/*This function do the features of option 5 in the main menu*/
/*I assumed that a manager can watch also the deliveries of other manager's members*/
public static void option5()
{
	final int NUMBEROFMANAGERS = 3;
	boolean isIdFound;
	String memberId;
	Manager tmpManager;
	Members tmpMember;
	int countEmptyManagerMembersHash=0;
	Scanner input = new Scanner(System.in);
	
	/*if the managers list is empty*/
	if(DataBase.managers == null || DataBase.managers.size() == 0)
	{
		System.out.println("The managers list is empty and therefore you can not use this option");
		return;
	}
	
	/*checking if the whole managers member's are empty*/
	Iterator<Manager> itr2 = DataBase.managers.iterator();
	while(itr2.hasNext())
	{
		tmpManager = itr2.next();
		if(tmpManager.getManagerMembers()== null || tmpManager.getManagerMembers().size() == 0)
		{
			countEmptyManagerMembersHash++;
		}
	}
	if(countEmptyManagerMembersHash == NUMBEROFMANAGERS)
	{
		System.out.println("Sorry, you can not use this option when all of the managers member's lists are empty");
		return;
	}
	
	/*Getting the member's id and finding its last delivery*/
	do
	{
		isIdFound = false;
		System.out.println("Please insert memberId:");
		memberId = input.next();
		tmpMember = new Members(memberId);
		for(Entry<Members,Delivery> entry: DataBase.ourLastDeliveries.entrySet())
		{
			if(entry.getKey().equals(tmpMember))
			{
				System.out.println("The member's last delivery is:");
				System.out.println(entry.getValue());
				System.out.println("__________________________________");
				isIdFound=true;
				return;
			}
		}
		if(isIdFound == false)
		{
			System.out.println("The id you inserted is not exist in the managers member's list or does not have deliveries");
		}
	}while(!isIdFound);
}

/*This function do the features of option 6 in the main menu*/
public static void option6()
{
	String livingArea;
	Scanner input = new Scanner(System.in);
	
	/*Getting the living area and input checking*/
	System.out.println("Please type the living area you would like to watch its deliveries(north,center or south): ");
	do
	{
		livingArea = input.next();
		if(!(Service.isValidArea(livingArea)))
		{
			System.out.println("Living area can be only north, center or south, please type the living area again:");
		}
	}while(!(Service.isValidArea(livingArea)));
	
	/*printing the whole area deliveries*/
	System.out.println("These are the whole deliveries from the "+ livingArea + ":");
	for(Entry<Integer,Members> entry:DataBase.ourMembersAndDeliveries1.entrySet())
	{
		if(entry.getValue().getLivingArea().equals(livingArea))
		{
			System.out.println(DataBase.ourMembersAndDeliveries2.get(entry.getKey()));
		}
	}
	System.out.println("___________________________________________");
}

/*This function do the features of option 7 in the main menu*/
public static void option7()
{
	Iterator<Manager> itr1 = DataBase.managers.iterator();
	Manager tmpManager;
	Delivery tmpDelivery;
	
	/*printing the whole members and their deliveries*/
	System.out.println("These are the whole system members and deliveries: ");
	System.out.println();
	while(itr1.hasNext())
	{
		tmpManager = itr1.next();
		for(Entry<String, Members> entry:tmpManager.getManagerMembers().entrySet())
		{
			System.out.println(entry.getValue().printMemberWithoutDeliveries());
			Iterator<Delivery> itr2 = entry.getValue().getDeliveries().iterator();
			while(itr2.hasNext())
			{
				tmpDelivery = itr2.next();
				System.out.println(tmpDelivery);
			}
			System.out.println();
		}
	}
}
}
