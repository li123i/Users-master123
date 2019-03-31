package DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Datetime implements Cloneable {

    	public  static class IDateTime {

    		private int year;private String year1;
    		private int month;private String month1;
    		private int day;private String day1;
    		private int hour;private String hour1;
    		private int minute;private String minute1;
    		private int second;private String second1;
    		private String str;
    		private Date date=new Date();
    		public IDateTime(String str) {
    			this.str=str;
    		}
       		public int getYear() {
       			year1=this.str.substring(0,4);
       			year=Integer.parseInt(year1);
    			return year;
    		}

    		public int getMonth() {
    			month1=this.str.substring(5,7);
    			month=Integer.parseInt(month1);
    			return month;
    		}
  
    		public int getDay() {
    			day1=this.str.substring(8,10);
    			day=Integer.parseInt(day1);
    			return day;
    		}
    			

    		public int getHour() {
    			hour1=this.str.substring(11,13);
        		hour=Integer.parseInt(hour1);
        		return hour;
    		}

    		public  int getMinute() {
    			minute1=this.str.substring(14,16);
        		minute=Integer.parseInt(minute1);
        		return minute;
    		}

    		public	int getSecond() {
    			second1=this.str.substring(17,19);
    			second=Integer.parseInt(second1);
        		return second;
    		}

    		public	void addDays(int day) {
    			this.day=day+this.day;
    			System.out.println(this.day);
    		}

    		public void addMonths(int month) {
    			this.month=this.month+month;
    			System.out.println(this.month);

    		}

    		public 	void addYears(int year) {
    			this.year=year+this.year;
    			System.out.println(this.year);
    		}

    		public 	void addHour(int hour) {    		
    			this.hour=this.hour+hour;
    			System.out.println(this.hour);
    		}

    		public	void addMinute(int minute) {
    			this.minute=this.minute+minute;
    			System.out.println(this.minute);
    		}

    		public	void addSecond(int second) {
    			this.second=this.second+second;
    			System.out.println(this.second);
    		}

    			/*
    			 * 按照dateFormat输出当前日期
    			 */
    		public	String dateToString() {
    			String date=year1+"年"+month1+"月"+day1+"日"+" "+hour1+"时"+minute1+"分"+second1+"秒";
    			return date;
    		}

    			/*
    			 * 按照dateTimeFormat输出当前日期时间
    			 */
    		public	String dateTimeToString() {
    			String date=year1+"年"+month1+"月"+day1+"日"+" "+hour1+"时"+minute1+"分"+second1+"秒"+" CST";
    			return date;
    		}
    		/*
    		 * 判断自己是否是闰年ֵ
    		 */
    		public boolean isThisLeapYear() {
    			return (year%4==0&&year%100!=0||year%400==0) ;
    		}

    		/*
    		 * 获取指定年月的天数
    		 */
    		public int getDaysInMonth(int y, int m) {
    			int d;
    		    int day[]= {31,31,30,31,30,31,31,30,31,30,31};
    		    if (2==m)
    		    {
    		        d=(((0==y%4)&&(0!=y%100)||(0==y%400))?29:28);
    		    }
    		    else
    		    {
    		        d=day[m-1];

    		    }
    		    return d;
    		    }
    		/*
    		 * 得到俩个日期间的间隔天数
    		 */
    		public int getTwoDay(String date1, String date2) {
    			int a1=Integer.parseInt(date1);
    			int a2=Integer.parseInt(date2);
    			return a2-a1;
    		}
    		/*
    		 * 设定日期格式：例(yyyy-MM-dd)
    		 */
    		public void setDateFormat(String format) {
    			String date=year1+"-"+month1+"-"+day1;
    			System.out.println(date);
    		}

    		/*
    		 * 设定日期时间格式，例(yyyy-MM-dd HH:mm:ss)
    		 */
    		public void setDateTimeFormat(String format) {
    	        String time =year1+"-"+month1+"-"+day1+" "+hour1+":"+minute1+":"+second1;
    			System.out.println(time);
    		}

    		/*
    		 * 调用此方法的对象和指定日期是否相等
    		 */
    		public boolean equals(Object date1) {
    			return this.str==date1;

    		}
    		public Object clone() throws CloneNotSupportedException{
    			IDateTime str=null;
    			str=(IDateTime)super.clone();
    			return str;
    		}
    	}
	public static void main(String[] args) {
		IDateTime datetime=new IDateTime("2010-11-01-01-01-01");
		String str;
		int a;
		a=datetime.getYear();
		System.out.println(a+"年");
		a=datetime.getMonth();
		System.out.println(a+"月");
		a=datetime.getDay();
		System.out.println(a+"天");
		a=datetime.getHour();
		System.out.println(a+"时");
		a=datetime.getMinute();
		System.out.println(a+"分");
		a=datetime.getSecond();
		System.out.println(a+"秒");
		str=datetime.dateToString();
		System.out.println("当前时间是"+str);
		str=datetime.dateTimeToString();
		System.out.println("当前时间是"+str);
		datetime.addYears(1);
		datetime.addMonths(12);
		datetime.addDays(1);
		datetime.addHour(1);
		datetime.addMinute(1);
		datetime.addSecond(1);
		boolean p;
		p=datetime.isThisLeapYear();
		System.out.println(p);
		a=datetime.getTwoDay("1","21");
		System.out.println("相差天数为"+a);
		datetime.setDateFormat("yyyy-MM-dd");
		datetime.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
		Date date1=new Date(2010-1900,1,1,1,1,1); 
		p=datetime.equals("2010-01-01-01-01-01");
		System.out.println(p);
		
	}
}

