package deliveriesCompany_208113332;
import java.util.Scanner;
/*This class meant for having useful general function for the project*/
public class Service {
	
	/*This function returns the index of the manager who entered the system in the array of managers*/
	public static int indexOfManager(Manager[] managers,String userName)
	{
		int index = 0;
		while(index < managers.length)
		{
			if(managers[index].getUserName().equals(userName))
			{
				return index;
			}
			index++;
		}
		return -1;	
	}
	
	/*This function creates a date*/
	public static Date createDate(Date d)
	{
		int day,mounth,year;
		Scanner input = new Scanner(System.in);
		System.out.println("Day:");
		day = input.nextInt();
		System.out.println("Mounth:");
		mounth = input.nextInt();
		System.out.println("Year:");
		year = input.nextInt();
		d = new Date(day,mounth,year);
		return d;
	}
	/*This function returns true if the area it got is a valid one(north,center,south) */
	/*else , it returns false*/
	public static boolean isValidArea(String area)
	{
		if(!(area.equals("north")) && !(area.equals("center")) && !(area.equals("south")))
		{
			return false;
		}
		return true;
	}
}
