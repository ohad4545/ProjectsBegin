/*This is a class with static values and functions that meant for keeping the whole database of the deliveries company*/
package deliveriesCompany_208113332;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;



public class DataBase {
	public static Integer countTree = 100; 
	public static ArrayList<Manager> managers = new ArrayList<Manager>();
	public static Map<Members,Delivery> ourLastDeliveries = new TreeMap<Members,Delivery>();
	public static Map<Integer,Members> ourMembersAndDeliveries1 = new TreeMap<Integer,Members>();
	public static Map<Integer,Delivery> ourMembersAndDeliveries2 = new TreeMap<Integer,Delivery>();
	
	
	/*Functions*/
	
	/*This function builds the ArrayList of the managers*/
	public static void createManagersList()
	{
										 /*userNames*//*Passwords*/
		DataBase.managers.add(new Manager("adminNorth","adminNorth","north","Avi","Cohen"));
		DataBase.managers.add(new Manager("adminCenter","adminCenter","center","Moshe","Levi"));
		DataBase.managers.add(new Manager("adminSouth","adminSouth","south","George","Jackson"));
	}
	
	/*This function Builds the manager's initial manual members HashMaps and ourMembersAndDeliveries1*/
	public static void CreateInitialManualMembers()
	{
		Members tmpMember;
		
		tmpMember = new Members("123456789", "Ohad", "Cohen", "north");
		DataBase.managers.get(0).getManagerMembers().put(tmpMember.getMemberId(), tmpMember);
		tmpMember = new Members("987654321", "Boaz", "Maoda", "north");
		DataBase.managers.get(0).getManagerMembers().put(tmpMember.getMemberId(), tmpMember);
		tmpMember = new Members("159357456", "Lior", "Sushard", "center");
		DataBase.managers.get(1).getManagerMembers().put(tmpMember.getMemberId(), tmpMember);
		tmpMember = new Members("852146927", "Samuel.L", "Jackson", "center");
		DataBase.managers.get(1).getManagerMembers().put(tmpMember.getMemberId(), tmpMember);
		tmpMember = new Members("004852147", "Neli", "Tagar", "south");
		DataBase.managers.get(2).getManagerMembers().put(tmpMember.getMemberId(), tmpMember);
		tmpMember = new Members("194682375", "Kobi", "Mahat", "south");
		DataBase.managers.get(2).getManagerMembers().put(tmpMember.getMemberId(), tmpMember);
	}
	
