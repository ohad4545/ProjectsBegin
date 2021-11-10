package deliveriesCompany_208113332;
/*This class meant for having useful general function for the project*/
public class Service {
	
	/*This function checks if a name input is a valid one*/
	public static boolean isValidName(String name)
	{
		for(int index = 0; index < name.length();index++) {
			if((name.charAt(index) < 65 || name.charAt(index) > 90) && name.charAt(index) != 32)
			{
				if(name.charAt(index) < 97 || name.charAt(index) > 122)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/*This function returns the index of the manager who entered the system in the array of managers*/
	public static int indexOfManager(Manager[] managers,String userName)
	{
		int index = 0;
		while(index < 3)
		{
			if(managers[index].getUserName().equals(userName))
			{
				return index;
			}
			index++;
		}
		return -1;
		
		
	}
}
