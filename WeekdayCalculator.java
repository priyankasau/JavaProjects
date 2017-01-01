
import java.util.Scanner;

public class WeekdayCalculator
{
	public static void main( String[] args )
	{
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Welcome to Mr. Mitchell's fantastic birth-o-meter!");
		System.out.println();
		System.out.println("All you have to do is enter your birth date, and it will");
		System.out.println("tell you the day of the week on which you were born.");
		System.out.println();
		System.out.println("Some automatic tests....");
		System.out.println("12 10 2003 => " + weekday(12,10,2003));
		System.out.println(" 2 13 1976 => " + weekday(2,13,1976));
		System.out.println(" 2 13 1977 => " + weekday(2,13,1977));
		System.out.println(" 7  2 1974 => " + weekday(7,2,1974));
		System.out.println(" 1 15 2003 => " + weekday(1,15,2003));
		System.out.println("10 13 2000 => " + weekday(10,13,2000));
		System.out.println();

		System.out.println("Now it's your turn!  What's your birthday?");
		System.out.print("Birth date (mm dd yyyy): ");
		int mm = keyboard.nextInt();
		int dd = keyboard.nextInt();
		int yyyy = keyboard.nextInt();
			
		//function call for weekday()
		String weekdayformat = weekday(mm, dd, yyyy);
		System.out.println("You were born on "+weekdayformat);
	}


	public static String weekday( int mm, int dd, int yyyy )
	{
		int yy, total;
		String date = "";
		
		yy = yyyy-1900;
		total = yy/4;
		total = total+yy;
		total = total+dd;
		total = total + month_offset(mm);
	
		if( ( mm == 01 || mm == 02) && is_leap(yyyy) ) {
			total = total - 1;
		}
		
		int dayOfWeek = total%7; 
		date = day_name(dayOfWeek)+", " +month_name(mm)+" "+dd+ "," + yyyy;

		return date;
	}


	// paste your functions from MonthName, WeekdayName, and MonthOffset here
		
	public static boolean is_leap( int year )
	{
		// years which are evenly divisible by 4 are leap years,
		// but years divisible by 100 are not leap years,
		// though years divisible by 400 are leap years
		boolean result;

		if ( year%400 == 0 )
			result = true;
		else if ( year%100 == 0 )
			result = false;
		else if ( year%4 == 0 )
			result = true;
		else
			result = false;
		
		return result;
	}



	public static int month_offset(int monthNumber){
		switch( monthNumber ){
		case 1:
			return 1;
			
		case 2:
			return 4;
			
		case 3:
			return 4;
			
		case 4:
			return 0;
			
		case 5:
			return 2;
				
		case 6:
			return 5;
			
		case 7:
			return 0;
			
		case 8:
			return 3;
			
		case 9:
			return 6;
			
		case 10:
			return 1;
			
		case 11:
			return 4;
			
		case 12:
			return 6;
		default :
			return -1;
		}
	}


	public static String month_name( int month )
	{
		String result;
			switch( month ){
			case 1:
				result= "January";
				break;
			case 2:
				result= "February";
				break;
			case 3:
				result= "March";
				break;
			case 4:
				result= "April";
				break;
			case 5:
				result= "May";
				break;	
			case 6:
				result= "June";
				break;
			case 7:
				result= "July";
				break;
			case 8:
				result= "August";
				break;
			case 9:
				result= "September";
				break;
			case 10:
				result= "October";
				break;
			case 11:
				result= "November";
				break;
			case 12:
				result= "December";
				break;
			default :
				result= "error";
			}
		
		return result;
	}

	public static String day_name( int day )
	{
		String result;
			switch( day ){
			
			case 1:
				result= "Monday";
				break;
			case 2:
				result= "Tuesday";
				break;
			case 3:
				result= "Wednesday";
				break;
			case 4:
				result= "Thursday";
				break;
			case 5:
				result= "Friday";
				break;	
			case 6:
				result= "Saturday";
				break;
			case 0:
				result ="Sunday";
				break;
			default :
				result = "error";
			}
		return result;
	}
}