	/*This function builds the member's initial manual deliveries ArrayLists and ourMembersAndDeliveries1,2 and ourLastDeliveries*/
	public static void CreateInitialManualDeliveries()
	{
		Date dateInvited,deadlineArriveDate;
		Delivery tmpDelivery;
		/*manager of the north members deliveries*/
		/*Id:123456789*/
		dateInvited = new Date(15,5,2020);
		tmpDelivery = new Delivery("G1",15.33,dateInvited);
		DataBase.managers.get(0).getManagerMembers().get("123456789").addDelivery(tmpDelivery);
		DataBase.ourMembersAndDeliveries1.put(countTree, DataBase.managers.get(0).getManagerMembers().get("123456789"));
		DataBase.ourMembersAndDeliveries2.put(countTree,tmpDelivery);
		DataBase.countTree += 5;
		DataBase.ourLastDeliveries.put(DataBase.managers.get(0).getManagerMembers().get("123456789"), tmpDelivery);
		
		dateInvited = new Date(17,10,2021);
		deadlineArriveDate = new Date(18,11,2021);
		tmpDelivery = new Express("E1",100.45,dateInvited,deadlineArriveDate,120);
		DataBase.managers.get(0).getManagerMembers().get("123456789").addDelivery(tmpDelivery);
		DataBase.ourMembersAndDeliveries1.put(countTree, DataBase.managers.get(0).getManagerMembers().get("123456789"));
		DataBase.ourMembersAndDeliveries2.put(countTree,tmpDelivery);
		DataBase.countTree += 5;
		DataBase.ourLastDeliveries.put(DataBase.managers.get(0).getManagerMembers().get("123456789"), tmpDelivery);
		
		/*Id:987654321*/
		dateInvited = new Date(4,1,2021);
		tmpDelivery = new Delivery("G2",38.12,dateInvited);
		DataBase.managers.get(0).getManagerMembers().get("987654321").addDelivery(tmpDelivery);
		DataBase.ourMembersAndDeliveries1.put(countTree, DataBase.managers.get(0).getManagerMembers().get("987654321"));
		DataBase.ourMembersAndDeliveries2.put(countTree,tmpDelivery);
		DataBase.countTree += 5;
		DataBase.ourLastDeliveries.put(DataBase.managers.get(0).getManagerMembers().get("987654321"), tmpDelivery);
		
		dateInvited = new Date(17,10,2021);
		tmpDelivery = new Delivery("G3",370,dateInvited);
		DataBase.managers.get(0).getManagerMembers().get("987654321").addDelivery(tmpDelivery);
		DataBase.ourMembersAndDeliveries1.put(countTree, DataBase.managers.get(0).getManagerMembers().get("987654321"));
		DataBase.ourMembersAndDeliveries2.put(countTree,tmpDelivery);
		DataBase.countTree += 5;
		DataBase.ourLastDeliveries.put(DataBase.managers.get(0).getManagerMembers().get("987654321"), tmpDelivery);
		
		/*manager of the center members deliveries*/
		/*Id: 159357456*/
		dateInvited = new Date(31,3,2019);
		tmpDelivery = new Delivery("G4",55.6,dateInvited);
		DataBase.managers.get(1).getManagerMembers().get("159357456").addDelivery(tmpDelivery);
		DataBase.ourMembersAndDeliveries1.put(countTree, DataBase.managers.get(1).getManagerMembers().get("159357456"));
		DataBase.ourMembersAndDeliveries2.put(countTree,tmpDelivery);
		DataBase.countTree += 5;
		DataBase.ourLastDeliveries.put(DataBase.managers.get(1).getManagerMembers().get("159357456"), tmpDelivery);
		
		dateInvited = new Date(1,1,2021);
		tmpDelivery = new Business("B1",1000.78,dateInvited,"APU software systems",890.6);
		DataBase.managers.get(1).getManagerMembers().get("159357456").addDelivery(tmpDelivery);
		DataBase.ourMembersAndDeliveries1.put(countTree, DataBase.managers.get(1).getManagerMembers().get("159357456"));
		DataBase.ourMembersAndDeliveries2.put(countTree,tmpDelivery);
		DataBase.countTree += 5;
		DataBase.ourLastDeliveries.put(DataBase.managers.get(1).getManagerMembers().get("159357456"), tmpDelivery);
		
		dateInvited = new Date(15,9,2021);
		
		/*Id:852146927*/
		tmpDelivery = new Delivery("G5",44.8,dateInvited);
		DataBase.managers.get(1).getManagerMembers().get("852146927").addDelivery(tmpDelivery);
		DataBase.ourMembersAndDeliveries1.put(countTree, DataBase.managers.get(1).getManagerMembers().get("852146927"));
		DataBase.ourMembersAndDeliveries2.put(countTree,tmpDelivery);
		DataBase.countTree += 5;
		DataBase.ourLastDeliveries.put(DataBase.managers.get(1).getManagerMembers().get("852146927"), tmpDelivery);
		
		dateInvited = new Date(20,10,2021);
		deadlineArriveDate = new Date(29,11,2021);
		tmpDelivery = new Express("E2",350,dateInvited,deadlineArriveDate,400);
		DataBase.managers.get(1).getManagerMembers().get("852146927").addDelivery(tmpDelivery);
		DataBase.ourMembersAndDeliveries1.put(countTree, DataBase.managers.get(1).getManagerMembers().get("852146927"));
		DataBase.ourMembersAndDeliveries2.put(countTree,tmpDelivery);
		DataBase.countTree += 5;
		DataBase.ourLastDeliveries.put(DataBase.managers.get(1).getManagerMembers().get("852146927"), tmpDelivery);
		
		/*manager of the south members deliveries*/
		/*Id:004852147*/
		dateInvited = new Date(31,3,2019);
		tmpDelivery = new Delivery("G6",55.6,dateInvited);
		DataBase.managers.get(2).getManagerMembers().get("004852147").addDelivery(tmpDelivery);
		DataBase.ourMembersAndDeliveries1.put(countTree, DataBase.managers.get(2).getManagerMembers().get("004852147"));
		DataBase.ourMembersAndDeliveries2.put(countTree,tmpDelivery);
		DataBase.countTree += 5;
		DataBase.ourLastDeliveries.put(DataBase.managers.get(2).getManagerMembers().get("004852147"), tmpDelivery);
		
		dateInvited = new Date(1,1,2021);
		tmpDelivery = new Business("B2",50.4,dateInvited,"Nice lambs",45);
		DataBase.managers.get(2).getManagerMembers().get("004852147").addDelivery(tmpDelivery);
		DataBase.ourMembersAndDeliveries1.put(countTree, DataBase.managers.get(2).getManagerMembers().get("004852147"));
		DataBase.ourMembersAndDeliveries2.put(countTree,tmpDelivery);
		DataBase.countTree += 5;
		DataBase.ourLastDeliveries.put(DataBase.managers.get(2).getManagerMembers().get("004852147"), tmpDelivery);
		
		/*Id:194682375*/
		dateInvited = new Date(15,9,2021);
		deadlineArriveDate = new Date(15,9,2021);
		tmpDelivery = new Express("E3",44.8,dateInvited,deadlineArriveDate,60);
		DataBase.managers.get(2).getManagerMembers().get("194682375").addDelivery(tmpDelivery);
		DataBase.ourMembersAndDeliveries1.put(countTree, DataBase.managers.get(2).getManagerMembers().get("194682375"));
		DataBase.ourMembersAndDeliveries2.put(countTree,tmpDelivery);
		DataBase.countTree += 5;
		DataBase.ourLastDeliveries.put(DataBase.managers.get(2).getManagerMembers().get("194682375"), tmpDelivery);
		
		dateInvited = new Date(20,10,2021);
		tmpDelivery = new Delivery("G7",350,dateInvited);
		DataBase.managers.get(2).getManagerMembers().get("194682375").addDelivery(tmpDelivery);
		DataBase.ourMembersAndDeliveries1.put(countTree, DataBase.managers.get(2).getManagerMembers().get("194682375"));
		DataBase.ourMembersAndDeliveries2.put(countTree,tmpDelivery);
		DataBase.countTree += 5;
		DataBase.ourLastDeliveries.put(DataBase.managers.get(2).getManagerMembers().get("194682375"), tmpDelivery);
	}
	
	/*This function gets the userName of a manager and returns the manager with the same userName the function got*/
	public static Manager ManagerByUserName(String userName)
	{
		if(userName == null)
		{
			 return null;
		}
		Iterator<Manager> itr1 = DataBase.managers.iterator();
		Manager tmpManager;
		while(itr1.hasNext())
		{
			tmpManager = itr1.next();
			if(tmpManager.getUserName().equals(userName))
			{
				return tmpManager;
			}
		}
		return null;
	}
       
	/*This function returns true if the lastName it gets already exists in the managers memeber's HashMaps*/
	/*else,it returns false*/
	public static boolean sameLastName(String lastName)
	{
		if(lastName == null)
			return false;
		Iterator<Manager> itr1 = DataBase.managers.iterator();
		Manager tmpManager;
		while(itr1.hasNext())
		{
			tmpManager = itr1.next();
			for(Entry<String,Members> entry:tmpManager.getManagerMembers().entrySet())
			{
				if(entry.getValue().getLastName().toLowerCase().equals(lastName.toLowerCase()))
				{
					return true;
				}
			}
		}
		return false;
	}
	
}
