package mylist;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.Integer;
public class Mylist {

    	public  static class IDateTime {

    		private int year;
    		private int month;
    		private int day;
    		private int hour;
    		private int minute;
    		private Date date=new Date();
    		public int getYear() {
    			SimpleDateFormat time=new SimpleDateFormat("yyyy");
    			String str=time.format(date);
    			year=Integer.parseInt(str);
    			return year;
    		}

    		public int getMonth() {
    			SimpleDateFormat time=new SimpleDateFormat("MM");
    			String str=time.format(date);
    			month=Integer.parseInt(str);
    			return month;
    		}
  
    		public int getDay() {
    			SimpleDateFormat time=new SimpleDateFormat("dd");
    			String str=time.format(date);
    			day=Integer.parseInt(str);
    			return day;
    		}
    			

    		public int getHour() {
    				SimpleDateFormat time=new SimpleDateFormat("HH");
        			String str=time.format(date);
        			hour=Integer.parseInt(str);
        			return hour;
    			}

    		public  int getMinute() {
    				SimpleDateFormat time=new SimpleDateFormat("mm");
        			String str=time.format(date);
        			minute=Integer.parseInt(str);
        			return minute;
    			}

    		
    	}

	public static void main(String[] args) {
		 // Create a date formatter that can parse dates of the form MM-dd-yyyy. 
	    SimpleDateFormat bartDateFormat = new SimpleDateFormat("MM-dd-yyyy"); 
	    
	    // Create a string containing a text date to be parsed. 
	    String dateStringToParse = "8-8-2005"; 
	    try { 
	      // Parse the text version of the date. 
	      //We have to perform the parse method in a 
	      //try-catch construct in case dateStringToParse 
	      //does not contain a date in the format we are expecting. 
	      Date date = bartDateFormat.parse(dateStringToParse); 
	      // Now send the parsed date as a long value 
	      // to the system output. 
	      System.out.println(date.getTime()); 
	    } 
	    catch (Exception ex){ 
	      System.out.println(ex.getMessage());   
	    }
	  } 

	}
}

