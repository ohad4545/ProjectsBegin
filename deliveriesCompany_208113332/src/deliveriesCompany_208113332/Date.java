package deliveriesCompany_208113332;

public class Date {
	private int day;
	private int mounth;
	private int year;
	
	/*constructor*/
	public Date(int day, int mounth, int year) {
		super();
		this.day = day;
		this.mounth = mounth;
		this.year = year;
	}
		
	/*Getters and Setters*/
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMounth() {
		return mounth;
	}
	public void setMounth(int mounth) {
		this.mounth = mounth;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	/*toString function*/
	@Override
	public String toString() {
		return "Date [day=" + day + ", mounth=" + mounth + ", year=" + year + "]";
	}
	
}
